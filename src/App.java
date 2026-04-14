import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

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

    private static IOrdenador<Integer> escolherOrdenador(int opcao) {
        switch (opcao) {
            case 1: return new BubbleSort<>();
            case 2: return new InsertionSort<>();
            case 3: return new SelectionSort<>();
            case 4: return new MergeSort<>();
            default: return null;
        }
    }

    private static String nomeOrdenador(int opcao) {
        switch (opcao) {
            case 1: return "BubbleSort";
            case 2: return "InsertionSort";
            case 3: return "SelectionSort";
            case 4: return "MergeSort";
            default: return "Desconhecido";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] tamanhosParaTeste = { 10, 20, 50, 100, 200, 500, 1000, 2000 };

        System.out.println("===== Sistema de Ordenação =====");
        System.out.println("Escolha o método de ordenação:");
        System.out.println("1 - BubbleSort");
        System.out.println("2 - InsertionSort");
        System.out.println("3 - SelectionSort");
        System.out.println("4 - MergeSort");
        System.out.println("5 - Todos os métodos");
        System.out.print("Opção: ");

        int opcao = scanner.nextInt();
        scanner.close();

        if (opcao < 1 || opcao > 5) {
            System.out.println("Opção inválida.");
            return;
        }

        for (int tam : tamanhosParaTeste) {
            Integer[] vetorBase = gerarVetorObjetos(tam);
            System.out.println("\n============================");
            System.out.println("Tamanho do vetor: " + tam);

            if (opcao == 5) {
                for (int i = 1; i <= 4; i++) {
                    IOrdenador<Integer> ordenador = escolherOrdenador(i);
                    ordenador.ordenar(vetorBase);
                    imprimirResultado(nomeOrdenador(i), ordenador);
                }
            } else {
                IOrdenador<Integer> ordenador = escolherOrdenador(opcao);
                ordenador.ordenar(vetorBase);
                imprimirResultado(nomeOrdenador(opcao), ordenador);
            }
        }
    }
}
