public class Variaveis {
    // Array para armazenar os valores das variáveis, 
    // com tamanho fixo de 26 (representando as letras A a Z)
    private Double[] t = new Double[26]; 

    // Construtor da classe Variaveis
    public Variaveis() {
        for (int i = 0; i < t.length; i++) {
            t[i] = null; 
        }
    }

    // Método para obter o valor de uma variável,
    // dado o índice (0 para A, 1 para B, ..., 25 para Z)=
    public Double getValor(int indice) {
        if (indice >= 0 && indice < t.length) {
            return t[indice];
        } else {
            return null;
        }
    }

    // Método para definir o valor de uma variável, dado o índice e 
    //o valor a ser armazenado
    public void setValor(int indice, Double valor) {
        if (indice >= 0 && indice < t.length) {
            t[indice] = valor;
        }
    }
}
