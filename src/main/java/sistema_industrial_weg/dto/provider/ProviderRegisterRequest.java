package sistema_industrial_weg.dto.provider;

import java.util.Objects;

public record ProviderRegisterRequest(String name, String cnpj) {

    public ProviderRegisterRequest {
        if(Objects.isNull(name) || name.isBlank()) throw new RuntimeException("Nome de fornecedor não pode ficar em branco");

        if(Objects.isNull(cnpj) || cnpj.isBlank()) throw new RuntimeException("Cnpj de fornecedor não pode ficar em branco");
    }


}
