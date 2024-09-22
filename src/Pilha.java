public class Pilha {

    private static final int TAM_DEFAULT = 100;
    private int topoPilha;
    private char[] e; // Array de char

    public Pilha(int tamanho) {
        this.e = new char[tamanho];
        this.topoPilha = -1;
    }

    public Pilha() {
        this(TAM_DEFAULT);
    }

    public boolean isEmpty() {
        return this.topoPilha == -1;
    }

    public boolean isFull() {
        return this.topoPilha == this.e.length-1;
    }

    public void push(char e) {
        if (!this.isFull()) {
            this.e[++this.topoPilha] = e;
        } else {
            System.out.println("Overflow - Estouro de Pilha");
        }
    }

    public char pop() {
        if (!this.isEmpty()) {
            return this.e[this.topoPilha--];
        } else {
            System.out.println("Underflow - Esvaziamento de Pilha");
            return '\0'; // Retorna char nulo em caso de underflow
        }
    }

    public char topo() {
        if (!this.isEmpty()) {
            return this.e[this.topoPilha];
        } else {
            System.out.println("Underflow - Esvaziamento de Pilha");
            return '\0'; // Retorna char nulo em caso de pilha vazia
        }
    }

    public int totalElementos() {
        return topoPilha + 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[Pilha] topoPilha: ")
                .append(topoPilha)
                .append(", capacidade: ")
                .append(e.length)
                .append(", quantidade de elementos: ")
                .append(totalElementos());
        if (topoPilha != -1) {
            sb.append(", valor do Topo: ")
                    .append(topo());
        } else {
            sb.append(", valor do Topo: PILHA VAZIA");
        }

        sb.append("\nConteúdo da Pilha: [ ");

        for (int i = 0; i <= topoPilha; ++i) {
            if (i != topoPilha) {
                sb.append(e[i]).append(", ");
            } else {
                sb.append(e[i]);
            }
        }
        sb.append(" ]");
        return sb.toString();
    }
}
