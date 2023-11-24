import java.time.LocalDate;

public class Seguro {
    private int idSeguro;
    private LocalDate vigencia;
    private String status;
    private String numeroApolice;
    private int idSegurado;
    private int idVeiculo;
    
    public Seguro() {
        
    }

    public Seguro(int idSeguro, LocalDate vigencia, String status, String numeroApolice, int idSegurado, int idVeiculo) {
        this.idSeguro = idSeguro;
        this.vigencia = vigencia;
        this.status = status;
        this.numeroApolice = numeroApolice;
        this.idSegurado = idSegurado;
        this.idVeiculo = idVeiculo;
    }

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

    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status = status;
    }

    public String getNumeroApolice() {
        return numeroApolice;
    }

    public void setApolice(String apolice) {
        this.numeroApolice = numeroApolice;
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
