package br.com;

import br.com.dao.UserDAO;
import br.com.model.MenuOption;
import br.com.model.UserModel;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private final static UserDAO dao = new UserDAO();
    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            try {
                showMenu();
                var userInput = scanner.nextInt();

                if (userInput < 1 || userInput > 6) {
                    System.err.println("Erro: Opção inválida! Escolha entre 1 e 6.");
                    continue;
                }

                var selectedOption = MenuOption.values()[userInput - 1];

                switch (selectedOption) {
                    case SAVE -> {
                        var user = dao.save(requestToSave());
                        System.out.printf("✅ Usuário cadastrado com sucesso! ID: %d%n", user.getId());
                    }
                    case UPDATE -> {
                        System.out.println("Informe o ID do usuário que deseja atualizar:");
                        long id = scanner.nextLong();
                        // O DAO vai lançar UserNotFoundException aqui se o ID não existir
                        dao.update(requestToUpdate(id));
                        System.out.println("✅ Usuário atualizado com sucesso!");
                    }
                    case DELETE -> {
                        dao.delete(requestId());
                        System.out.println("✅ Usuário removido com sucesso.");
                    }
                    case FIND_BY_ID -> {
                        var user = dao.findBynaId(requestId());
                        System.out.println("🔍 Usuário encontrado: " + user);
                    }
                    case FIND_ALL -> {
                        var users = dao.findAll();
                        if (users.isEmpty()) {
                            System.out.println("⚠️ Nenhum usuário cadastrado.");
                        } else {
                            users.forEach(System.out::println);
                        }
                    }
                    case EXIT -> {
                        System.out.println("Encerrando sistema...");
                        return;
                    }
                }
            } catch (InputMismatchException e) {
                System.err.println("❌ Erro: Entrada inválida! Por favor, use apenas números.");
                scanner.nextLine(); // Limpa o cache do scanner para evitar loop infinito
            } catch (UserNotFoundException e) {
                System.err.println("⚠️ " + e.getMessage());
            } catch (DateTimeParseException e) {
                System.err.println("❌ Erro: Formato de data inválido. Use dd/MM/yyyy.");
            } catch (Exception e) {
                System.err.println("💥 Ocorreu um erro inesperado: " + e.getMessage());
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n===========================================");
        System.out.println("SISTEMA GESTOR DE USUÁRIOS COM DB LOCAL");
        System.out.println("1. Cadastrar | 2. Atualizar | 3. Excluir");
        System.out.println("4. Buscar ID | 5. Listar    | 6. Sair");
        System.out.println("\n===========================================");
        System.out.print("Escolha uma opção: ");
    }

    private static long requestId() {
        System.out.print("Informe o ID: ");
        return scanner.nextLong();
    }

    private static UserModel requestToSave() {

        return inputUserDetails(0);
    }

    private static UserModel requestToUpdate(long id) {

        return inputUserDetails(id);
    }

    private static UserModel inputUserDetails(long id) {
        System.out.print("Nome: ");
        var name = scanner.next();

        System.out.print("E-mail: ");
        var email = scanner.next();

        // Validação de E-mail simples
        if (!email.contains("@") || !email.contains(".")) {
            throw new UserNotFoundException.InvalidUserDataException("E-mail inválido! O formato deve ser nome@dominio.com");
        }

        System.out.print("Data de Nascimento (dd/MM/yyyy): ");
        var birthdayString = scanner.next();

        try {
            var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            var localDate = LocalDate.parse(birthdayString, formatter);
            var birthday = localDate.atStartOfDay().atOffset(ZoneOffset.UTC);

            return new UserModel(id, name, email, birthday);

        } catch (DateTimeParseException e) {

            throw new UserNotFoundException.InvalidUserDataException("Formato de data inválido (use dd/MM/yyyy).");
        }
    }
}