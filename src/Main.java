import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Categoria categoria = new Categoria();

    public static void main(String[] args) throws SQLException {

        try (var connection = ConnectionDB.connect()) {
            System.out.println("Banco de dados conectado com sucesso!");

            System.out.println("\n Menu:" +
                    "\n 1- Criar uma tarefa" +
                    "\n 2- Editar uma tarefa" +
                    "\n 3- Excluir tarefa" +
                    "\n 4- Visualizar todas as tarefas" +
                    "\n 5- Visualizar tarefas pendentes" +
                    "\n 6- Visualizar tarefas concluídas" +
                    "\n 7- Sair");

            int num = scanner.nextInt();
            scanner.nextLine();

            switch (num) {
                case 1:
                    criarTarefa();
                    break;
                case 2:
                    editarTarefa();
                    break;
                case 3:
                    excluirTarefa();
                    break;
                case 4:
                    verTodas();
                    break;
                case 5:
                    verPendentes();
                    break;
                case 6:
                    verConcluidas();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }

    private static void criarTarefa() throws SQLException {
        System.out.println("Digite o título: ");
        String titulo = scanner.nextLine();
        scanner.nextLine();

        System.out.println("Digite a descrição: ");
        String descricao = scanner.nextLine();

        System.out.println("Tarefa completa? (s/n): ");
        boolean status = scanner.nextLine().equalsIgnoreCase("s");

        new Tarefas(titulo, descricao, status);
    }

    private static void editarTarefa() throws SQLException {
        System.out.println("Digite o ID da tarefa para editar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Tarefas tarefa = categoria.getTarefaPorId(id);
        System.out.println("Digite o novo título da tarefa:");
        String titulo = scanner.nextLine();

        System.out.println("Digite a nova descrição da tarefa:");
        String descricao = scanner.nextLine();

        System.out.println("A tarefa está completa? (s/n):");
        boolean status = scanner.nextLine().equalsIgnoreCase("s");

        tarefa.editarTarefa(titulo, descricao, status);
    }

    private static void excluirTarefa() throws SQLException {
        System.out.println("Digite o ID da tarefa para excluir: ");
        int id = scanner.nextInt();

        Tarefas tarefa = categoria.getTarefaPorId(id);
        tarefa.excluirTarefa();
    }

    private static void verTodas() throws SQLException {
        List<Tarefas> tarefas = categoria.getTodasTarefas();
        for (Tarefas tarefa : tarefas) {
            System.out.println(tarefa);
        }
    }

    private static void verPendentes() throws SQLException {
        List<Tarefas> tarefas = categoria.getTodasTarefas();
        for (Tarefas tarefa : tarefas) {
            if (!tarefa.isStatus()) {
                System.out.println(tarefa);
            }
        }
    }

    private static void verConcluidas() throws SQLException {
        List<Tarefas> tarefas = categoria.getTodasTarefas();
        for(Tarefas tarefa : tarefas) {
            if(tarefa.isStatus()) {
                System.out.println(tarefa);
            }
        }
    }


}