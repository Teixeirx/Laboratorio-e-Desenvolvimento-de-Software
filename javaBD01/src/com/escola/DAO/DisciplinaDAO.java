
package com.escola.DAO;

import com.escola.model.Disciplina;
import com.escola.model.Professor;
import com.escola.util.Conexoes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DAO para a entidade Disciplina.
 * Realiza as operações de CRUD no banco de dados.
 */
public class DisciplinaDAO {

    private static final Logger logger = Logger.getLogger(DisciplinaDAO.class.getName());

    public void inserir(Disciplina disciplina) {
        String sql = "INSERT INTO Disciplina (nome, carga_horaria, id_professor) VALUES (?, ?, ?)";
        try (Connection conexao = Conexoes.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, disciplina.getNome());
            stmt.setInt(2, disciplina.getCargaHoraria());
            stmt.setInt(3, disciplina.getProfessor().getId());
            stmt.executeUpdate();
            logger.info("Disciplina inserida com sucesso.");
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao inserir disciplina: " + ex.getMessage(), ex);
        }
    }

    public List<Disciplina> listarTodas() {
        // SQL com JOIN para buscar os dados do professor junto com a disciplina
        String sql = "SELECT d.id as disciplina_id, d.nome as disciplina_nome, d.carga_horaria, " +
                     "p.id as professor_id, p.nome as professor_nome, p.idade as professor_idade, p.disciplina as professor_disciplina " +
                     "FROM Disciplina d " +
                     "JOIN Professor p ON d.id_professor = p.id";

        List<Disciplina> lista = new ArrayList<>();
        try (Connection conexao = Conexoes.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Cria o objeto Professor
                Professor professor = new Professor();
                professor.setId(rs.getInt("professor_id"));
                professor.setNome(rs.getString("professor_nome"));
                professor.setIdade(rs.getInt("professor_idade"));
                professor.setDisciplina(rs.getString("professor_disciplina"));

                // Cria o objeto Disciplina e associa o professor
                Disciplina disciplina = new Disciplina();
                disciplina.setId(rs.getInt("disciplina_id"));
                disciplina.setNome(rs.getString("disciplina_nome"));
                disciplina.setCargaHoraria(rs.getInt("carga_horaria"));
                disciplina.setProfessor(professor);

                lista.add(disciplina);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao buscar disciplinas: " + ex.getMessage(), ex);
        }
        return lista;
    }
}
