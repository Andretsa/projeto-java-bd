import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Veiculo {

    private int idVeiculo;

    private String placa;

    private int ano;

    private String modelo;

    private String status;

    private String seguradora;

    private int idPessoa;


    public Veiculo(String placa, int ano, String modelo, String status, String seguradora) {
        this.idVeiculo = idVeiculo;
        this.placa = placa;
        this.ano = ano;
        this.modelo = modelo;
        this.status = status;
        this.seguradora = seguradora;
    }

}
