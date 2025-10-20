package sistema_industrial_weg.infra.exception;

import java.sql.SQLException;

public class GlobalExceptionHandler {
    public static String handler(RuntimeException e) {
        return e.getMessage();
    }
}
