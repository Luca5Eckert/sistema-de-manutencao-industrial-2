package sistema_industrial_weg.model.provider;

public class Provider {

    private final long id;

    private String name;
    private String cnpj;

    public Provider(String name, String cnpj) {
        this.id = -1;
        this.name = name;
        this.cnpj = cnpj;
    }

    public Provider(long id, String name, String cnpj) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setNome(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

}
