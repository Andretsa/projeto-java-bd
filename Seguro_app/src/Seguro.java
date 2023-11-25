import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seguro {

    private int idSeguro;

    private LocalDate vigencia;

    private String status;

    private String numeroApolice;

    private int idSegurado;

    private int idVeiculo;

    @Override
    public String toString() {
        return "Seguro{" +
                "idSeguro=" + idSeguro +
                ", vigencia=" + vigencia +
                ", status='" + status + '\'' +
                ", numeroApolice='" + numeroApolice + '\'' +
                ", idSegurado=" + idSegurado +
                ", idVeiculo=" + idVeiculo +
                '}';
    }

}
