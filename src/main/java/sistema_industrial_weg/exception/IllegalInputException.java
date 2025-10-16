package sistema_industrial_weg.exception;

public class IllegalInputException extends RuntimeException {

    public IllegalInputException(String typeInput) {
        super("Entrada invalida. Insira um " + typeInput);
    }
}
