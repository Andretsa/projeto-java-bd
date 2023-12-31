import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaDAO pessoaDAO = new PessoaDAO();
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        SeguroDAO seguroDAO = new SeguroDAO();

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
                        adicionarSeguro(seguroDAO, scanner);
                        break;
                    case 0:
                        System.out.println("Encerrando o programa. Até mais!");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (SQLException e) {
                System.out.println("Erro no banco de dados: " + e.getMessage());
            }

        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Adicionar Pessoa");
        System.out.println("2. Adicionar Veículo");
        System.out.println("3. Listar Pessoas");
        System.out.println("4. Buscar Pessoa por ID");
        System.out.println("5. Atualizar Pessoa");
        System.out.println("6. Adicionar Seguro");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void adicionarPessoa(PessoaDAO pessoaDAO, Scanner scanner) throws SQLException {
        System.out.println("\nAdicionar Pessoa:");
        System.out.print("Informe o CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Informe o nome completo: ");
        String nomeCompleto = scanner.nextLine();
        System.out.print("Informe o número de telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Informe o email: ");
        String email = scanner.nextLine();

        System.out.print("Informe o tipo de pessoa (segurado ou administrador): ");
        String tipoPessoa = scanner.nextLine();

        Pessoa novaPessoa = new Pessoa(cpf, nomeCompleto, telefone, email);

        if (tipoPessoa.equalsIgnoreCase("administrador")) {
            pessoaDAO.adicionarAdministrador(novaPessoa);
            System.out.println("Administrador adiciionado com sucesso.");
        } else if (tipoPessoa.equalsIgnoreCase("segurado")) {
            pessoaDAO.adicionarSegurado(novaPessoa);
            System.out.println("Segurado adicionado com sucesso.");
        }else{
            System.out.println("Pessoa não cadastrada.");
        }
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
        System.out.print("Informe a seguradora: ");
        String seguradora = scanner.nextLine();

        System.out.print("Informe o ID da Pessoa a associar o veículo: ");
        int idPessoa = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        Pessoa pessoaAssociada = pessoaDAO.buscarPorId(idPessoa);

        if(pessoaAssociada != null){
            Veiculo novoVeiculo = new Veiculo(placa, ano, modelo,seguradora);
            // Obtém o Segurado associado à Pessoa
            Segurado segurado = pessoaDAO.getSeguradoByIdPessoa(idPessoa);
            if(segurado != null) {
                novoVeiculo.setSegurado(segurado);
                veiculoDAO.inserirVeiculo(novoVeiculo, segurado.getIdSegurado());

                System.out.println("Veículo cadastrado com sucesso e assosciado ao segurado.");
            }else {
                System.out.println("Erro: A pessoa não é um segurado.");
            }
        } else {
            System.out.println("Erro: Pessoa não encontrada.");
        }
    }

    private static void listarPessoas(PessoaDAO pessoaDAO) throws SQLException {
        System.out.println("\nLista de Pessoas:");
        List<Pessoa> pessoas = pessoaDAO.listar();
        for (Pessoa pessoa : pessoas) {
            System.out.println(pessoa);
        }
    }

    private static void buscarPessoaPorId(PessoaDAO pessoaDAO, Scanner scanner) throws SQLException {
        try {
            System.out.print("Informe o ID da Pessoa a buscar: ");
            int idPessoa = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            Pessoa pessoaEncontrada = pessoaDAO.buscarPorId(idPessoa);

            if (pessoaEncontrada != null) {
                System.out.println(pessoaEncontrada);
            } else {
                System.out.println("Pessoa não encontrada.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Certifique-se de inserir um número como ID.");
            scanner.nextLine(); // Limpar o buffer do scanner em caso de entrada inválida
        }
    }

    private static void atualizarPessoa(PessoaDAO pessoaDAO, Scanner scanner) throws SQLException {
        System.out.println("\nAtualizar Pessoa:");
        System.out.print("Informe o ID da Pessoa a atualizar: ");
        int idPessoa = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        Pessoa pessoaExistente = pessoaDAO.buscarPorId(idPessoa);

        if (pessoaExistente != null) {
            System.out.println("Informações atuais da pessoa:");
            System.out.println(pessoaExistente);

            System.out.print("Informe o novo nome completo: ");
            String novoNome = scanner.nextLine();
            System.out.print("Informe o novo número de telefone: ");
            String novoTelefone = scanner.nextLine();
            System.out.print("Informe o novo email: ");
            String novoEmail = scanner.nextLine();

            pessoaExistente.setNomeCompleto(novoNome);
            pessoaExistente.setTelefone(novoTelefone);
            pessoaExistente.setEmail(novoEmail);

            pessoaDAO.atualizar(pessoaExistente);

            System.out.println("Pessoa atualizada com sucesso.");
            System.out.println("Informações atualizadas:");
            System.out.println(pessoaExistente);
        } else {
            System.out.println("Pessoa não encontrada.");
        }
    }

    private static void adicionarSeguro(SeguroDAO seguroDAO, Scanner scanner) throws SQLException {
        System.out.println("\nAdicionar Seguro:");
        System.out.print("Informe a data de contratação (AAAA-MM-DD): ");
        String dataContratacaoStr = scanner.nextLine();

        try {
            LocalDate vigencia = LocalDate.parse(dataContratacaoStr);

            System.out.print("Informe o status do seguro: ");
            String status = scanner.nextLine();

            System.out.print("Informe o número da apólice do seguro: ");
            String numeroApolice = scanner.nextLine();

            System.out.print("Informe o ID do Segurado para associar ao seguro: ");
            int idSegurado = scanner.nextInt();

            System.out.print("Informe o ID do Veículo para associar ao seguro: ");
            int idVeiculo = scanner.nextInt();
            scanner.nextLine();

            Seguro novoSeguro = new Seguro(vigencia, status, numeroApolice);
            seguroDAO.adicionarSeguro(novoSeguro, idVeiculo, idSegurado);

            System.out.println("Seguro cadastrado com sucesso.");
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido. Use o formato AAAA-MM-DD.");
        }
    }

}

