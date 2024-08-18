import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AcessoTarefa {
    public List<Tarefas> getTodasTarefas() throws SQLException {
        List<Tarefas> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefas";

        try (Connection conn = ConnectionDB.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Tarefas tarefa = new Tarefas(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        rs.getBoolean("status")
                );
                tarefas.add(tarefa);
            }
        }
        return tarefas;
    }

    public Tarefas getTarefaPorId(int id) throws SQLException {
        String sql = "SELECT * FROM tarefas WHERE id = ?";
        Tarefas tarefa = null;

        try (Connection conn = ConnectionDB.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    tarefa = new Tarefas(
                            rs.getInt("id"),
                            rs.getString("titulo"),
                            rs.getString("descricao"),
                            rs.getBoolean("status")
                    );
                }
            }
        }
        return tarefa;
    }

}

