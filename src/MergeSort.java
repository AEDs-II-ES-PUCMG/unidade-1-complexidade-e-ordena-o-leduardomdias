import java.util.Arrays;

public class MergeSort<T extends Comparable<T>> implements IOrdenador<T> {

    private int comparacoes;
    private int movimentacoes;
    private double tempoOrdenacao;
    private double inicio;

    private final double nanoToMilli = 1.0 / 1_000_000;

    @Override
    public int getComparacoes() {
        return comparacoes;
    }

    @Override
    public int getMovimentacoes() {
        return movimentacoes;
    }

    @Override
    public double getTempoOrdenacao() {
        return tempoOrdenacao;
    }

    private void iniciar() {
        this.comparacoes = 0;
        this.movimentacoes = 0;
        this.inicio = System.nanoTime();
    }

    private void terminar() {
        this.tempoOrdenacao = (System.nanoTime() - this.inicio) * nanoToMilli;
    }

    @Override
    public T[] ordenar(T[] dados) {
        T[] dadosOrdenados = Arrays.copyOf(dados, dados.length);
        iniciar();
        mergeSort(dadosOrdenados, 0, dadosOrdenados.length - 1);
        terminar();
        return dadosOrdenados;
    }

    private void mergeSort(T[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;
            mergeSort(vetor, inicio, meio);
            mergeSort(vetor, meio + 1, fim);
            merge(vetor, inicio, meio, fim);
        }
    }

    @SuppressWarnings("unchecked")
    private void merge(T[] vetor, int inicio, int meio, int fim) {
        T[] esquerda = (T[]) new Comparable[meio - inicio + 1];
        T[] direita = (T[]) new Comparable[fim - meio];

        for (int i = 0; i < esquerda.length; i++) {
            esquerda[i] = vetor[inicio + i];
            movimentacoes++;
        }
        for (int i = 0; i < direita.length; i++) {
            direita[i] = vetor[meio + 1 + i];
            movimentacoes++;
        }

        int i = 0, j = 0, k = inicio;
        while (i < esquerda.length && j < direita.length) {
            comparacoes++;
            if (esquerda[i].compareTo(direita[j]) <= 0) {
                vetor[k] = esquerda[i];
                i++;
            } else {
                vetor[k] = direita[j];
                j++;
            }
            movimentacoes++;
            k++;
        }

        while (i < esquerda.length) {
            vetor[k] = esquerda[i];
            movimentacoes++;
            i++;
            k++;
        }

        while (j < direita.length) {
            vetor[k] = direita[j];
            movimentacoes++;
            j++;
            k++;
        }
    }
}
