package sistema_industrial_weg.infra.beans;

import sistema_industrial_weg.infra.dao.RequestDao;
import sistema_industrial_weg.repository.RequestRepository;
import sistema_industrial_weg.service.request.RequestMapper;
import sistema_industrial_weg.service.request.RequestService;

public class RequestBeans {

    private final static RequestDao REQUEST_DAO = new RequestDao();
    private final static RequestMapper REQUEST_MAPPER = new RequestMapper();
    private final static RequestRepository REQUEST_REPOSITORY = new RequestRepository(REQUEST_DAO);
    private final static RequestService REQUEST_SERVICE = new RequestService(REQUEST_REPOSITORY, ItemRequestBeans.toInstanceRepository(), REQUEST_MAPPER, MaterialBeans.toInstanceRepository());

    public static RequestService toInstanceRequest(){
        return REQUEST_SERVICE;
    }



}
