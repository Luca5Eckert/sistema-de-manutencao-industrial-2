package sistema_industrial_weg.dto.request.create;

import java.util.List;

public record RequestCreateRequest(String sector, List<RequestMaterialRequest> materialGetResponseList) {

    public RequestCreateRequest {
        if(sector == null || sector.isBlank()){
            throw new RuntimeException("O setor não pode ficar em branco");
        }
        if(materialGetResponseList == null || materialGetResponseList.isEmpty()){
            throw new RuntimeException("A lista de materias não pode ser vazia");
        }
    }

}
