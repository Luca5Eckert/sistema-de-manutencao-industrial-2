package sistema_industrial_weg.service.request;

import sistema_industrial_weg.dto.request.create.RequestMaterialRequest;
import sistema_industrial_weg.model.item_request.ItemRequest;
import sistema_industrial_weg.model.item_request.ItemRequestId;

public class RequestMapper {
    public ItemRequest toEntity(RequestMaterialRequest requestMaterialRequest) {
        ItemRequestId id = new ItemRequestId(-1, requestMaterialRequest.idMaterial());
        return new ItemRequest(id, requestMaterialRequest.quantity());
    }
}
