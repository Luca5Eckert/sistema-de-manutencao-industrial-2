package sistema_industrial_weg.infra.dao;

import sistema_industrial_weg.infra.connection.ConnectionDatabase;
import sistema_industrial_weg.model.request.Request;
import sistema_industrial_weg.model.request.enumerator.RequestStatus;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public List<Request> getAllPendent() {
        List<Request> requestList = new ArrayList<>();
        String query = """
                SELECT
                    id
                    , setor
                    , dataSolicitacao
                    , status
                FROM requisicao
                WHERE status = ?
                """;


        try(Connection connection = ConnectionDatabase.toInstance();
            PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1, RequestStatus.PENDENTE.toString());

            try(ResultSet resultSet = statement.executeQuery()){

                while(resultSet.next()){
                    long id = resultSet.getLong("id");
                    String sector = resultSet.getString("setor");
                    LocalDate requestDate = resultSet.getDate("dataSolicitacao").toLocalDate();
                    String status = resultSet.getString("status");

                    Request request = new Request(id, sector, requestDate, RequestStatus.valueOf(status));
                    requestList.add(request);
                }

            }

        } catch (SQLException sqlException){
            throw new RuntimeException(sqlException.getMessage());
        }

        return requestList;

    }

    public boolean existById(long id) {
        String query = """
                SELECT 0
                FROM requisicao
                """;

        try(Connection connection = ConnectionDatabase.toInstance();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()){

            return resultSet.next();

        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }


    }

    public void changeStatus(long id, RequestStatus requestStatus) {
        String query = """
                UPDATE
                    requisicao
                SET
                    status = ?
                WHERE
                    id = ?
                """;

        try(Connection connection = ConnectionDatabase.toInstance();
            PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1, requestStatus.toString());
            statement.setLong(2, id);

            statement.executeUpdate();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
}
