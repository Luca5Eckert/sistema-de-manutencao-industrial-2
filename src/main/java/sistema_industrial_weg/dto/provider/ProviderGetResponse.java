package sistema_industrial_weg.dto.provider;

public record ProviderGetResponse(long id, String name, String cnpj) {

    @Override
    public String toString() {
        return name + "  " + cnpj;
    }
}
