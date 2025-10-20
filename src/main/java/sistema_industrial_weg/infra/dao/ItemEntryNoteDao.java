package sistema_industrial_weg.infra.dao;

import sistema_industrial_weg.infra.connection.ConnectionDatabase;
import sistema_industrial_weg.model.item_entry_note.ItemEntryNote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ItemEntryNoteDao {
    public void createAll(List<ItemEntryNote> itensEntryNote, long id) {
        String query = """
                INSERT INTO notaentradaitem 
                (
                idNotaEntrada
                , idMaterial
                , quantidade
                )
                VALUES
                (?, ?, ?)
                """;

        try(Connection connection = ConnectionDatabase.toInstance();
            PreparedStatement statement = connection.prepareStatement(query)){


            for (ItemEntryNote itemEntryNote : itensEntryNote) {
                statement.setLong(1, id);
                statement.setLong(2, itemEntryNote.getItemEntryNoteId().getMaterialId());
                statement.setDouble(3, itemEntryNote.getQuantity());

                statement.addBatch();

            }

            statement.executeBatch();

        } catch (SQLException e) {
            throw new RuntimeException("Erro na conexão do banco de dados: " + e.getMessage());
        }

    }
}
