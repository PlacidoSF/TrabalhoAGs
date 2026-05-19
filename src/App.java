import core.AlgoritmoGenetico;
import model.Individuo;

public class App {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("Algoritmo Genético - Maximização de f(x, y)");
        System.out.println("f(x, y) = |e^(-x) - y^2 + 1| + 10^-4");
        System.out.println("==================================================");

        
        int tamanhoPopulacao = 100;
        int geracoes = 500;
        double taxaMutacao = 0.05;
        int kTorneio = 3;

        System.out.println("\nExecutando AG com os parâmetros:");
        System.out.println(" - tamanho_populacao: " + tamanhoPopulacao);
        System.out.println(" - geracoes: " + geracoes);
        System.out.println(" - taxa_mutacao: " + taxaMutacao);
        System.out.println(" - k_torneio: " + kTorneio);

        
        AlgoritmoGenetico ag = new AlgoritmoGenetico(
                tamanhoPopulacao, geracoes, taxaMutacao, kTorneio
        );

        Individuo resultado = ag.executar();

        System.out.println("\n==================================================");
        System.out.println("RESULTADO FINAL");
        System.out.println("==================================================");
        System.out.printf("Melhor x: %.6f\n", resultado.getX());
        System.out.printf("Melhor y: %.6f\n", resultado.getY());
        System.out.printf("Valor máximo f(x, y): %.6f\n", resultado.getFitness());
        System.out.println("Cromossomo (binário): " + resultado.getCromossomo());
        System.out.println("==================================================");
    }
}