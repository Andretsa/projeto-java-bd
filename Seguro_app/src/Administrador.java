
public class Administrador extends Pessoa{
    private int idAdministrador;

    public Administrador(){

    }
    public Administrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public int getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public int getIdPessoa() {
        return super.getIdPessoa();
    }
}
