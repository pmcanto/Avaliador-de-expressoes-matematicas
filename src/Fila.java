public class Fila<T> {

    private static final int TAMANHO_PADRAO = 10;
    private T dados[];
    private int contagem;
    private int primeiro;
    private int ultimo;

    public Fila() {
        this(TAMANHO_PADRAO);
    }

    @SuppressWarnings("unchecked")
    public Fila(int tamanho) {
        dados = (T[]) new Object[tamanho];
        contagem = 0;
        primeiro = 0;
        ultimo = 0;
    }

    public void enfileirar(T valor) {
        if (estaCheia()) {
            throw new RuntimeException("enfileirar(): fila cheia.");
        }
        dados[ultimo] = valor;
        contagem++;
        ultimo = (ultimo + 1) % dados.length;
    }

    public boolean estaCheia() {
        return contagem == dados.length;
    }

    public T desenfileirar() {
        if (estaVazia()) {
            throw new RuntimeException("desenfileirar(): a fila está vazia.");
        }
        T valor = dados[primeiro];
        primeiro = (primeiro + 1) % dados.length;
        contagem--;
        return valor;
    }

    public boolean estaVazia() {
        return contagem == 0;
    }

    public T frente() {
        if (estaVazia()) {
            throw new RuntimeException("frente(): fila vazia");
        }
        return dados[primeiro];
    }

    public T tras() {
        if (estaVazia()) {
            throw new RuntimeException("tras(): fila vazia");
        }
        int indice = (dados.length + ultimo - 1) % dados.length;
        return dados[indice];
    }

    public int tamanho() {
        return dados.length;
    }

    public int contador() {
        return contagem;
    }
}
