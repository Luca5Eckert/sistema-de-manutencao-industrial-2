package sistema_industrial_weg.service.provider;

import sistema_industrial_weg.dto.provider.ProviderGetResponse;
import sistema_industrial_weg.dto.provider.ProviderRegisterRequest;
import sistema_industrial_weg.model.provider.Provider;
import sistema_industrial_weg.repository.ProviderRepository;

import java.util.List;
import java.util.Objects;

public class ProviderService {

    private final ProviderRepository providerRepository;
    private final ProviderMapper providerMapper;

    public ProviderService(ProviderRepository providerRepository, ProviderMapper providerMapper) {
        this.providerRepository = providerRepository;
        this.providerMapper = providerMapper;
    }

    public void register(ProviderRegisterRequest request) {
        if(Objects.isNull(request)) throw new RuntimeException("Requisição não pode ser nula");

        if(providerRepository.existByCnpj(request.cnpj())) throw new RuntimeException("Cnpj já cadastrado");

        Provider provider = new Provider(request.name(), request.cnpj());
        providerRepository.save(provider);
    }

    public List<ProviderGetResponse> getAll() {
        return providerRepository.getAll().stream().map(providerMapper::toGetResponse).toList();
    }
}
