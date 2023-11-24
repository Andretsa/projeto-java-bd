public class Pessoa {
    private int idPessoa;
    private String cpf;
    private String nomeCompleto;
    private String whatsapp;
    private String email;

    public Pessoa ( ) {

    }
    public Pessoa(int idPessoa, String cpf, String nomeCompleto, String whatsapp, String email) {
        this.idPessoa = idPessoa;
        this.cpf = cpf;
        this.nomeCompleto = nomeCompleto;
        this.whatsapp = whatsapp;
        this.email = email;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

