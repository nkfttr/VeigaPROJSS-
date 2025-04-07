package CodigoVeiga.repository;

import CodigoVeiga.service.CPFValidator;

import java.sql.*;
import java.util.Scanner;

public class UserEdition {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/user_management";
    private static final String USER = "root";
    private static final String PASSWORD = "18root04";

    public static void editarUsuario(Scanner scanner) {
        // mostra os usariso
        UserQuery.listarUsuarios();
        System.out.print("Digite o ID do usuário para editar: ");

        if (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida! Por favor, insira um número inteiro.");
            scanner.next();
            return;
        }

        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo CPF: ");
        String cpf = scanner.nextLine();

        // função da validação dos cpfs
        if (!CPFValidator.validarCPF(cpf)) {
            System.out.println("Erro: CPF inválido!");
            return;
        }

        System.out.print("Nova idade: ");

        if (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida! Por favor, insira um número inteiro para a idade.");
            scanner.next();
            return;
        }

        int idade = scanner.nextInt();
        scanner.nextLine();

        String sql = "UPDATE usuarios SET nome=?, cpf=?, idade=? WHERE id=?";
        // conecta no banco
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setInt(3, idade);
            stmt.setInt(4, id);
            // se usuários foram encontrados
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Usuário atualizado com sucesso!");
            } else {
                System.out.println("Erro: Usuário não encontrado.");
            }
        } catch (SQLException e) {
            // chave única
            if (e.getErrorCode() == 1062) {
                System.out.println("Erro: CPF já cadastrado! Tente outro CPF.");
            }// algum campo nulo
            else if (e.getErrorCode() == 1048) {
                System.out.println("Erro: Todos os campos são obrigatórios!");
            } else {
                System.out.println("Erro ao editar usuário: " + e.getMessage());
            }
        }
    }
}