package operadores;

import model.Individuo;
import java.util.Random;

public class Crossover {

    public static Individuo[] pontoUnico(Individuo pai1, Individuo pai2, double taxaCrossover, Random random) {
        if (random.nextDouble() < taxaCrossover) {
            String cromossomo1 = pai1.getCromossomo();
            String cromossomo2 = pai2.getCromossomo();
            
            // Ponto de corte entre 1 e o penúltimo bit (garante que há troca de fato)
            int ponto = 1 + random.nextInt(cromossomo1.length() - 1);

            String filho1Str = cromossomo1.substring(0, ponto) + cromossomo2.substring(ponto);
            String filho2Str = cromossomo2.substring(0, ponto) + cromossomo1.substring(ponto);

            return new Individuo[]{ new Individuo(filho1Str), new Individuo(filho2Str) };
        }

        // Sem crossover: retorna cópias dos pais
        return new Individuo[]{ new Individuo(pai1.getCromossomo()), new Individuo(pai2.getCromossomo()) };
    }
}