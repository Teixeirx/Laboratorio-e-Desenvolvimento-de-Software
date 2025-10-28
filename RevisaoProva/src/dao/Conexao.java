package dao;
import java.sql.*;

public class Conexao {
    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/escola",
            "laboratorio", // usuário do MySQL
            "laboratorio"      // senha do MySQL
        );
    }
}
