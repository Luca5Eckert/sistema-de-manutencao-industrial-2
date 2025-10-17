package sistema_industrial_weg.repository;

import sistema_industrial_weg.infra.dao.MaterialDao;
import sistema_industrial_weg.model.material.Material;

import java.util.List;

public class MaterialRepository {

    private final MaterialDao materialDao;

    public MaterialRepository(MaterialDao materialDao) {
        this.materialDao = materialDao;
    }

    public void save(Material material) {
        materialDao.save(material);
    }

    public boolean existByName(String name) {
        return materialDao.existByName(name);
    }

    public List<Material> getMaterials(List<Long> materialsId) {
        return materialDao.getMaterials(materialsId);
    }
}
