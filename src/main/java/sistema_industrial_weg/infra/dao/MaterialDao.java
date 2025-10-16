package sistema_industrial_weg.infra.dao;

import sistema_industrial_weg.infra.connection.ConnectionDatabase;
import sistema_industrial_weg.model.material.Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialDao {

    public void save(Material material) {
        String query = """
                INSERT INTO material
                (nome, unidade, estoque)
                VALUES
                (?, ?, ?)
                """;

        try(Connection connection = ConnectionDatabase.toInstance();
            PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1, material.getName());
            statement.setString(2, material.getUnit());
            statement.setDouble(3, material.getStock());

            statement.executeUpdate();

        } catch (SQLException e){
            throw new RuntimeException("Erro na conexão do banco de dados: " + e.getMessage());
        }

    }

    public boolean existByName(String name) {
        String query = """
                SELECT 0
                FROM material
                WHERE nome = ?
                """;

        try(Connection connection = ConnectionDatabase.toInstance();
            PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, name);

            try(ResultSet resultSet = statement.executeQuery()){

                return resultSet.next();

            }

        } catch (SQLException e){
            throw new RuntimeException("Erro na conexão do banco de dados: " + e.getMessage());
        }

    }
}
