package dao;
import model.Professor;
import java.sql.*;
import java.util.*;

public class ProfessorDAO {
    public void inserir(Professor p) throws SQLException {
        Connection c = Conexao.conectar();
        PreparedStatement ps = c.prepareStatement("INSERT INTO professores (nome, email) VALUES (?,?)");
        ps.setString(1, p.getNome());
        ps.setString(2, p.getEmail());
        ps.executeUpdate();
        c.close();
    }

    public List<Professor> listar() throws SQLException {
        Connection c = Conexao.conectar();
        ResultSet rs = c.createStatement().executeQuery("SELECT * FROM professores");
        List<Professor> lista = new ArrayList<>();
        while(rs.next()){
            lista.add(new Professor(rs.getInt("id"), rs.getString("nome"), rs.getString("email")));
        }
        c.close();
        return lista;
    }
}
