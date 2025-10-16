package sistema_industrial_weg.model.material;

public class Material {

    private final long id;

    private String nome;
    private String unidade;
    private double estoque;

    public Material(double estoque, String unidade, String nome) {
        this.id = -1;
        this.estoque = estoque;
        this.unidade = unidade;
        this.nome = nome;
    }

    public Material(long id, String nome, String unidade, double estoque) {
        this.id = id;
        this.nome = nome;
        this.unidade = unidade;
        this.estoque = estoque;
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

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public double getEstoque() {
        return estoque;
    }

    public void setEstoque(double estoque) {
        this.estoque = estoque;
    }
}
