package sistema_industrial_weg.repository;

import sistema_industrial_weg.infra.dao.RequestDao;
import sistema_industrial_weg.model.request.Request;

public class RequestRepository {

    private final RequestDao requestDao;

    public RequestRepository(RequestDao requestDao) {
        this.requestDao = requestDao;
    }

    public void save(Request request) {
        requestDao.save(request);
    }
}
