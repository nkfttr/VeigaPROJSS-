package CodigoVeiga.ENTITY;

import java.sql.*;

public class UserTableManager {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/user_management";
    private static final String USER = "root";
    private static final String PASSWORD = "18root04";

    public static void createTable() throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS usuarios (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "nome VARCHAR(100) NOT NULL, " +
                    "cpf VARCHAR(11) UNIQUE NOT NULL, " +
                    "idade INT NOT NULL)";
            stmt.executeUpdate(sql);
        }
    }
}
