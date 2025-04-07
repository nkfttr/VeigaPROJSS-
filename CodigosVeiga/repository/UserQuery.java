package CodigoVeiga.repository;

import java.sql.*;
import java.util.Scanner;

public class UserQuery {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/user_management";
    private static final String USER = "root";
    private static final String PASSWORD = "18root04";

    public static void listarUsuarios() {
        String sql = "SELECT * FROM usuarios ORDER BY nome";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (!rs.isBeforeFirst()) { // se o resultado nao tem cadastro
                System.out.println("Nenhum usuário cadastrado.");
                return;
            }

            System.out.println("\n--- Lista de Usuários ---");
            while (rs.next()) {
                System.out.printf("ID: %d | Nome: %s | CPF: %s | Idade: %d\n",
                        rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getInt("idade"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }
    }



    public static void consultarPorCPF(Scanner scanner) {
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();
        consultar("cpf", cpf);
    }

    public static void consultarPorNome(Scanner scanner) {
        System.out.print("Digite as iniciais do nome: ");
        String nome = scanner.nextLine() + "%";
        consultar("nome", nome);
    }

    private static void consultar(String campo, String valor) {
        String sql = "SELECT * FROM usuarios WHERE " + campo + " LIKE ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, valor);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.isBeforeFirst()) {
                    System.out.println("Nenhum usuário encontrado com as informações fornecidas.");
                    return;
                }
                while (rs.next()) {
                    System.out.printf("ID: %d | Nome: %s | CPF: %s | Idade: %d\n",
                            rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getInt("idade"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        }
    }
}
