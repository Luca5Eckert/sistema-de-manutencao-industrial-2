package sistema_industrial_weg.infra.dao;

import sistema_industrial_weg.infra.connection.ConnectionDatabase;
import sistema_industrial_weg.model.material.Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Material> getMaterials(List<Long> materialsId) {
        List<Material> materials = new ArrayList<>();

        StringBuilder queryBuilder = new StringBuilder("""
                SELECT id, nome, unidade, estoque
                WHERE id IN (
                """);
        queryBuilder.append("?, ".repeat(Math.max(0, materialsId.size() - 1)));
        queryBuilder.append(" ?");

        String query = queryBuilder + " )";

        try(Connection connection = ConnectionDatabase.toInstance();
            PreparedStatement statement = connection.prepareStatement(query)){

            for(int i = 0; i < materialsId.size(); i++){
                statement.setLong(i, materialsId.get(i));
            }

            try(ResultSet resultSet = statement.executeQuery()){

                while(resultSet.next()){
                    long id = resultSet.getLong("id");
                    String nome = resultSet.getString("nome");
                    String unidade = resultSet.getString("unidade");
                    double estoque = resultSet.getDouble("estoque");

                    var material = new Material(id, nome, unidade, estoque);
                    materials.add(material);
                }

            }

        } catch (SQLException e){
            throw new RuntimeException("Erro na conexão do banco de dados: " + e.getMessage());
        }

        return materials;

    }


}
