package model;

import java.util.Random;

public class Individuo {
    // Parâmetros da codificação binária
    private static final int BITS_POR_VARIAVEL = 12;
    private static final double INTERVALO_MIN = -10.0;
    private static final double INTERVALO_MAX = 10.0;

    private final String cromossomo;
    private double x;
    private double y;
    private double fitness;

    // Construtor 1: Cria um indivíduo a partir de um cromossomo existente (usado no Crossover/Mutação)
    public Individuo(String cromossomo) {
        this.cromossomo = cromossomo;
        this.decodificar();
        this.calcularFitness();
    }

    // Construtor 2: Cria um indivíduo com genes aleatórios (usado na inicialização da população)
    public Individuo(Random random) {
        StringBuilder sb = new StringBuilder();
        int totalBits = 2 * BITS_POR_VARIAVEL;
        
        for (int i = 0; i < totalBits; i++) {
            sb.append(random.nextBoolean() ? "1" : "0");
        }
        
        this.cromossomo = sb.toString();
        this.decodificar();
        this.calcularFitness();
    }

    // Quebra o cromossomo de 24 bits e mapeia para os valores reais de x e y
    private void decodificar() {
        String segmentoX = this.cromossomo.substring(0, BITS_POR_VARIAVEL);
        String segmentoY = this.cromossomo.substring(BITS_POR_VARIAVEL, 2 * BITS_POR_VARIAVEL);

        this.x = decodificarValor(segmentoX);
        this.y = decodificarValor(segmentoY);
    }

    // Transforma o segmento de 12 bits em uma escala decimal dentro de [-10, 10]
    private double decodificarValor(String segmentoBinario) {
        int inteiro = Integer.parseInt(segmentoBinario, 2);
        int maxBin = (1 << BITS_POR_VARIAVEL) - 1; // 2^12 - 1 = 4095

        return INTERVALO_MIN + (inteiro * (INTERVALO_MAX - INTERVALO_MIN) / maxBin);
    }

    // Calcula a aptidão com base na função f(x, y) = |e^(-x) - y^2 + 1| + 10^-4
    private void calcularFitness() {
        double valorExp = Math.exp(-this.x);

        // Em Java, Math.exp para valores muito negativos de x retorna Double.POSITIVE_INFINITY em vez de estourar erro.
        // Fazemos a proteção de segurança de forma direta.
        if (Double.isInfinite(valorExp)) {
            this.fitness = Double.POSITIVE_INFINITY;
        } else {
            this.fitness = Math.abs(valorExp - (this.y * this.y) + 1) + 1e-4;
        }
    }

    // Métodos de acesso (Getters) necessários para os operadores do algoritmo
    public String getCromossomo() {
        return cromossomo;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getFitness() {
        return fitness;
    }

    @Override
    public String toString() {
        return String.format("Cromossomo: %s | x: %.4f | y: %.4f | Fitness: %.4f", 
                cromossomo, x, y, fitness);
    }
}