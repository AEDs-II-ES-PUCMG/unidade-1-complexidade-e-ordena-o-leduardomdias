import java.util.Arrays;
import java.util.Random;

public class App {
    static final int[] tamanhosTesteGrande =  { 31_250_000, 62_500_000, 125_000_000, 250_000_000, 500_000_000 };
    static final int[] tamanhosTesteMedio =   {     12_500,     25_000,      50_000,     100_000,     200_000 };
    static final int[] tamanhosTestePequeno = {          3,          6,          12,          24,          48 };
    static Random aleatorio = new Random();
    static long operacoes;
    static double nanoToMilli = 1.0/1_000_000;
    

    /**
     * Gerador de vetores aleatórios de tamanho pré-definido. 
     * @param tamanho Tamanho do vetor a ser criado.
     * @return Vetor com dados aleatórios, com valores entre 1 e (tamanho/2), desordenado.
     */
    static int[] gerarVetor(int tamanho){
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = aleatorio.nextInt(1, tamanho/2);
        }
        return vetor;        
    }

    /**
     * Gerador de vetores de objetos do tipo Integer aleatórios de tamanho pré-definido. 
     * @param tamanho Tamanho do vetor a ser criado.
     * @return Vetor de Objetos Integer com dados aleatórios, com valores entre 1 e (tamanho/2), desordenado.
     */
    static Integer[] gerarVetorObjetos(int tamanho) {
        Integer[] vetor = new Integer[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = aleatorio.nextInt(1, 10 * tamanho);
        }
        return vetor;
    }

    private static void imprimirResultado(String nome, IOrdenador<Integer> ordenador) {
        System.out.println(nome + ":");
        System.out.println("Comparações: " + ordenador.getComparacoes());
        System.out.println("Movimentações: " + ordenador.getMovimentacoes());
        System.out.println("Tempo de ordenação (ms): " + ordenador.getTempoOrdenacao());
    }


    public static void main(String[] args) {
        int[] tamanhosParaTeste = { 10, 20, 50, 100, 200, 500, 1000, 2000 };

        for (int tam : tamanhosParaTeste) {
            Integer[] vetorBase = gerarVetorObjetos(tam);

            BubbleSort<Integer> bolha = new BubbleSort<>();
            InsertionSort<Integer> insercao = new InsertionSort<>();
            SelectionSort<Integer> selecao = new SelectionSort<>();

            Integer[] vetorOrdenadoBolha = bolha.ordenar(vetorBase);
            Integer[] vetorOrdenadoInsercao = insercao.ordenar(vetorBase);
            Integer[] vetorOrdenadoSelecao = selecao.ordenar(vetorBase);

            if (!Arrays.equals(vetorOrdenadoBolha, vetorOrdenadoInsercao)
                    || !Arrays.equals(vetorOrdenadoBolha, vetorOrdenadoSelecao)) {
                throw new IllegalStateException("Resultados diferentes entre algoritmos para tamanho=" + tam);
            }

            System.out.println("\n============================");
            System.out.println("Tamanho do vetor: " + tam);
            imprimirResultado("BubbleSort", bolha);
            imprimirResultado("InsertionSort", insercao);
            imprimirResultado("SelectionSort", selecao);
        }
    }
}
