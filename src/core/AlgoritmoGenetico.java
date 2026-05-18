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
    private final double taxaCrossover;
    private final double taxaMutacao;
    private final int kTorneio;
    private final Random random;

    public AlgoritmoGenetico(int tamanhoPopulacao, int geracoes, double taxaCrossover, double taxaMutacao, int kTorneio) {
        this.tamanhoPopulacao = tamanhoPopulacao;
        this.geracoes = geracoes;
        this.taxaCrossover = taxaCrossover;
        this.taxaMutacao = taxaMutacao;
        this.kTorneio = kTorneio;
        this.random = new Random();
    }

    public Individuo executar() {
        // 1. Inicialização da População
        List<Individuo> populacao = new ArrayList<>();
        for (int i = 0; i < tamanhoPopulacao; i++) {
            populacao.add(new Individuo(random));
        }

        Individuo melhorGlobal = populacao.get(0);

        // Laço principal de evolução
        for (int g = 0; g < geracoes; g++) {
            
            // Encontrar o melhor da geração atual (para o elitismo)
            Individuo melhorGeracao = populacao.get(0);
            for (Individuo ind : populacao) {
                if (ind.getFitness() > melhorGeracao.getFitness()) {
                    melhorGeracao = ind;
                }
            }

            // Atualizar o melhor global
            if (melhorGeracao.getFitness() > melhorGlobal.getFitness()) {
                melhorGlobal = melhorGeracao;
            }

            // 3. Criação da Nova Geração
            List<Individuo> novaPopulacao = new ArrayList<>();

            // Aplicar Elitismo: o melhor da geração anterior entra direto
            novaPopulacao.add(melhorGeracao);

            // Preencher o restante da população
            while (novaPopulacao.size() < tamanhoPopulacao) {
                // Seleção
                Individuo pai1 = Selecao.torneio(populacao, kTorneio, random);
                Individuo pai2 = Selecao.torneio(populacao, kTorneio, random);

                // Crossover
                Individuo[] filhos = Crossover.pontoUnico(pai1, pai2, taxaCrossover, random);

                // Mutação
                Individuo filho1 = Mutacao.bitABit(filhos[0], taxaMutacao, random);
                Individuo filho2 = Mutacao.bitABit(filhos[1], taxaMutacao, random);

                // Adicionar à nova população respeitando o limite
                novaPopulacao.add(filho1);
                if (novaPopulacao.size() < tamanhoPopulacao) {
                    novaPopulacao.add(filho2);
                }
            }

            populacao = novaPopulacao;
        }

        return melhorGlobal;
    }
}