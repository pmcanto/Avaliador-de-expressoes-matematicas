import java.util.Map;
import java.util.Scanner;

public class InfixToPostfixCalculator {

    // Função para verificar se um caractere é um operador
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    // Função para verificar a precedência dos operadores
    private static int precedence(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    // Função para converter expressão infixa para posfixa
    public static String infixToPostfix(String expression) {
        StringBuilder result = new StringBuilder();
        Pilha stack = new Pilha();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // Ignorar espaços em branco
            if (c == ' ') {
                continue;
            }

            // Se o caractere for um operando, adicioná-lo ao resultado
            if (Character.isLetter(c)) {
                result.append(c);
            }
            // Se o caractere for '(', empilhá-lo
            else if (c == '(') {
                stack.push(c);
            }
            // Se o caractere for ')', desempilhar até encontrar '('
            else if (c == ')') {
                while (!stack.isEmpty() && stack.topo() != '(') {
                    result.append(stack.pop());
                }
                stack.pop();
            }
            // Se o caractere for um operador
            else if (isOperator(c)) {
                while (!stack.isEmpty() && precedence(stack.topo()) >= precedence(c)) {
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }

        // Desempilhar todos os operadores restantes
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    // Função para processar a expressão
    public static void processExpression(Scanner scanner, Fila<String> commandQueue) {
        System.out.print("Digite a expressão: ");
        String expression = scanner.nextLine();
        Map<Character, Double> variables = Comandos.getVariables();

        // Verificar se todas as variáveis na expressão foram definidas
        for (char c : expression.toCharArray()) {
            if (Character.isLetter(c) && !variables.containsKey(c)) {
                System.out.println("Erro: Variável " + c + " não definida.");
                return;
            }
        }

        // Converter para notação pós-fixa
        String postfixExpression = infixToPostfix(expression);
        try {
            commandQueue.enqueue(postfixExpression);
            System.out.println("Expressão pós-fixa gravada: " + postfixExpression);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
