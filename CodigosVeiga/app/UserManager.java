package CodigoVeiga.service;

import CodigoVeiga.ENTITY.UserTableManager;
import CodigoVeiga.repository.UserDeletion;
import CodigoVeiga.repository.UserEdition;
import CodigoVeiga.repository.UserQuery;
import CodigoVeiga.repository.UserRegistration;

import java.util.Scanner;

public class UserManager {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/user_management";
    private static final String USER = "root";
    private static final String PASSWORD = "18root04";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            UserTableManager.createTable();
            menu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            //exibe menu
            System.out.println("\n=== MENU ===");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Editar usuário");
            System.out.println("3 - Remover usuário");
            System.out.println("4 - Consultar todos os usuários");
            System.out.println("5 - Consultar usuário por CPF");
            System.out.println("6 - Consultar usuários por nome");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                int opcao = scanner.nextInt();
                scanner.nextLine();
                switch (opcao) {
                    case 1:
                        UserRegistration.cadastrarUsuario(scanner);
                        break;
                    case 2:
                        UserEdition.editarUsuario(scanner);
                        break;
                    case 3:
                        UserDeletion.removerUsuario(scanner);
                        break;
                    case 4:
                        UserQuery.listarUsuarios();
                        break;
                    case 5:
                        UserQuery.consultarPorCPF(scanner);
                        break;
                    case 6:
                        UserQuery.consultarPorNome(scanner);
                        break;
                    case 0:
                        System.exit(0);
                    default:
                        System.out.println("Opção inválida!");
                }
            } else {
                System.out.println("Entrada inválida! Por favor, insira um número inteiro.");
                scanner.next();
            }

        }
    }
}
