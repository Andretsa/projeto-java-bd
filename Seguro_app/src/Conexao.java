import java.sql.*;


public class Conexao {

    public static Connection GeraConexao(){
        Connection conexao = null;
        try{
            String url = "jdbc:mysql://localhost/seguro_veiculo";
            String usuario ="root";
            String senha="root";
            conexao = DriverManager.getConnection(url,usuario,senha);
            System.out.println("Conexão realizada com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao tentar conectar: " + e.getMessage());
            conexao = null;
        }
        return conexao;
    }

    public static int buscarIdGerado(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        int generatedKey =0;
        if (rs.next()) {
            generatedKey = rs.getInt(1);
        }
        return generatedKey;
    }
}