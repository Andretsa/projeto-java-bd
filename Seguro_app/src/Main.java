import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaDAO pessoaDAO = new PessoaDAO();
        VeiculoDAO veiculoDAO = new VeiculoDAO();

        System.out.println("Bem-vindo! Selecione uma opção:");
        int opcao;

        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            try {
                switch (opcao) {
                    case 1:
                        adicionarPessoa(pessoaDAO, scanner);
                        break;
                    case 2:
                        adicionarVeiculo(veiculoDAO, pessoaDAO, scanner);
                        break;
                    case 3:
                        listarPessoas(pessoaDAO);
                        break;
                    case 4:
                        buscarPessoaPorId(pessoaDAO, scanner);
                        break;
                    case 5:
                        atualizarPessoa(pessoaDAO, scanner);
                        break;
                    case 6:
                        solicitarAlteracaoSegurado(pessoaDAO, scanner);
                        break;
                    case 0:
                        System.out.println("Encerrando o programa. Até mais!");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } while (opcao != 0);
    }

    private static void exibirMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Adicionar Pessoa");
        System.out.println("2. Adicionar Veículo");
        System.out.println("3. Listar Pessoas");
        System.out.println("4. Buscar Pessoa por ID");
        System.out.println("5. Atualizar Pessoa");
        System.out.println("6. Solicitar Alteração (Segurado)");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void adicionarPessoa(PessoaDAO pessoaDAO, Scanner scanner) throws SQLException {
        System.out.println("\nAdicionar Pessoa:");
        System.out.print("Informe o CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Informe o nome completo: ");
        String nomeCompleto = scanner.nextLine();
        System.out.print("Informe o número de telefone : ");
        String telefone = scanner.nextLine();
        System.out.print("Informe o email: ");
        String email = scanner.nextLine();

        Pessoa novaPessoa = new Pessoa( cpf, nomeCompleto, telefone, email);
        pessoaDAO.adiciona(novaPessoa);

        System.out.println("Pessoa cadastrada com sucesso.");
    }

    private static void adicionarVeiculo(VeiculoDAO veiculoDAO, PessoaDAO pessoaDAO, Scanner scanner) throws SQLException {
        System.out.println("\nAdicionar Veículo:");
        System.out.print("Informe a placa: ");
        String placa = scanner.nextLine();
        System.out.print("Informe o ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner
        System.out.print("Informe o modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Informe o status: ");
        String status = scanner.nextLine();
        System.out.print("Informe a seguradora: ");
        String seguradora = scanner.nextLine();

        listarPessoas(pessoaDAO);
        System.out.print("Informe o ID da Pessoa a associar o veículo: ");
        int idPessoa = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        Veiculo novoVeiculo = new Veiculo(placa, ano, modelo, status, seguradora);
        novoVeiculo.setIdPessoa(String.valueOf(idPessoa));
        veiculoDAO.salvarVeiculo(novoVeiculo);

        System.out.println("Veículo cadastrado com sucesso.");
    }

    private static void listarPessoas(PessoaDAO pessoaDAO) throws SQLException {
        System.out.println("\nLista de Pessoas:");
        List<Pessoa> pessoas = pessoaDAO.listar();
        for (Pessoa pessoa : pessoas) {
            System.out.println(pessoa);
        }
    }

    private static void buscarPessoaPorId(PessoaDAO pessoaDAO, Scanner scanner) throws SQLException {
        System.out.print("Informe o ID da Pessoa a buscar: ");
        int idPessoa = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        Pessoa pessoaEncontrada = pessoaDAO.buscarPorId(idPessoa);
        if (pessoaEncontrada != null) {
            System.out.println("Pessoa encontrada: " + pessoaEncontrada);
        } else {
            System.out.println("Pessoa não encontrada.");
        }
    }

    private static void atualizarPessoa(PessoaDAO pessoaDAO, Scanner scanner) throws SQLException {
        System.out.println("\nAtualizar Pessoa:");
        System.out.print("Informe o ID da Pessoa a atualizar: ");
        int idPessoa = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        Pessoa pessoaExistente = pessoaDAO.buscarPorId(idPessoa);

        if (pessoaExistente != null) {
            System.out.print("Informe o novo nome completo: ");
            String novoNome = scanner.nextLine();
            System.out.print("Informe o novo número de : ");
            String novo = scanner.nextLine();
            System.out.print("Informe o novo email: ");
            String novoEmail = scanner.nextLine();

            Pessoa pessoaAtualizada = new Pessoa(idPessoa, pessoaExistente.getCpf(), novoNome, novo, novoEmail);
            pessoaDAO.atualizar(pessoaAtualizada);

            System.out.println("Pessoa atualizada com sucesso.");
        } else {
            System.out.println("Pessoa não encontrada.");
        }
    }

    private static void solicitarAlteracaoSegurado(PessoaDAO pessoaDAO, Scanner scanner) throws SQLException {
        System.out.print("Informe o ID do Segurado que deseja solicitar alteração: ");
        int idSegurado = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        Segurado segurado = (Segurado) pessoaDAO.buscarPorId(idSegurado);

        if (segurado != null) {
            System.out.print("Informe a alteração desejada: ");
            String alteracao = scanner.nextLine();
            segurado.solicitarAlteracao();
            System.out.println("Solicitação de alteração enviada com sucesso.");
        } else {
            System.out.println("Segurado não encontrado.");
        }
    }
}
