import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;


public class SeguroDAO {
    private Connection connection;

    public SeguroDAO() {
        this.connection = new Conexao().GeraConexao();
    }

    public void adicionarSeguro(Seguro seguro, int idVeiculo, int idSegurado) throws SQLException {
        String sql = "INSERT INTO Seguro (vigencia, status, numeroApolice, idVeiculo, idSegurado) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            LocalDate seguroVigencia = seguro.getVigencia();
            if (seguroVigencia != null) {
                stmt.setDate(1, java.sql.Date.valueOf(seguroVigencia));
                stmt.setString(2, seguro.getStatus());
                stmt.setString(3, seguro.getNumeroApolice());
                stmt.setInt(4, idVeiculo);
                stmt.setInt(5, idSegurado);

                stmt.execute();
            } else {
                throw new IllegalArgumentException("Vigência do seguro não pode ser nula");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar seguro: " + e.getMessage(), e);
        }
    }
}