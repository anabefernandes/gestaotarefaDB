import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class Categoria {
    private final AcessoTarefa acessoTarefa = new AcessoTarefa();

    public List<Tarefas> getTodasTarefas() throws SQLException {
        return acessoTarefa.getTodasTarefas();
    }

    public List<Tarefas> getTarefasPorStatus(boolean status) throws SQLException {
        return acessoTarefa.getTodasTarefas().stream()
                .filter(tarefa -> tarefa.isStatus() == status)
                .collect(Collectors.toList());
    }

    public Tarefas getTarefaPorId(int id) throws SQLException {
        return acessoTarefa.getTarefaPorId(id);
    }

    public void mostrarPendentes() throws SQLException {
        List<Tarefas> pendentes = getTarefasPorStatus(false);
        pendentes.forEach(System.out::println);
    }

    public void mostrarConcluidas() throws SQLException {
        List<Tarefas> concluidas = getTarefasPorStatus(true);
        concluidas.forEach(System.out::println);
    }

    public void mostrarTarefasPorStatus(boolean status) throws SQLException {
        List<Tarefas> tarefas = getTarefasPorStatus(status);
        tarefas.forEach(System.out::println);
    }
}
