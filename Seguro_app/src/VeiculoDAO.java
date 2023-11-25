import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {
    private Connection connection;

    public VeiculoDAO() {
        this.connection = new Conexao().GeraConexao();
    }

    public void salvarVeiculo(Veiculo veiculo) throws SQLException {
        // Verifica se o veículo já existe no banco de dados
        Veiculo existente = buscarVeiculoPorId(veiculo.getIdVeiculo());

        if (existente != null) {
            // Se existir, realiza uma atualização
            atualizarVeiculo(veiculo);
        } else {
            // Se não existir, realiza uma inserção
            inserirVeiculo(veiculo);
        }
    }

    public Veiculo buscarVeiculoPorId(int idVeiculo) throws SQLException {
        String query = "SELECT * FROM Veiculo WHERE idVeiculo = ?";
        Veiculo veiculo = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idVeiculo);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    veiculo = criarVeiculoAPartirDoResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return veiculo;
    }

    public List<Veiculo> buscarTodosVeiculos() throws SQLException {
        String query = "SELECT * FROM Veiculo";
        List<Veiculo> veiculos = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Veiculo veiculo = criarVeiculoAPartirDoResultSet(resultSet);
                veiculos.add(veiculo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return veiculos;
    }

    public void deletarVeiculo(int idVeiculo) throws SQLException {
        String query = "DELETE FROM Veiculo WHERE idVeiculo = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idVeiculo);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void inserirVeiculo(Veiculo veiculo) throws SQLException {
        String sql = "INSERT INTO Veiculo (placa, ano, modelo, status, seguradora) VALUES (?, ?, ?, ?, ?)";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, veiculo.getPlaca());
            preparedStatement.setInt(2, veiculo.getAno());
            preparedStatement.setString(3, veiculo.getModelo());
            preparedStatement.setString(4, veiculo.getStatus());
            preparedStatement.setString(5, veiculo.getSeguradora());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // excecoes
            e.printStackTrace();
            throw e;
        }
    }

    private void atualizarVeiculo(Veiculo veiculo) throws SQLException {
        String query = "UPDATE Veiculo SET placa = ?, ano = ?, modelo = ?, status = ?, seguradora = ? WHERE idVeiculo = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, veiculo.getPlaca());
            preparedStatement.setInt(2, veiculo.getAno());
            preparedStatement.setString(3, veiculo.getModelo());
            preparedStatement.setString(4, veiculo.getStatus());
            preparedStatement.setString(5, veiculo.getSeguradora());
            preparedStatement.setInt(6, veiculo.getIdVeiculo());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private Veiculo criarVeiculoAPartirDoResultSet(ResultSet resultSet) throws SQLException {
        Veiculo veiculo = new Veiculo();
        veiculo.setIdVeiculo(resultSet.getInt("idVeiculo"));
        veiculo.setPlaca(resultSet.getString("placa"));
        veiculo.setAno(resultSet.getInt("ano"));
        veiculo.setModelo(resultSet.getString("modelo"));
        veiculo.setStatus(resultSet.getString("status"));
        veiculo.setSeguradora(resultSet.getString("seguradora"));
        return veiculo;
    }
}
