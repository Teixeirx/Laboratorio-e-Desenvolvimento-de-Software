
package com.escola.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Fábrica de conexões com o banco de dados.
 * Gerencia a criação e o fechamento de conexões.
 */
public class Conexoes {

    private static final String URL = "jdbc:mysql://localhost:3306/escola?useTimezone=true&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String SENHA = "laboratorio";
    private static final Logger logger = Logger.getLogger(Conexoes.class.getName());

    private Conexoes() {
        // Construtor privado para evitar instanciação
    }

    /**
     * Obtém uma nova conexão com o banco de dados.
     *
     * @return um objeto Connection ou null em caso de falha.
     */
    public static Connection getConexao() {
        try {
            logger.info("Iniciando conexão com o banco de dados...");
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            logger.info("Conexão estabelecida com sucesso.");
            return conexao;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao conectar ao banco de dados: " + e.getMessage(), e);
            return null;
        }
    }

    /**
     * Fecha uma conexão com o banco de dados.
     *
     * @param conexao A conexão a ser fechada.
     */
    public static void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
                logger.info("Conexão com o banco de dados fechada.");
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Erro ao fechar a conexão: " + e.getMessage(), e);
            }
        }
    }
}
