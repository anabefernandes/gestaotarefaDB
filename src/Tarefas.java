import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Tarefas {
    private int id;
    private String titulo;
    private String descricao;
    private boolean status; //false-pendente e true-completo

    public Tarefas(String titulo, String descricao, boolean status) throws SQLException {
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        addBancoDados();
    }

    public Tarefas(int id, String titulo, String descricao, boolean status) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Tarefas{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", status=" + status +
                '}';
    }

    private void addBancoDados() throws SQLException {
        String query = "INSERT INTO tarefas(titulo, descricao, status) VALUES (?, ?, ?)";

        try(Connection connection = ConnectionDB.connect();
            PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, this.titulo);
            stmt.setString(2, this.descricao);
            stmt.setBoolean(3, this.status);

            int linhas = stmt.executeUpdate();

            if(linhas > 0) {
                System.out.println("Tarefa adicionada!");
            } else {
                System.out.println("Falha ao adicionar tarefa;");
            }
        }
    }

    public void editarTarefa(String titulo, String descricao, boolean status) throws SQLException  {
        String sql = "UPDATE tarefas SET titulo = ?, descricao = ?, status = ? WHERE id = ?";

        try (Connection connection = ConnectionDB.connect();
        PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, titulo);
            stmt.setString(2, descricao);
            stmt.setBoolean(3, status);
            stmt.setInt(4, getId());

            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                System.out.println("Tarefa atualizada!");
            } else {
                System.out.println("Falha ao atualizat a tarefa.");
            }
        }
    }

    public void excluirTarefa() throws SQLException {
        String query = "DELETE FROM tarefas WHERE id = ?";

        try(Connection connection = ConnectionDB.connect();
        PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, this.id);

            int linhas = stmt.executeUpdate();
            if(linhas > 0) {
                System.out.println("Tarefa excluida.");
            } else {
                System.out.println("Falha ao excluir.");
            }
        }
    }


}

