package sistema_industrial_weg.service.request;

import sistema_industrial_weg.dto.request.create.RequestCreateRequest;
import sistema_industrial_weg.dto.request.create.RequestMaterialRequest;
import sistema_industrial_weg.dto.request.get.RequestGetResponse;
import sistema_industrial_weg.model.item_request.ItemRequest;
import sistema_industrial_weg.model.material.Material;
import sistema_industrial_weg.model.request.Request;
import sistema_industrial_weg.model.request.enumerator.RequestStatus;
import sistema_industrial_weg.repository.ItemRequestRepository;
import sistema_industrial_weg.repository.MaterialRepository;
import sistema_industrial_weg.repository.RequestRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class RequestService {

    private final RequestRepository requestRepository;
    private final ItemRequestRepository itemRequestRepository;
    private final RequestMapper requestMapper;
    private final MaterialRepository materialRepository;

    public RequestService(RequestRepository requestRepository, ItemRequestRepository itemRequestRepository, RequestMapper requestMapper, MaterialRepository materialRepository) {
        this.requestRepository = requestRepository;
        this.itemRequestRepository = itemRequestRepository;
        this.requestMapper = requestMapper;
        this.materialRepository = materialRepository;
    }

    public void create(RequestCreateRequest createRequest){
        var listMaterialStock = materialRepository.getMaterials(createRequest.materialGetResponseList().stream().map(RequestMaterialRequest::idMaterial).toList());

        if(!haveSuficientQuantity(createRequest.materialGetResponseList(), listMaterialStock)) throw new RuntimeException("Quantidade insuficiente de materias");

        Request request = new Request(createRequest.sector(), LocalDate.now(), RequestStatus.PENDENTE);
        requestRepository.save(request);

        var listItemRequest = createRequest.materialGetResponseList().stream().map(requestMapper::toEntity).toList();
        itemRequestRepository.saveAll(request.getId(), listItemRequest);

    }

    private boolean haveSuficientQuantity(List<RequestMaterialRequest> requestMaterialRequests, List<Material> listMaterialStock) {
        for (RequestMaterialRequest material : requestMaterialRequests) {
            if(material.quantity() > getMaterial(material.idMaterial(), listMaterialStock).getStock()){
                return false;
            }
        }
        return true;
    }

    private Material getMaterial(long id, List<Material> materialList) {
        for (Material material : materialList) {

            if(material.getId() == id){
                return material;
            }

        }
        throw new RuntimeException("Erro ao encontrar material");

    }


    public List<RequestGetResponse> getAllPendent() {
        return requestRepository.getAllPendent().stream().map(requestMapper::toGetResponse).toList();
    }

    public void cancelRequest(long id) {
        if(!requestRepository.existById(id)) throw new RuntimeException("Nenhuma requisição encontrada com id: " + id);

        requestRepository.changeStatus(id, RequestStatus.CANCELADA);
    }

    public void execute(long id) {
        var materials = requestRepository.getMaterials(id);

        if(!canExecute(materials)) throw new RuntimeException("Quantidade insuficiente de materias");

        requestRepository.changeStatus(id, RequestStatus.ATENDIDA);

        materialRepository.updateAll(materials);
    }

    private boolean canExecute(Map<ItemRequest, Material> materials) {
        for(Map.Entry<ItemRequest, Material> materialEntry : materials.entrySet()){
            if(materialEntry.getKey().getQuantity() > materialEntry.getValue().getStock()){
                return false;
            }
        }
        return true;
    }

}
