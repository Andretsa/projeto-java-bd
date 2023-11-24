import java.util.Scanner;

public class Segurado extends Pessoa {
    private int idSegurado;
    private String numeroApolice;

    public Segurado() {

    }

    public Segurado(int idSegurado, String numeroApolice, int idPessoa, String cpf, String nomeCompleto, String telefone, String email) {
        // Chama o construtor da classe pai (Pessoa) que recebe parâmetros
        super(idPessoa, cpf, nomeCompleto, telefone, email);

        this.idSegurado = idSegurado;
        this.numeroApolice = numeroApolice;
    }


    public int getIdSegurado() {
        return idSegurado;
    }

    public void setIdSegurado(int idSegurado) {
        this.idSegurado = idSegurado;
    }

    public String getNumeroApolice() {
        return numeroApolice;
    }

    public void setNumeroApolice(String numeroApolice) {
        this.numeroApolice = numeroApolice;
    }

    public void solicitarAlteracao() {
        System.out.println("Escolha a alteração desejada:");
        System.out.println("1. Alterar nome");
        System.out.println("2. Alterar telefone");
        System.out.println("3. Alterar email");
        System.out.print("Opção: ");

        Scanner scanner = new Scanner(System.in);

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        switch (opcao) {
            case 1:
                System.out.print("Informe o novo nome: ");
                String novoNome = scanner.nextLine();
                setNomeCompleto(novoNome);
                break;
            case 2:
                System.out.print("Informe o novo telefone: ");
                String novoTelefone = scanner.nextLine();
                settelefone(novoTelefone);
                break;
            case 3:
                System.out.print("Informe o novo email: ");
                String novoEmail = scanner.nextLine();
                setEmail(novoEmail);
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }
}


