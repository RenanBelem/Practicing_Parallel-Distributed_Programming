package com.company;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args){
        Semaphore barreira1 = new Semaphore(0);
        Semaphore barreira2 = new Semaphore(1);
        Semaphore mutex = new Semaphore(1);
        int[] contador = new int[1];
        int[] numeroThreads = new int[1];
        int[] tentativaArremesso = new int[7];
        boolean[] fimDoJogo = new boolean[1];

        Basquete b1 = new Basquete(barreira1, barreira2, mutex, contador, numeroThreads, fimDoJogo,
                tentativaArremesso, 0, "Michael Jackson");
        Basquete b2 = new Basquete(barreira1, barreira2, mutex, contador, numeroThreads, fimDoJogo,
                tentativaArremesso, 1, "Vladimir Lenin");
        Basquete b3 = new Basquete(barreira1, barreira2, mutex, contador, numeroThreads, fimDoJogo,
                tentativaArremesso, 2, "Flavio Caça Rato");
        Basquete b4 = new Basquete(barreira1, barreira2, mutex, contador, numeroThreads, fimDoJogo,
                tentativaArremesso, 3, "Tarsila Do Amarau");
        Basquete b5 = new Basquete(barreira1, barreira2, mutex, contador, numeroThreads, fimDoJogo,
                tentativaArremesso, 4, "Daiane Dos Santos");
        Basquete b6 = new Basquete(barreira1, barreira2, mutex, contador, numeroThreads, fimDoJogo,
                tentativaArremesso, 5, "Rosa Luxemburgo");
        Basquete b7 = new Basquete(barreira1, barreira2, mutex, contador, numeroThreads, fimDoJogo,
                tentativaArremesso, 6, "Diego Maradona");

        System.out.println("***** BASQUETE DOS FAMOSOS *****");
        System.out.println("Quem acertar 3 cestas de 3 pontos, ou seja, fazer 9 pontos, VENCE!");

        fimDoJogo[0] = false;
        contador[0] = 0;
        numeroThreads[0] = 7;
        b1.start();b2.start();b3.start();b4.start();b5.start();b6.start();b7.start();
        try
        {
            b1.join();b2.join();b3.join();b4.join();b5.join();b6.join();b7.join();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

