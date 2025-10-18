package sistema_industrial_weg.service.material;

import sistema_industrial_weg.dto.material.MaterialGetResponse;
import sistema_industrial_weg.model.material.Material;

public class MaterialMapper {
    public MaterialGetResponse toGetResponse(Material material) {
        return new MaterialGetResponse(material.getId(), material.getName(), material.getUnit(), material.getStock());
    }
}
