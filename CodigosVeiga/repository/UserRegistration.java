package CodigoVeiga.repository;

import CodigoVeiga.service.CPFValidator;

import java.sql.*;
import java.util.Scanner;

public class UserRegistration {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/user_management";
    private static final String USER = "root";
    private static final String PASSWORD = "18root04";

    public static void cadastrarUsuario(Scanner scanner) {
        System.out.println("\n--- Cadastrar Usuário ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        // validação do cpf
        if (!CPFValidator.validarCPF(cpf)) {
            System.out.println("Erro: CPF inválido!");
            return;
        }

        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();

        // verificação de campos
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Erro: O nome não pode ser vazio ou nulo.");
            return;
        }
        if (cpf == null || cpf.trim().isEmpty()) {
            System.out.println("Erro: O CPF não pode ser vazio ou nulo.");
            return;
        }
        if (idade <= 0) {
            System.out.println("Erro: A idade deve ser maior que zero.");
            return;
        }

        String sql = "INSERT INTO usuarios (nome, cpf, idade) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             //coloca os avlores do usuario na string sql
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setInt(3, idade);
            stmt.executeUpdate();
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println("Erro: CPF já cadastrado! Tente outro CPF.");
            } else if (e.getErrorCode() == 1048) {
                System.out.println("Erro: Todos os campos são obrigatórios!");
            } else {
                System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
            }
        }
    }
}
