Com base nos arquivos Java fornecidos, o módulo `ex` (que presumivelmente agrupa `Main.java`, `GeradorImpares.java` e `GeradorPares.java`) deve ser adicionado ao README com foco em **programação concorrente** e **sincronização de threads** usando **Semáforos**.

---
## ☕ Coleção de Exercícios Práticos em Java

Este repositório contém uma coleção de implementações em Java, divididas em quatro módulos distintos. Cada pasta foca em um conceito ou lógica específica de programação orientada a objetos e processamento paralelo/distribuído.

## 📂 Estrutura do Projeto

O código está organizado nas seguintes pastas:

### 📁 Gerenciamento de Entidade (Jogador)

Focada na criação e manipulação básica de objetos.

  * **`Jogador.java`**: Classe que define o modelo de um jogador. Provavelmente contém atributos como nome, pontuação, e métodos *getters* e *setters*.
  * **`Main.java`**: Classe principal deste módulo. Responsável por instanciar a classe `Jogador`, atribuir valores e exibir os resultados no console.

### 📁 Processamento e Tarefas (Combinadora/Trabalhadora)

Focada em lógica de execução, combinação de dados ou multithreading.

  * **`Combinadora.java`**: Classe responsável por lógica de agregação ou combinação de resultados.
  * **`Trabalhadora.java`**: Classe que executa uma tarefa específica ou "trabalho". O nome sugere o uso de processamento em *background* ou implementação de lógica de serviço.
  * **`Main.java`**: Classe principal (Runner) específica para testar a interação entre a `Combinadora` e a `Trabalhadora`.

### 📁 Domínio de Esportes (Basquete)

Focada na aplicação de regras de negócio específicas para um esporte.

  * **`Basquete.java`**: Classe que modela as regras, pontuações ou estatísticas específicas de um jogo ou jogador de basquete.
  * **`Main.java`**: Classe principal utilizada para executar as simulações ou testes relacionados ao módulo de Basquete.

---

### 📁 Sincronização de Threads (Geradores) 💡

Focada em **Programação Concorrente**, demonstrando o uso de **Semáforos** para **sincronização de acesso a recursos compartilhados** entre múltiplas *threads*.

  * **`GeradorPares.java`**: Thread responsável por inserir números pares aleatórios (0, 2, 4, 6, 8) no array `CHAVE` e incrementar um contador de pares.
  * **`GeradorImpares.java`**: Thread responsável por inserir números ímpares aleatórios (1, 3, 5, 7, 9) no array `CHAVE` e incrementar um contador de ímpares.
  * **`Main.java`**: Classe principal que instancia **quatro threads** (dois `GeradorPares` e dois `GeradorImpares`) e utiliza três semáforos (`semaforoVetor`, `semaforoPar`, `semaforoImpar`) para garantir:
    1.  **Exclusão Mútua** no acesso ao array `CHAVE` (`semaforoVetor`).
    2.  **Exclusão Mútua** no incremento dos contadores compartilhados (`semaforoPar` e `semaforoImpar`).

-----

## 🚀 Como Executar

Como os projetos estão separados por contextos (Pastas), você deve compilar e executar cada `Main` individualmente.

### Pré-requisitos

  * Java JDK instalado.

-----

## 🛠 Tecnologias

  * **Linguagem:** Java
  * **Paradigma:** Orientação a Objetos (POO)
  * **Concorrência:** Semáforos (`java.util.concurrent.Semaphore`)
