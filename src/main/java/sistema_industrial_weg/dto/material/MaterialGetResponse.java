package sistema_industrial_weg.dto.material;

public record MaterialGetResponse(long id, String name, String unit, double stock) {

    @Override
    public String toString() {
        return name + "  " + stock + " " + unit;
    }
}
