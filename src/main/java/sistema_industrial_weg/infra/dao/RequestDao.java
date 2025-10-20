package sistema_industrial_weg.infra.dao;

import sistema_industrial_weg.infra.connection.ConnectionDatabase;
import sistema_industrial_weg.model.request.Request;

import java.sql.*;
import java.time.LocalDate;

public class RequestDao {

    public void save(Request request) {
        String query = """
                INSERT INTO requisicao
                (
                    setor,
                    dataSolicitacao,
                    status
                )
                VALUES
                (
                    ?,
                    ?,
                    ?
                )
                """;


        try(Connection connection = ConnectionDatabase.toInstance();
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            statement.setString(1, request.getSector());
            statement.setDate(2, Date.valueOf(request.getRequestDate()));
            statement.setString(3, request.getStatus().toString());

            statement.executeUpdate();

            try(ResultSet generatedKeys = statement.getGeneratedKeys()){
                if(generatedKeys.next()){
                    request.setId(generatedKeys.getLong(1));
                } else {
                    throw new RuntimeException("Erro ao obter id gerado");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
