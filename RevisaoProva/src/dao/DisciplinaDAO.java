package dao;

import model.*;
import java.sql.*;
import java.util.*;

public class DisciplinaDAO {
    public void inserir(Disciplina d) throws SQLException {
        Connection c = Conexao.conectar();
        PreparedStatement ps = c.prepareStatement(
            "INSERT INTO disciplinas (nome, carga_horaria, professor_id) VALUES (?,?,?)"
        );
        ps.setString(1, d.getNome());
        ps.setInt(2, d.getCargaHoraria());
        ps.setInt(3, d.getProfessor().getId());
        ps.executeUpdate();
        c.close();
    }

    public List<Disciplina> listar() throws SQLException {
        Connection c = Conexao.conectar();
        String sql = """
            SELECT d.id, d.nome, d.carga_horaria, p.id AS pid, p.nome AS pnome, p.email 
            FROM disciplinas d 
            INNER JOIN professores p ON d.professor_id = p.id
        """;
        ResultSet rs = c.createStatement().executeQuery(sql);
        List<Disciplina> lista = new ArrayList<>();
        while (rs.next()) {
            Professor prof = new Professor(rs.getInt("pid"), rs.getString("pnome"), rs.getString("email"));
            Disciplina d = new Disciplina(rs.getInt("id"), rs.getString("nome"), rs.getInt("carga_horaria"), prof);
            lista.add(d);
        }
        c.close();
        return lista;
    }
}
