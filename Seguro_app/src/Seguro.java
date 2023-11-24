import java.time.LocalDate;

public class Seguro {
    private int idSeguro;
    private LocalDate vigencia;
    private String situacao;
    private String apolice;
    private int idSegurado;
    private int idVeiculo;

    // Construtores
    public Seguro() {
        // Construtor vazio
    }

    public Seguro(int idSeguro, LocalDate vigencia, String situacao, String apolice, int idSegurado, int idVeiculo) {
        this.idSeguro = idSeguro;
        this.vigencia = vigencia;
        this.situacao = situacao;
        this.apolice = apolice;
        this.idSegurado = idSegurado;
        this.idVeiculo = idVeiculo;
    }

    // Getters e Setters...

    public int getIdSeguro() {
        return idSeguro;
    }

    public void setIdSeguro(int idSeguro) {
        this.idSeguro = idSeguro;
    }

    public LocalDate getVigencia() {
        return vigencia;
    }

    public void setVigencia( LocalDate vigencia) {
        this.vigencia = vigencia;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getApolice() {
        return apolice;
    }

    public void setApolice(String apolice) {
        this.apolice = apolice;
    }

    public int getIdSegurado() {
        return idSegurado;
    }

    public void setIdSegurado(int idSegurado) {
        this.idSegurado = idSegurado;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }
}
