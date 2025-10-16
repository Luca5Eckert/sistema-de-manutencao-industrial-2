package sistema_industrial_weg.infra.beans;

import sistema_industrial_weg.service.provider.ProviderService;

public class ProviderBeans {

    private final static ProviderService PROVIDER_SERVICE = new ProviderService();

    public static ProviderService toInstanceService(){
        return PROVIDER_SERVICE;
    }

}
