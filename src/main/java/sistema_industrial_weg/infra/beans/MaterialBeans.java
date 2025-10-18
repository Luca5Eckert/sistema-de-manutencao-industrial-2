package sistema_industrial_weg.infra.beans;

import sistema_industrial_weg.infra.dao.MaterialDao;
import sistema_industrial_weg.repository.MaterialRepository;
import sistema_industrial_weg.service.material.MaterialMapper;
import sistema_industrial_weg.service.material.MaterialService;

public class MaterialBeans {

    private final static MaterialDao MATERIAL_DAO = new MaterialDao();
    private final static MaterialMapper MATERIAL_MAPPER = new MaterialMapper();
    private final static MaterialRepository MATERIAL_REPOSITORY = new MaterialRepository(MATERIAL_DAO);
    private final static MaterialService MATERIAL_SERVICE = new MaterialService(MATERIAL_REPOSITORY, MATERIAL_MAPPER);

    public static MaterialService toInstanceService(){
        return MATERIAL_SERVICE;
    }

    public static MaterialRepository toInstanceRepository() {
        return MATERIAL_REPOSITORY;
    }
}
