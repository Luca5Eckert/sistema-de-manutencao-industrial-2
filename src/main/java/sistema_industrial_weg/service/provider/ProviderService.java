package sistema_industrial_weg.service.provider;

import sistema_industrial_weg.dto.provider.ProviderRegisterRequest;
import sistema_industrial_weg.model.provider.Provider;
import sistema_industrial_weg.repository.ProviderRepository;

import java.util.Objects;

public class ProviderService {

    private final ProviderRepository providerRepository;

    public ProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    public void register(ProviderRegisterRequest request) {
        if(Objects.isNull(request)) throw new RuntimeException("Requisição não pode ser nula");

        if(providerRepository.existByCnpj(request.cnpj())) throw new RuntimeException("Cnpj já cadastrado");

        Provider provider = new Provider(request.name(), request.cnpj());
        providerRepository.save(provider);
    }
}
