
package com.escola.DAO;

import com.escola.model.Aluno;
import com.escola.util.Conexoes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DAO para a entidade Aluno.
 * Realiza as operações de CRUD no banco de dados.
 */
public class AlunoDAO {

    private static final Logger logger = Logger.getLogger(AlunoDAO.class.getName());

    public void inserir(Aluno aluno) {
        String sql = "INSERT INTO aluno (nome, idade, curso) VALUES (?, ?, ?)";
        try (Connection conexao = Conexoes.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setString(3, aluno.getCurso());
            stmt.execute();
            logger.info("Aluno inserido com sucesso.");
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao inserir aluno: " + ex.getMessage(), ex);
        }
    }

    public Aluno buscarPorId(int id) {
        String sql = "SELECT * FROM Aluno WHERE id = ?";
        try (Connection conexao = Conexoes.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Aluno aluno = new Aluno();
                    aluno.setId(rs.getInt("id"));
                    aluno.setNome(rs.getString("nome"));
                    aluno.setIdade(rs.getInt("idade"));
                    aluno.setCurso(rs.getString("curso"));
                    return aluno;
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao consultar aluno: " + ex.getMessage(), ex);
        }
        return null;
    }

    public void atualizar(Aluno aluno) {
        String sql = "UPDATE aluno SET nome=?, idade=?, curso=? WHERE id=?";
        try (Connection conexao = Conexoes.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setString(3, aluno.getCurso());
            stmt.setInt(4, aluno.getId());
            stmt.execute();
            logger.info("Aluno atualizado com sucesso.");
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao atualizar aluno: " + ex.getMessage(), ex);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM Aluno WHERE id=?";
        try (Connection conexao = Conexoes.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
            logger.info("Aluno excluído com sucesso.");
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao excluir aluno: " + ex.getMessage(), ex);
        }
    }

    public List<Aluno> listarTodos() {
        String sql = "SELECT * FROM aluno";
        List<Aluno> listaAlunos = new ArrayList<>();
        try (Connection conexao = Conexoes.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setIdade(rs.getInt("idade"));
                aluno.setCurso(rs.getString("curso"));
                listaAlunos.add(aluno);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao consultar todos os alunos: " + ex.getMessage(), ex);
        }
        return listaAlunos;
    }

    public List<Aluno> buscarPorNome(String nome) {
        String sql = "SELECT * FROM aluno WHERE nome LIKE ?";
        List<Aluno> listaAlunos = new ArrayList<>();
        try (Connection conexao = Conexoes.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Aluno aluno = new Aluno();
                    aluno.setId(rs.getInt("id"));
                    aluno.setNome(rs.getString("nome"));
                    aluno.setIdade(rs.getInt("idade"));
                    aluno.setCurso(rs.getString("curso"));
                    listaAlunos.add(aluno);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao consultar alunos pelo nome: " + ex.getMessage(), ex);
        }
        return listaAlunos;
    }
}
