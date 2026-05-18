package operadores;

import model.Individuo;
import java.util.List;
import java.util.Random;

public class Selecao {

    public static Individuo torneio(List<Individuo> populacao, int k, Random random) {
        Individuo vencedor = populacao.get(random.nextInt(populacao.size()));

        for (int i = 1; i < k; i++) {
            Individuo competidor = populacao.get(random.nextInt(populacao.size()));
            if (competidor.getFitness() > vencedor.getFitness()) {
                vencedor = competidor;
            }
        }
        return vencedor;
    }
}