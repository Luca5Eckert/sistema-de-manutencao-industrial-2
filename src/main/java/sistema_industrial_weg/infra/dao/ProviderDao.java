package sistema_industrial_weg.infra.dao;

import sistema_industrial_weg.dto.provider.ProviderGetResponse;
import sistema_industrial_weg.infra.connection.ConnectionDatabase;
import sistema_industrial_weg.model.provider.Provider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProviderDao {

    public void save(Provider provider) {
        String query = """
                INSERT INTO fornecedor
                (nome, cnpj)
                VALUES
                (?, ?)
                """;

        try (Connection connection = ConnectionDatabase.toInstance();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, provider.getName());
            statement.setString(2, provider.getCnpj());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro na conexão do banco de dados: " + e.getMessage());
        }

    }

    public boolean existByCnpj(String cnpj) {
        String query = """
                SELECT 0
                FROM fornecedor
                WHERE cnpj = ?
                """;

        try (Connection connection = ConnectionDatabase.toInstance();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, cnpj);

            try (ResultSet resultSet = statement.executeQuery()) {

                return resultSet.next();

            }


        } catch (SQLException e) {
            throw new RuntimeException("Erro na conexão do banco de dados: " + e.getMessage());
        }

    }

    public boolean existById(long id) {
        String query = """
                SELECT 0
                FROM fornecedor
                WHERE id = ?
                """;

        try (Connection connection = ConnectionDatabase.toInstance();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                return resultSet.next();

            }


        } catch (SQLException e) {
            throw new RuntimeException("Erro na conexão do banco de dados: " + e.getMessage());
        }

    }

    public List<Provider> getAll() {
        List<Provider> providers = new ArrayList<>();
        String query = """
                SELECT
                id, nome, cnpj
                FROM
                fornecedor
                """;

        try(Connection connection = ConnectionDatabase.toInstance();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()
        ){

            while(resultSet.next()){
                long id = resultSet.getLong("id");
                String name = resultSet.getString("nome");
                String cnpj = resultSet.getString("cnpj");

                Provider provider = new Provider(id, name, cnpj);
                providers.add(provider);
            }


        } catch (SQLException e) {
            throw new RuntimeException("Erro na conexão do banco de dados: " + e.getMessage());
        }

        return providers;
    }
}
