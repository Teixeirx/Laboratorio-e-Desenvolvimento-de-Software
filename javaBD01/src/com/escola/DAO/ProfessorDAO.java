
package com.escola.DAO;

import com.escola.model.Professor;
import com.escola.util.Conexoes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DAO para a entidade Professor.
 * Realiza as operações de CRUD no banco de dados.
 */
public class ProfessorDAO {

    private static final Logger logger = Logger.getLogger(ProfessorDAO.class.getName());

    public void inserir(Professor professor) {
        String sql = "INSERT INTO professor (nome, idade, disciplina) VALUES (?, ?, ?)";
        try (Connection conexao = Conexoes.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, professor.getNome());
            stmt.setInt(2, professor.getIdade());
            stmt.setString(3, professor.getDisciplina());
            stmt.execute();
            logger.info("Professor inserido com sucesso.");
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao inserir professor: " + ex.getMessage(), ex);
        }
    }

    public Professor buscarPorId(int id) {
        String sql = "SELECT * FROM Professor WHERE id = ?";
        try (Connection conexao = Conexoes.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Professor professor = new Professor();
                    professor.setId(rs.getInt("id"));
                    professor.setNome(rs.getString("nome"));
                    professor.setIdade(rs.getInt("idade"));
                    professor.setDisciplina(rs.getString("disciplina"));
                    return professor;
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao consultar professor: " + ex.getMessage(), ex);
        }
        return null;
    }

    public void atualizar(Professor professor) {
        String sql = "UPDATE Professor SET nome=?, idade=?, disciplina=? WHERE id=?";
        try (Connection conexao = Conexoes.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, professor.getNome());
            stmt.setInt(2, professor.getIdade());
            stmt.setString(3, professor.getDisciplina());
            stmt.setInt(4, professor.getId());
            stmt.execute();
            logger.info("Professor atualizado com sucesso.");
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao atualizar professor: " + ex.getMessage(), ex);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM Professor WHERE id=?";
        try (Connection conexao = Conexoes.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
            logger.info("Professor excluído com sucesso.");
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao excluir professor: " + ex.getMessage(), ex);
        }
    }

    public List<Professor> listarTodos() {
        String sql = "SELECT * FROM professor";
        List<Professor> listaProfessores = new ArrayList<>();
        try (Connection conexao = Conexoes.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Professor professor = new Professor();
                professor.setId(rs.getInt("id"));
                professor.setNome(rs.getString("nome"));
                professor.setIdade(rs.getInt("idade"));
                professor.setDisciplina(rs.getString("disciplina"));
                listaProfessores.add(professor);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao consultar todos os professores: " + ex.getMessage(), ex);
        }
        return listaProfessores;
    }

    public List<Professor> buscarPorNome(String nome) {
        String sql = "SELECT * FROM professor WHERE nome LIKE ?";
        List<Professor> listaProfessores = new ArrayList<>();
        try (Connection conexao = Conexoes.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Professor professor = new Professor();
                    professor.setId(rs.getInt("id"));
                    professor.setNome(rs.getString("nome"));
                    professor.setIdade(rs.getInt("idade"));
                    professor.setDisciplina(rs.getString("disciplina"));
                    listaProfessores.add(professor);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao consultar professores pelo nome: " + ex.getMessage(), ex);
        }
        return listaProfessores;
    }
}
