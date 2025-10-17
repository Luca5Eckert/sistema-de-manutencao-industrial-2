package sistema_industrial_weg.service.provider;

import sistema_industrial_weg.dto.provider.ProviderGetResponse;
import sistema_industrial_weg.model.provider.Provider;

public class ProviderMapper {
    public ProviderGetResponse toGetResponse(Provider provider) {
        return new ProviderGetResponse(provider.getId(), provider.getName(), provider.getCnpj());
    }
}
