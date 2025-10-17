package sistema_industrial_weg.infra.beans;

import sistema_industrial_weg.infra.dao.ProviderDao;
import sistema_industrial_weg.repository.ProviderRepository;
import sistema_industrial_weg.service.provider.ProviderMapper;
import sistema_industrial_weg.service.provider.ProviderService;

public class ProviderBeans {

    private final static ProviderDao PROVIDER_DAO = new ProviderDao();
    private final static ProviderMapper PROVIDER_MAPPER = new ProviderMapper();
    private final static ProviderRepository PROVIDER_REPOSITORY = new ProviderRepository(PROVIDER_DAO);
    private final static ProviderService PROVIDER_SERVICE = new ProviderService(PROVIDER_REPOSITORY, PROVIDER_MAPPER);

    public static ProviderService toInstanceService(){
        return PROVIDER_SERVICE;
    }

    public static ProviderRepository toInstanceRepository() {
        return PROVIDER_REPOSITORY;
    }
}
