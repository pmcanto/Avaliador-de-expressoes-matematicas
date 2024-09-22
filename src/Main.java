import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Menu Principal:");
            System.out.println("1. REC");
            System.out.println("2. EXIT");
            System.out.print("Selecione uma opção: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    RecMenu.startRecording(scanner);
                    break;
                case "2":
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        System.out.println("Programa encerrado.");
        scanner.close();
    }
}
