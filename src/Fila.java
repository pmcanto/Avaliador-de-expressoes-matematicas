public class Fila<T> {
    private static final int TAM_DEFAULT = 100;
    private int inicio, fim, qtd;
    private Object[] e;

    public Fila(int tamanho) {
        this.inicio = 0;
        this.fim = 0;
        this.qtd = 0;
        e = new Object[tamanho];
    }

    public Fila() {
        this(TAM_DEFAULT);
    }

    public boolean qIsEmpty() {
        return (qtd == 0);
    }

    public boolean qIsFull() {
        return (qtd == e.length);
    }

    public void enqueue(T elemento) throws Exception {
        if (!qIsFull()) {
            this.e[this.fim++] = elemento;
            this.fim = this.fim % this.e.length;
            this.qtd++;
        } else {
            throw new Exception("Overflow - Estouro de Fila");
        }
    }

    @SuppressWarnings("unchecked")
    public T dequeue() throws Exception {
        T aux;
        if (!qIsEmpty()) {
            aux = (T) this.e[this.inicio];
            this.inicio = ++this.inicio % this.e.length;
            this.qtd--;
            return aux;
        } else {
            throw new Exception("Underflow - Esvaziamento de Fila");
        }
    }

    @SuppressWarnings("unchecked")
    public T front() throws Exception {
        if (!qIsEmpty()) {
            return (T) e[inicio];
        } else {
            throw new Exception("Underflow - Esvaziamento de Fila");
        }
    }

    @SuppressWarnings("unchecked")
    public T rear() throws Exception {
        if (!qIsEmpty()) {
            int pfinal = (this.fim != 0) ? this.fim - 1 : this.e.length - 1;
            return (T) this.e[pfinal];
        } else {
            throw new Exception("Underflow - Esvaziamento de Fila");
        }
    }

    public int totalElementos() {
        return qtd;
    }
}
