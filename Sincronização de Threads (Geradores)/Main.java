package com.company;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        Integer[] CHAVE = new Integer[100];
        Semaphore semaforoVetor = new Semaphore(1);
        Semaphore semaforoPar = new Semaphore(1);
        Semaphore semaforoImpar = new Semaphore(1);

        Integer[] contadorDePar = new Integer[1];
        contadorDePar[0] = 0;

        Integer[] contadorDeImpar = new Integer[1];
        contadorDeImpar[0] = 0;

        GeradorPares geradorPares1 = new GeradorPares(CHAVE, contadorDePar, semaforoVetor, semaforoPar);
        GeradorImpares geradorImpares1 = new GeradorImpares(CHAVE, contadorDeImpar, semaforoVetor, semaforoImpar);
        GeradorPares geradorPares2 = new GeradorPares(CHAVE, contadorDePar, semaforoVetor, semaforoPar);
        GeradorImpares geradorImpares2 = new GeradorImpares(CHAVE, contadorDeImpar, semaforoVetor, semaforoImpar);

        geradorPares1.start();
        geradorImpares1.start();
        geradorPares2.start();
        geradorImpares2.start();

        try {
            geradorImpares1.join(); // espera a thread geradorImpares1 terminar
            geradorPares1.join(); // espera a thread geradorPares1 terminar
            geradorImpares2.join(); // espera a thread geradorImpares2 terminar
            geradorPares2.join(); // espera a thread geradorPares2 terminar

            System.out.print("O resultado final do array CHAVE é: \n");
            for (Integer valor : CHAVE) {
                System.out.print(valor + " ");
            }

            System.out.print("\nQuantidade de impares: " + contadorDeImpar[0]);
            System.out.print("\nQuantidade de pares: " + contadorDePar[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
