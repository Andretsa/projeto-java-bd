public class Administrador extends Pessoa {
    private int idAdministrador;

    public Administrador() {
    }

    public Administrador(int idAdministrador, int idPessoa, String cpf, String nomeCompleto, String telefone, String email) {
        // Chama o construtor da classe pai (Pessoa) que recebe parâmetros
        super(idPessoa, cpf, nomeCompleto, telefone, email);

        this.idAdministrador = idAdministrador;
    }

    public int getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }
}
