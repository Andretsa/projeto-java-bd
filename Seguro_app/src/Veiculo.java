
public class Veiculo {

    private int idVeiculo;

    private String placa;

    private int ano;

    private String modelo;

    private String seguradora;
    private Segurado segurado; //fk

    public Veiculo(){

    }
    public Veiculo(int idVeiculo, String placa, int ano, String modelo, String seguradora) {
        this.idVeiculo = idVeiculo;
        this.placa = placa;
        this.ano = ano;
        this.modelo = modelo;
        this.seguradora = seguradora;
    }

    public Veiculo(String placa, int ano, String modelo, String seguradora) {
        this.placa = placa;
        this.ano = ano;
        this.modelo = modelo;
        this.seguradora = seguradora;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSeguradora() {
        return seguradora;
    }

    public void setSeguradora(String seguradora) {
        this.seguradora = seguradora;
    }

    public Segurado getSegurado() {
        return segurado;
    }

    public void setSegurado(Segurado segurado) {
        this.segurado = segurado;
    }
}
