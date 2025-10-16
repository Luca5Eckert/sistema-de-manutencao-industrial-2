package sistema_industrial_weg.service.material;

import sistema_industrial_weg.dto.material.MaterialRegisterRequest;
import sistema_industrial_weg.model.material.Material;
import sistema_industrial_weg.repository.MaterialRepository;

public class MaterialService {

    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }


    public void register(MaterialRegisterRequest request) {
        if(request == null) throw new RuntimeException("Requisição não pode ser nula");

        if(request.initialQuantity() < 0) throw new RuntimeException(" Valor inicial do material não pode ser negativo");

        if(materialRepository.existByName(request.name())) throw new RuntimeException("Material já cadastrado com esse nome");

        Material material = new Material(request.name(), request.unit(), request.initialQuantity());

        materialRepository.save(material);
    }

}
