// |--------------- Projeto Estrutura De Dados I ------------------|
// |----------- Avaliador de Expressões Matemáticas ---------------|

// |-- Nome: Thais Feffeira Canguçu  RA: 10403283 -----------------|
// |-- Nome: Pedro Moniz Canto       RA: 10426546 -----------------|


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Inicializa uma pilha para armazenar números e operadores
        Pilha<Double> pilhaNumeros = new Pilha<>(20);
        Pilha<String> pilhaOperadores = new Pilha<>(20);

        // Inicializa uma fila para armazenar comandos
        Fila<String> filaComandos = new Fila<>(10);

        // Objeto para armazenar variáveis e seus valores
        Variaveis variaveis = new Variaveis();

        Scanner entrada = new Scanner(System.in);
        String comando = "";
        String resultado = "";
        boolean tocando = false;
        int contador = 0; // Contador de comandos gravados

        System.out.println("Digite um comando: ");

        while (!comando.equals("EXIT")) {
            if (tocando && contador > 0) {
                comando = filaComandos.desenfileirar();
                contador--;
                filaComandos.enfileirar(comando); // Reinsere o comando na fila
            } else {
                comando = entrada.nextLine().toUpperCase().replaceAll(" ", "");
            }
            System.out.println("===================");

            // Valida o comando inserido
            if ((!validarExpressao(comando)) && (validarComando(comando).equals("invalido"))) {
                System.out.println("Operação com erro de formatação");
                continue;
            }

            if (validarComando(comando).equals("operacao")) {
                // Converte a expressão para notação pós-fixa se o comando inserido 
                //for uma operação matemática
                for (int i = 0; i < comando.length(); i++) {
                    char c = comando.charAt(i);
                    if (Character.isLetter(c)) {
                        resultado += c;
                    } else if (c == '(') {
                        pilhaOperadores.empilhar(Character.toString(c));
                    } else if (c == ')') {
                        // Desempilha até encontrar o parêntese de abertura se for uma letra 
                        while (!pilhaOperadores.estaVazia() && !pilhaOperadores.topo().equals("(")) {
                            resultado += pilhaOperadores.topo();
                            pilhaOperadores.desempilhar();
                        }
                        if (pilhaOperadores.topo().equals("(")) {
                            pilhaOperadores.desempilhar();
                        }
                    } else {
                        while (!pilhaOperadores.estaVazia() && prioridade(Character.toString(c)) <= prioridade(pilhaOperadores.topo())) {
                            resultado += pilhaOperadores.topo();
                            pilhaOperadores.desempilhar();
                        }
                        pilhaOperadores.empilhar(Character.toString(c));
                    }
                }
                // Desempilha todos os operadores restantes
                while (!pilhaOperadores.estaVazia()) {
                    resultado += pilhaOperadores.topo();
                    pilhaOperadores.desempilhar();
                }

                // Calcula o resultado da expressão pós-fixa
                for (int i = 0; i < resultado.length(); i++) {
                    double op1 = 0;
                    double op2 = 0;
                    char c = resultado.charAt(i);
                    if (Character.isLetter(c)) {
                        int vari = (int) c - 65;
                        try {
                            Double valor = variaveis.getValor(vari);
                            if (valor == null) {
                                throw new Exception();
                            }
                            pilhaNumeros.empilhar(valor);
                        } catch (Exception e) {
                            System.out.println("A variável " + c + " ainda não foi definida");
                            resultado = "";
                            while (!pilhaNumeros.estaVazia()) {
                                pilhaNumeros.desempilhar();
                            }
                            break;
                        }
                    } else {
                        // Realiza a operação com os dois operandos do topo da pilha
                        op2 = pilhaNumeros.topo();
                        pilhaNumeros.desempilhar();
                        op1 = pilhaNumeros.topo();
                        pilhaNumeros.desempilhar();
                        pilhaNumeros.empilhar(realizarOperacao(op1, op2, c));
                    }
                }
                // Exibe o resultado final
                if (!pilhaNumeros.estaVazia()) {
                    System.out.println(pilhaNumeros.topo());
                    resultado = "";
                    while (!pilhaNumeros.estaVazia()) {
                        pilhaNumeros.desempilhar();
                    }
                }
            } else if (validarComando(comando).equals("atribVar")) {
                // Atribui um valor a uma variável
                try {
                    String[] partes = comando.split("=");
                    int vari = (int) partes[0].charAt(0) - 65;
                    double valor = Double.parseDouble(partes[1]);
                    variaveis.setValor(vari, valor);
                } catch (Exception ex) {
                    System.out.println("Não foi possível definir a variável de forma correta, tente novamente.");
                }
            // Imprime as variáveis já definidas e seus respectivos valores
            } else if (comando.equals("VARS")) {
                for (int i = 0; i < 25; i++) {
                    Double n = variaveis.getValor(i);
                    if (!(n == null)) {
                        int n2 = i + 65;
                        System.out.println((char) n2 + " = " + n);
                    }
                }
            // Apaga todas as variáveis salvas
            } else if (comando.equals("RESET")) {
                for (int i = 0; i < 26; i++) {
                    variaveis.setValor(i, null);
                }
                System.out.println("Variáveis excluídas com sucesso");
            } else if (comando.equals("REC")) {
                if ((filaComandos.contador() < 10) && (!validarComando(comando).equals("parar"))) {
                    System.out.println("Gravação iniciada");
                }
                while ((filaComandos.contador() < 10) && (!validarComando(comando).equals("parar"))) {
                    if (!validarComando(comando).equals("invalido")) {
                        System.out.println("REC: " + (filaComandos.contador() + 1) + "/10:");
                        comando = entrada.nextLine().toUpperCase().replaceAll(" ", "");
                        switch (validarComando(comando)) {
                            case "atribVar":
                            case "operacao":
                            case "lerVar":
                            case "resetar":
                                filaComandos.enfileirar(comando);
                                break;
                            case "gravar":
                            case "tocar":
                                System.out.println("O comando " + comando + " não pode ser usado durante uma gravação");
                                break;
                            default:
                                System.out.println("Comando inválido");
                        }
                    } else {
                        System.out.println("Comando inválido");
                    }
                }
                System.out.println("Número máximo de comandos atingido ou comando 'STOP' recebido");
            
            // Apaga todos os comandos gravados na fila.
            } else if (comando.equals("ERASE")) {
                filaComandos = new Fila<>(10);
                System.out.println("Fila de comandos apagada com sucesso");
            // Para a reprodução de comandos gravados. 
            } else if (comando.equals("STOP")) {
                tocando = false;
                contador = 0;
                System.out.println("A gravação foi interrompida");
            } else if (comando.equals("PLAY")) {
                tocando = true;
                contador = filaComandos.contador();
                System.out.println("Gravação iniciada");
            } else {
                System.out.println("Comando inválido");
            }

            System.out.println("===================");
        }
        entrada.close();
    }

    // Função que realiza operações de acordo com o operador inserido pelo usuário.
    public static double realizarOperacao(double operando1, double operando2, char operador) {
        switch (operador) {
            case '+':
                return operando1 + operando2;
            case '-':
                return operando1 - operando2;
            case '*':
                return operando1 * operando2;
            case '/':
                if (operando2 == 0) {
                    return 0;
                }
                return operando1 / operando2;
            case '^':
                return Math.pow(operando1, operando2);
            default:
                return 0;
        }
    }

    // Função para verificar a prioridade cada operador da expressão inserida. 
    public static int prioridade(String operador) {
        switch (operador) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            default:
                return 0;
        }
    }

    // Função para validar a entrada do usuário e verificar se é um comando disponível.
    public static String validarComando(String comando) {
        if (comando.matches("^[A-Z]=\\d+(\\.\\d+)?$")) {
            return "atribVar";
        }

        if (comando.matches("VARS")) {
            return "lerVar";
        }
        if (comando.matches("RESET")) {
            return "resetar";
        }
        if (comando.matches("STOP")) {
            return "parar";
        }
        if (comando.matches("ERASE")) {
            return "apagar";
        }
        if (comando.matches("REC")) {
            return "gravar";
        }
        if (comando.matches("EXIT")) {
            return "sair";
        }
        if (comando.matches("PLAY")) {
            return "tocar";
        }
        if (comando.matches(".*[+-/*^].*")) {
            if (validarExpressao(comando)) {
                return "operacao";
            }
        } else {
            return "invalido";
        }
        return "invalido";
    }

    // Função para validar as expressões matemáticas inseridas pela usuário.
    public static boolean validarExpressao(String expressao) {
        int balanco = 0; // Contador para parênteses
        boolean ultimoOperador = true;
        boolean operadorEncontrado = false;

        for (int i = 0; i < expressao.length(); i++) {
            char c = expressao.charAt(i);

            if (c == '(') {
                balanco++;
                ultimoOperador = true;
            } else if (c == ')') {
                balanco--;
                if (balanco < 0) return false; // Parênteses desbalanceados
                ultimoOperador = false;
            } else if ("+-*/^".indexOf(c) != -1) {
                if (ultimoOperador || i == 0 || i == expressao.length() - 1) {
                    return false;
                }
                ultimoOperador = true;
            } else if (Character.isLetter(c)) {
                operadorEncontrado = true;
                ultimoOperador = false;
            } else {
                return false;
            }
        }
        return balanco == 0 && operadorEncontrado && !ultimoOperador;
    }
}
