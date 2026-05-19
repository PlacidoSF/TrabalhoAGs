import core.AlgoritmoGenetico;
import model.Individuo;

public class App {
    public static void main(String[] args) {
    
        int tamanhoPopulacao = 120;
        int geracoes = 400;
        double taxaMutacao = 0.05;
        int kTorneio = 4;

        System.out.println("\n---[ Otimização via Algoritmo Genético ]---");
        System.out.println("Função Alvo: f(x, y) = |e^(-x) - y^2 + 1| + 10^-4\n");
        
        System.out.println("[ Parâmetros de Configuração ]");
        System.out.printf(" > População    : %d indivíduos\n", tamanhoPopulacao);
        System.out.printf(" > Gerações     : %d\n", geracoes);
        System.out.printf(" > Taxa Mutação : %.2f\n", taxaMutacao);
        System.out.printf(" > Torneio (k)  : %d\n", kTorneio);
        
        System.out.println("\nProcessando evolução...");

        AlgoritmoGenetico ag = new AlgoritmoGenetico(
                tamanhoPopulacao, geracoes, taxaMutacao, kTorneio
        );

        Individuo resultado = ag.executar();

        System.out.println("\n[ >>> Solução Global Encontrada <<< ]");
        System.out.printf(" * Coordenada X : %.6f\n", resultado.getX());
        System.out.printf(" * Coordenada Y : %.6f\n", resultado.getY());
        System.out.printf(" * Aptidão (Max): %.6f\n", resultado.getFitness());
        System.out.println("\nDNA (Cromossomo): " + resultado.getCromossomo());
        System.out.println("-------------------------------------------\n");
    }
}