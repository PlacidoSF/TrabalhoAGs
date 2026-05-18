# Algoritmo Genético - Maximização de Função (Java)

Este repositório contém a implementação de um Algoritmo Genético (AG) binário desenvolvido do zero em Java.

O objetivo do projeto é demonstrar a aplicação prática da computação evolutiva para encontrar o máximo global de uma função matemática real, especificamente f(x,y) = |e^(-x) - y^2 + 1| + 10^(-4), no intervalo de [-10, 10] com precisão de 0.005. O trabalho foi construído sem o uso de bibliotecas prontas de otimização, atendendo aos requisitos da disciplina de Inteligência Artificial da UFERSA.

Nesta versão, foram implementados recursos como a codificação binária em cromossomos de 24 bits, seleção por torneio, crossover de ponto único e mutação bit a bit. Além disso, a arquitetura conta com elitismo para garantir a preservação do melhor indivíduo a cada geração, otimizando a convergência para o resultado final.

## Como Executar

Compile os arquivos do pacote e execute a classe principal `App`:

```bash
javac model/*.java operadores/*.java core/*.java App.java
java App
```

## Autores
* Plácido da Silva França
* Gabriel Felipe Pereira Dantas
