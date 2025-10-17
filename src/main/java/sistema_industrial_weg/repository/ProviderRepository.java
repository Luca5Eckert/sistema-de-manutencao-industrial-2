package sistema_industrial_weg.repository;

import sistema_industrial_weg.dto.provider.ProviderGetResponse;
import sistema_industrial_weg.infra.dao.ProviderDao;
import sistema_industrial_weg.model.provider.Provider;

import java.util.List;

public class ProviderRepository {

    private final ProviderDao providerDao;

    public ProviderRepository(ProviderDao providerDao) {
        this.providerDao = providerDao;
    }

    public void save(Provider provider) {
        providerDao.save(provider);
    }

    public boolean existByCnpj(String cnpj) {
        return providerDao.existByCnpj(cnpj);
    }

    public boolean existById(long id) {
        return providerDao.existById(id);
    }

    public List<ProviderGetResponse> getAll() {
        return providerDao.getAll();
    }
}
