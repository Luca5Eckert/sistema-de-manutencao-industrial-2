package sistema_industrial_weg.infra.dao;

import sistema_industrial_weg.infra.connection.ConnectionDatabase;
import sistema_industrial_weg.model.item_request.ItemRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ItemRequestDao {


    public void saveAll(long requestId, List<ItemRequest> listItemRequest) {
        String query = """
                INSERT INTO requisicaoitem
                (
                    idRequisicao,
                    idMaterial,
                    quantidade
                )
                VALUES
                (
                    ?,
                    ?,
                    ?
                )
                """;

        try(Connection connection = ConnectionDatabase.toInstance();
            PreparedStatement statement = connection.prepareStatement(query)){

            for (ItemRequest itemRequest : listItemRequest) {

                statement.setLong(1, requestId);
                statement.setLong(2, itemRequest.getItemRequestId().getMaterialId());
                statement.setDouble(3, itemRequest.getQuantity());

                statement.addBatch();

            }

            statement.executeBatch();

        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }


    }
}
