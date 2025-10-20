package sistema_industrial_weg.dto.request.create;

public record RequestMaterialRequest(long idMaterial, String name, double quantity, String unit) {

    public String toString() {
        return name + " " + quantity;
    }

}
