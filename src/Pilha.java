public class Pilha<T> {
    private static final int TAMANHO_PADRAO = 30;

    private T dados[];
    private int contagem;

    public Pilha(int tamanho) {
        this.dados = (T[]) new Object[tamanho];
        this.contagem = 0;
    }

    public Pilha() {
        this(TAMANHO_PADRAO);
        this.contagem = 0;
    }

    public void empilhar(T valor) {
        if (estaCheia())
            return;
        dados[contagem] = valor;
        contagem++;
    }

    public void desempilhar() {
        if (estaVazia())
            return;
        --contagem;
        dados[contagem] = null;
    }

    public T topo() {
        if (estaVazia())
            return null;
        return dados[contagem - 1];
    }

    public boolean estaCheia() {
        return contagem == dados.length;
    }

    public boolean estaVazia() {
        return contagem == 0;
    }

    public int contagem() {
        return contagem;
    }

    public int tamanho() {
        return dados.length;
    }
}
