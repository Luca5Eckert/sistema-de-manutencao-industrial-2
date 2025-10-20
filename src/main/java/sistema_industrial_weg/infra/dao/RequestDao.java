package sistema_industrial_weg.infra.dao;

import sistema_industrial_weg.infra.connection.ConnectionDatabase;
import sistema_industrial_weg.model.item_request.ItemRequest;
import sistema_industrial_weg.model.item_request.ItemRequestId;
import sistema_industrial_weg.model.material.Material;
import sistema_industrial_weg.model.request.Request;
import sistema_industrial_weg.model.request.enumerator.RequestStatus;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<ItemRequest, Material> getItemsAndMaterials(long requisitionId) {
        Map<ItemRequest, Material> map = new HashMap<>();

        String query = """
                SELECT
                    ir.idRequisicao,
                    ir.idMaterial,
                    ir.quantidade,
                    m.id AS mid,
                    m.nome,
                    m.unidade,
                    m.estoque
                FROM
                    Requisicao r
                JOIN
                    RequisicaoItem ir ON ir.idRequisicao = r.id
                JOIN
                    Material m ON m.id = ir.idMaterial
                WHERE
                    r.id = ?
                """;

        try (Connection connection = ConnectionDatabase.toInstance();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, requisitionId);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    long idReq = rs.getLong("idRequisicao");
                    long idMat = rs.getLong("idMaterial");
                    double quantidade = rs.getDouble("quantidade");

                    long matId = rs.getLong("mid");
                    String nome = rs.getString("nome");
                    String unidade = rs.getString("unidade");
                    double estoque = rs.getDouble("estoque");

                    ItemRequest item = new ItemRequest(new ItemRequestId(idReq, idMat), quantidade);
                    Material material = new Material(matId, nome, unidade, estoque);

                    map.put(item, material);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return map;
    }

}
