import java.util.Scanner;

public class RecMenu {
    private static boolean isRecording = false;

    public static void startRecording(Scanner scanner) {
        isRecording = true;
        System.out.println("Modo REC ativado. Comandos serão gravados na fila.");

        while (isRecording) {
            System.out.println("Menu REC:");
            System.out.println("1. expression");
            System.out.println("2. VAR");
            System.out.println("3. VARS");
            System.out.println("4. RESET");
            System.out.println("5. STOP");
            System.out.println("6. PLAY");
            System.out.println("7. ERASE");
            System.out.println("8. EXIT");
            System.out.print("Selecione uma opção: ");
            String recChoice = scanner.nextLine();

            switch (recChoice) {
                case "1":
                    InfixToPostfixCalculator.processExpression(scanner, Comandos.getCommandQueue());
                    break;
                case "2":
                    Comandos.handleVarCommand(scanner);
                    break;
                case "3":
                    System.out.println(Comandos.listVars());
                    break;
                case "4":
                    Comandos.resetVars();
                    break;
                case "5":
                    isRecording = false;
                    Comandos.stopRecording(scanner);
                    break;
                case "6":
                    Comandos.playCommands();
                    break;
                case "7":
                    Comandos.eraseCommands();
                    System.out.println("Comandos apagados.");
                    break;
                case "8":
                    isRecording = false;
                    System.out.println("Saindo do modo REC.");
                    break;
                default:
                    Comandos.recordCommand(recChoice);
            }
            Comandos.printRemainingSlots();
        }
    }
}
