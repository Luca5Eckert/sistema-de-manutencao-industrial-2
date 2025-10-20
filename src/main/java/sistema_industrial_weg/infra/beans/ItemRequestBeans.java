package sistema_industrial_weg.infra.beans;

import sistema_industrial_weg.infra.dao.ItemRequestDao;
import sistema_industrial_weg.repository.ItemRequestRepository;

public class ItemRequestBeans {

    private final static ItemRequestDao ITEM_REQUEST_DAO = new ItemRequestDao();
    private final static ItemRequestRepository ITEM_REQUEST_REPOSITORY = new ItemRequestRepository(ITEM_REQUEST_DAO);

    public static ItemRequestRepository toInstanceRepository(){
        return ITEM_REQUEST_REPOSITORY;
    }

}
