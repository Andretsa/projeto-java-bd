import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Veiculo {
    private int idVeiculo;
    private String placa;
    private int ano;
    private String modelo;
    private String status;
    private String seguradora;
    private String idPessoa;

    public Veiculo(){

    }

    public Veiculo(String placa, int ano, String modelo, String status, String seguradora) {
        this.idVeiculo = idVeiculo;
        this.placa = placa;
        this.ano = ano;
        this.modelo = modelo;
        this.status = status;
        this.seguradora = seguradora;
    }

    public Veiculo(int idVeiculo, String placa, int ano, String modelo, String status, String seguradora, String idPessoa) {
        this.idVeiculo = idVeiculo;
        this.placa = placa;
        this.ano = ano;
        this.modelo = modelo;
        this.status = status;
        this.seguradora = seguradora;
        this.idPessoa = idPessoa;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSeguradora() {
        return seguradora;
    }

    public void setSeguradora(String seguradora) {
        this.seguradora = seguradora;
    }

    public String getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(String idPessoa) {
        this.idPessoa = idPessoa;
    }
}
