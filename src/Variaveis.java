public class Variaveis {
    private Double[] t = new Double[26]; // Criação de um array para 25 variáveis

    public Variaveis() {
        for (int i = 0; i < t.length; i++) {
            t[i] = null; // Inicializa todas as variáveis como nulas
        }
    }

    // Alteração: o parâmetro agora é um int
    public Double getValor(int indice) {
        if (indice >= 0 && indice < t.length) {
            return t[indice];
        } else {
            return null;
        }
    }

    // Alteração: o índice (indice) é int e o valor é Double
    public void setValor(int indice, Double valor) {
        if (indice >= 0 && indice < t.length) {
            t[indice] = valor;
        }
    }
}
