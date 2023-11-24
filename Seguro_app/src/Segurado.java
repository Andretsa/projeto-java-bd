public class Segurado extends Pessoa {
    private int idSegurado;
    private int numeroApolice;

    public Segurado() {
        super();
    }

    public Segurado(int idSegurado, int numeroApolice, int idPessoa, String cpf, String nomeCompleto, String whatsapp, String email) {
        // Chama o construtor da classe pai (Pessoa) que recebe parâmetros
        super(idPessoa, cpf, nomeCompleto, whatsapp, email);

        this.idSegurado = idSegurado;
        this.numeroApolice = numeroApolice;
    }

    // Adicionando o getter e setter para o campo id
    public int getId() {
        return idSegurado;
    }

    public void setId(int id) {
        this.idSegurado = id;
    }

    public int getNumeroApolice() {
        return numeroApolice;
    }

    public void setNumeroApolice(int numeroApolice) {
        this.numeroApolice = numeroApolice;
    }
}


