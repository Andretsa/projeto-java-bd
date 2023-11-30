import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class PessoaDAO {
    private Connection connection;

    public Connection getConnection() throws Exception {
        if (connection == null) {
            connection = new Conexao().GeraConexao();
        }
        return connection;
    }

    public PessoaDAO() {
        this.connection = Conexao.GeraConexao();
    }

    public int adicionar(Pessoa pessoa) {
        String sql = "INSERT INTO Pessoa(cpf, nomeCompleto, telefone, email) VALUES(?,?,?,?)";
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, pessoa.getCpf());
            stmt.setString(2, pessoa.getNomeCompleto());
            stmt.setString(3, pessoa.getTelefone());
            stmt.setString(4, pessoa.getEmail());
            stmt.execute();

            int idGerado = Conexao.buscarIdGerado(stmt);
            stmt.close();
            return idGerado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Pessoa> listar() {
        List<Pessoa> pessoas = new ArrayList<>();
        String sql = "SELECT idPessoa, cpf, nomeCompleto, telefone, email FROM pessoa";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setIdPessoa(resultSet.getInt("idPessoa"));
                pessoa.setCpf(resultSet.getString("cpf"));
                pessoa.setNomeCompleto(resultSet.getString("nomeCompleto"));
                pessoa.setTelefone(resultSet.getString("telefone"));
                pessoa.setEmail(resultSet.getString("email"));
                pessoas.add(pessoa);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar pessoas: " + e.getMessage(), e);
        }
        return pessoas;
    }

    public Pessoa buscarPorId(int idPessoa) {
        String sql = "SELECT idPessoa, cpf, nomeCompleto, telefone, email FROM pessoa WHERE idPessoa = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPessoa);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    Pessoa pessoa = new Pessoa();
                    pessoa.setIdPessoa(resultSet.getInt("idPessoa"));
                    pessoa.setNomeCompleto(resultSet.getString("nomeCompleto"));
                    pessoa.setCpf(resultSet.getString("cpf"));
                    pessoa.setTelefone(resultSet.getString("telefone"));
                    pessoa.setEmail(resultSet.getString("email"));
                    return pessoa;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pessoa por ID: " + e.getMessage(), e);
        }
        return null;
    }
    private List<Integer> obterIdsAdministradoresCadastrados() {
        List<Integer> idsAdministradores = new ArrayList<>();
        String sql = "SELECT idAdministrador FROM Administrador";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int idAdministrador = resultSet.getInt("idAdministrador");
                idsAdministradores.add(idAdministrador);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter IDs de administradores: " + e.getMessage(), e);
        }

        return idsAdministradores;
    }

    private List<Integer> obterIdsPessoasTipoSegurado() {
        List<Integer> idsPessoas = new ArrayList<>();
        String sql = "SELECT idPessoa FROM PessoaTipoSegurado";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int idPessoa = resultSet.getInt("idPessoa");
                idsPessoas.add(idPessoa);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter IDs de pessoas tipo segurado: " + e.getMessage(), e);
        }

        return idsPessoas;
    }

    public void atualizar(Pessoa pessoa) {
        String sql = "UPDATE pessoa SET nomeCompleto = ?, telefone = ?, email = ? WHERE idPessoa = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, pessoa.getNomeCompleto());
            stmt.setString(2, pessoa.getTelefone());
            stmt.setString(3, pessoa.getEmail());
            stmt.setInt(4, pessoa.getIdPessoa());
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Pessoa atualizada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Administrador getAdministradorByIdPessoa(int idPessoa) {
        String sql = "SELECT idAdministrador FROM administrador WHERE idPessoa = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPessoa);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    Administrador administrador = new Administrador();
                    administrador.setIdAdministrador(resultSet.getInt("idAdministrador"));
                    administrador.setIdPessoa(idPessoa); // Associando à Pessoa correspondente
                    return administrador;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar administrador por ID de Pessoa: " + e.getMessage(), e);
        }
        return null;
    }

    public Segurado getSeguradoByIdPessoa(int idPessoa) {
        String sql = "SELECT idSegurado FROM segurado WHERE idPessoa = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPessoa);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    Segurado segurado = new Segurado();
                    segurado.setIdSegurado(resultSet.getInt("idSegurado"));
                    segurado.setIdPessoa(idPessoa); // Associando à Pessoa correspondente
                    return segurado;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar segurado por ID de Pessoa: " + e.getMessage(), e);
        }
        return null;
    }

    public void adicionarSegurado(Pessoa pessoa) {
        int idPessoa = adicionar(pessoa);
        int idAdministrador = sortearIdAdministrador();
        String sql = "INSERT INTO Segurado(idPessoa, idAdministrador) VALUES(?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPessoa);
            stmt.setInt(2, idAdministrador);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar segurado: " + e.getMessage(), e);
        }
    }

    private int sortearIdAdministrador() {
        List<Integer> listaIdsAdministradores = obterIdsAdministradoresCadastrados();
        if (listaIdsAdministradores.isEmpty()) {
            throw new RuntimeException("Não há administradores cadastrados para selecionar aleatoriamente.");
        }
        Random random = new Random();
        int index = random.nextInt(listaIdsAdministradores.size());
        return listaIdsAdministradores.get(index);
    }
    public void adicionarAdministrador(Pessoa pessoa) {
        int idPessoa = adicionar(pessoa);
        String sql = "INSERT INTO Administrador(idPessoa) VALUES(?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPessoa);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar administrador: " + e.getMessage(), e);
        }
    }
    public void excluirPessoa(int idPessoa) {
        String sql = "DELETE FROM Pessoa WHERE idPessoa = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPessoa);
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Pessoa excluída com sucesso.");
            } else {
                System.out.println("Nenhuma pessoa encontrada com o ID " + idPessoa);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir pessoa: " + e.getMessage(), e);
        }
    }
}

