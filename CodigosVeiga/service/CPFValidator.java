package CodigoVeiga.service;

public class CPFValidator {
    //verifica se tem algo além de numeros
    public static boolean validarCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        //verifica o tamanho da string
        if (cpf.length() != 11) {
            System.out.println("Erro: O CPF deve ter 11 dígitos numéricos.");
            return false;
        }
        //evite colocar todos os numeros iguais no cpf
        if (cpf.matches("(\\d)\\1{10}")) {
            System.out.println("Erro: O CPF não pode ter todos os dígitos iguais.");
            return false;
        }

        return true;
    }
}
