package sistema_industrial_weg.repository;

import sistema_industrial_weg.infra.dao.RequestDao;
import sistema_industrial_weg.model.request.Request;
import sistema_industrial_weg.model.request.enumerator.RequestStatus;

import java.util.List;

public class RequestRepository {

    private final RequestDao requestDao;

    public RequestRepository(RequestDao requestDao) {
        this.requestDao = requestDao;
    }

    public void save(Request request) {
        requestDao.save(request);
    }

    public List<Request> getAllPendent() {
        return requestDao.getAllPendent();
    }

    public boolean existById(long id) {
        return requestDao.existById(id);
    }

    public void changeStatus(long id, RequestStatus requestStatus) {
        requestDao.changeStatus(id, requestStatus);
    }
}
