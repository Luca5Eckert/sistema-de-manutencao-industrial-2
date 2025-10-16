package sistema_industrial_weg.model.material;

public class Material {

    private final long id;

    private String name;
    private String unit;
    private double stock;

    public Material(String name, String unit, double stock) {
        this.id = -1;
        this.name = name;
        this.unit = unit;
        this.stock = stock;
    }

    public Material(long id, String name, String unit, double stock) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.stock = stock;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }
}
