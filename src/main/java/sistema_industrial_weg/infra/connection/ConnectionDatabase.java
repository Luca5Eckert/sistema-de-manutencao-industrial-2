package sistema_industrial_weg.infra.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDatabase {

    private static final String URL = "jdbc:mysql://localhost:3306/sistema_de_manutencao_industrial_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection toInstance() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) throws SQLException {
        toInstance();
    }

}