package operadores;

import model.Individuo;
import java.util.Random;

public class Mutacao {

    public static Individuo bitABit(Individuo individuo, double taxaMutacao, Random random) {
        char[] bits = individuo.getCromossomo().toCharArray();
        boolean mutou = false;

        for (int i = 0; i < bits.length; i++) {
            if (random.nextDouble() < taxaMutacao) {
                bits[i] = (bits[i] == '0') ? '1' : '0';
                mutou = true;
            }
        }

        // Se houve mutação, cria um novo indivíduo. Se não, retorna o mesmo.
        if (mutou) {
            return new Individuo(new String(bits));
        }
        return individuo;
    }
}