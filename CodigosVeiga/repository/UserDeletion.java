package CodigoVeiga.repository;

import java.sql.*;
import java.util.Scanner;

public class UserDeletion {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/user_management";
    private static final String USER = "root";
    private static final String PASSWORD = "18root04";

    public static void removerUsuario(Scanner scanner) {
        // lsita dos usuarios
        UserQuery.listarUsuarios();

        System.out.print("Digite o ID do usuário para remover: ");

        if (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida! Por favor, insira um número inteiro.");
            scanner.next();
            return;
        }

        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Tem certeza? (S/N): ");
        // checa a resposta
        if (!scanner.nextLine().equalsIgnoreCase("S")) return;

        String sql = "DELETE FROM usuarios WHERE id=?";

        // remove os usuários
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Usuário removido com sucesso!");
            } else {
                System.out.println("Erro: Usuário não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao remover usuário: " + e.getMessage());
        }
    }
}