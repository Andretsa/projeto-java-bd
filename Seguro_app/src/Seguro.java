import java.time.LocalDate;


public class Seguro {

    private int idSeguro;

    private LocalDate vigencia;

    private String status;

    private String numeroApolice;

    private Veiculo veiculo;
    private Segurado segurado;

    public int getIdSeguro() {
        return idSeguro;
    }

    public void setIdSeguro(int idSeguro) {
        this.idSeguro = idSeguro;
    }

    public LocalDate getVigencia() {
        return vigencia;
    }

    public void setVigencia(LocalDate vigencia) {
        this.vigencia = vigencia;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNumeroApolice() {
        return numeroApolice;
    }

    public void setNumeroApolice(String numeroApolice) {
        this.numeroApolice = numeroApolice;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Segurado getSegurado() {
        return segurado;
    }

    public void setSegurado(Segurado segurado) {
        this.segurado = segurado;
    }

    @Override
    public String toString() {
        return "Seguro{" +
                "idSeguro=" + idSeguro +
                ", vigencia=" + vigencia +
                ", status='" + status + '\'' +
                ", numeroApolice='" + numeroApolice + '\'' +
                ", idSegurado=" + segurado +
                ", idVeiculo=" + veiculo +
                '}';
    }
}
