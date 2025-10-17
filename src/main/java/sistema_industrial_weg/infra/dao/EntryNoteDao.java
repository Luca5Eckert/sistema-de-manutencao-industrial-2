package sistema_industrial_weg.infra.dao;

import sistema_industrial_weg.infra.connection.ConnectionDatabase;
import sistema_industrial_weg.model.entry_note.EntryNote;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.ResultSet;
import java.sql.Statement;

public class EntryNoteDao {

    public void create(EntryNote entryNote) {
        String query = """
            INSERT INTO notaentrada
            (idFornecedor, dataEntrada)
            VALUES
            (?, ?)
            """;

        try (Connection connection = ConnectionDatabase.toInstance();
             PreparedStatement statement = connection.prepareStatement(
                     query,
                     Statement.RETURN_GENERATED_KEYS
             )) {

            statement.setLong(1, entryNote.getProviderId());
            statement.setDate(2, Date.valueOf(entryNote.getEntryDate()));

            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long generatedId = generatedKeys.getLong(1);
                    entryNote.setId(generatedId);
                } else {
                    throw new SQLException("Falha ao obter o ID gerado.");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

