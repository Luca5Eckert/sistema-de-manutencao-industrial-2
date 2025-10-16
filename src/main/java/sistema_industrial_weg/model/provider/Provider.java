package sistema_industrial_weg.model.provider;

public class Provider {

    private final long id;

    private String nome;
    private String cnpj;

    public Provider(String nome, String cnpj) {
        this.id = -1;
        this.nome = nome;
        this.cnpj = cnpj;
    }

    public Provider(long id, String nome, String cnpj) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

}
