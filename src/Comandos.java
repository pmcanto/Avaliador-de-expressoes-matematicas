import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Comandos {
    private static Map<Character, Double> variables = new HashMap<>();
    private static final int MAX_COMMANDS = 10;
    private static Fila<String> commandQueue = new Fila<>(MAX_COMMANDS);

    public static void handleVarCommand(Scanner scanner) {
        System.out.print("Digite a variável (A-Z): ");
        char var = scanner.nextLine().toUpperCase().charAt(0);
        System.out.print("Digite o valor: ");
        double value = Double.parseDouble(scanner.nextLine());
        variables.put(var, value);
        System.out.println("Variável " + var + " atribuída com o valor " + value);
    }

    public static String listVars() {
        if (variables.isEmpty()) {
            return "Nenhuma variável registrada.";
        } else {
            StringBuilder sb = new StringBuilder("Variáveis registradas:\n");
            for (Map.Entry<Character, Double> entry : variables.entrySet()) {
                sb.append(entry.getKey()).append(" = ").append(entry.getValue()).append("\n");
            }
            return sb.toString();
        }
    }

    public static void resetVars() {
        variables.clear();
        System.out.println("Todas as variáveis foram resetadas.");
    }

    public static Map<Character, Double> getVariables() {
        return variables;
    }

    public static void playCommands() {
        System.out.println("Reproduzindo comandos gravados...");
        Fila<String> tempQueue = new Fila<>(MAX_COMMANDS);

        while (!commandQueue.qIsEmpty()) {
            try {
                String command = commandQueue.dequeue();
                System.out.println("Executando comando: " + command);
                tempQueue.enqueue(command);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        // Restaurar os comandos na fila original
        while (!tempQueue.qIsEmpty()) {
            try {
                commandQueue.enqueue(tempQueue.dequeue());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void stopRecording(Scanner scanner) {
        System.out.println("Gravação interrompida. Apenas PLAY, ERASE ou EXIT são permitidos.");

        boolean stopMenuActive = true;
        while (stopMenuActive) {
            System.out.println("Menu STOP:");
            System.out.println("6. PLAY");
            System.out.println("7. ERASE");
            System.out.println("8. EXIT");
            System.out.print("Selecione uma opção: ");
            String stopChoice = scanner.nextLine();

            switch (stopChoice) {
                case "6":
                    playCommands();
                    break;
                case "7":
                    eraseCommands();
                    System.out.println("Comandos apagados.");
                    break;
                case "8":
                    stopMenuActive = false;
                    System.out.println("Saindo do modo STOP.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void recordCommand(String command) {
        try {
            if (!commandQueue.qIsFull()) {
                commandQueue.enqueue(command);
                System.out.println("Comando gravado: " + command);
            } else {
                System.out.println("Limite de comandos atingido. Modo REC desativado.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printRemainingSlots() {
        int remainingSlots = MAX_COMMANDS - commandQueue.totalElementos();
        System.out.println("Lugares vazios restantes na fila: " + remainingSlots);
    }

    // Método para obter a fila de comandos
    public static Fila<String> getCommandQueue() {
        return commandQueue;
    }

    // Método para redefinir a fila de comandos
    public static void resetCommandQueue() {
        commandQueue = new Fila<>(MAX_COMMANDS);
    }

    // Método para apagar todos os comandos da fila
    public static void eraseCommands() {
        commandQueue = new Fila<>(MAX_COMMANDS);
    }
}
