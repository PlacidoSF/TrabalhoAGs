package core;

import model.Individuo;
import operadores.Crossover;
import operadores.Mutacao;
import operadores.Selecao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlgoritmoGenetico {

    private final int tamanhoPopulacao;
    private final int geracoes;
    private final double taxaMutacao;
    private final int kTorneio;
    private final Random random;

    public AlgoritmoGenetico(int tamanhoPopulacao, int geracoes, double taxaMutacao, int kTorneio) {
        this.tamanhoPopulacao = tamanhoPopulacao;
        this.geracoes = geracoes;
        this.taxaMutacao = taxaMutacao;
        this.kTorneio = kTorneio;
        this.random = new Random();
    }

    public Individuo executar() {

        // iniciando população
        List<Individuo> populacao = new ArrayList<>();
        for (int i = 0; i < tamanhoPopulacao; i++) {
            populacao.add(new Individuo(random));
        }

        Individuo melhorGlobal = populacao.get(0);

        
        for (int g = 0; g < geracoes; g++) {
            
            
            for (Individuo ind : populacao) {
                if (ind.getFitness() > melhorGlobal.getFitness()) {
                    melhorGlobal = ind;
                }
            }

            
            List<Individuo> novaPopulacao = new ArrayList<>();

            
            while (novaPopulacao.size() < tamanhoPopulacao) {
                
                Individuo pai1 = Selecao.torneio(populacao, kTorneio, random);
                Individuo pai2 = Selecao.torneio(populacao, kTorneio, random);

                
                Individuo[] filhos = Crossover.pontoUnico(pai1, pai2, random);

                
                Individuo filho1 = Mutacao.bitABit(filhos[0], taxaMutacao, random);
                Individuo filho2 = Mutacao.bitABit(filhos[1], taxaMutacao, random);

                
                novaPopulacao.add(filho1);
                if (novaPopulacao.size() < tamanhoPopulacao) {
                    novaPopulacao.add(filho2);
                }
            }

            populacao = novaPopulacao;
        }

       
        for (Individuo ind : populacao) {
            if (ind.getFitness() > melhorGlobal.getFitness()) {
                melhorGlobal = ind;
            }
        }

        return melhorGlobal;
    }
}