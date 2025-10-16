package sistema_industrial_weg.dto.material;

import java.util.Objects;

public record MaterialRegisterRequest(String name, String unit, double initialQuantity) {

    public MaterialRegisterRequest {
        if(Objects.isNull(name) || name.isBlank()) throw new RuntimeException("Nome de fornecedor não pode ficar em branco");

        if(Objects.isNull(unit) || unit.isBlank()) throw new RuntimeException("Unidade de fornecedor não pode ficar em branco");

        if(initialQuantity() < 0 ) throw new RuntimeException("Quantidade inicial não pode ficar em branco ou ser negativa");
    }


}
