package sistema_industrial_weg.service.request;

import sistema_industrial_weg.dto.request.create.RequestMaterialRequest;
import sistema_industrial_weg.dto.request.get.RequestGetResponse;
import sistema_industrial_weg.model.item_request.ItemRequest;
import sistema_industrial_weg.model.item_request.ItemRequestId;
import sistema_industrial_weg.model.request.Request;

public class RequestMapper {
    public ItemRequest toEntity(RequestMaterialRequest requestMaterialRequest) {
        ItemRequestId id = new ItemRequestId(-1, requestMaterialRequest.idMaterial());
        return new ItemRequest(id, requestMaterialRequest.quantity());
    }

    public RequestGetResponse toGetResponse(Request request) {
        return new RequestGetResponse(request.getId(), request.getSector(), request.getRequestDate(), request.getStatus());
    }
}
