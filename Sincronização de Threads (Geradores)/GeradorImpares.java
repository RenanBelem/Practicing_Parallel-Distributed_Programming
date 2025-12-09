package com.company;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class GeradorImpares extends Thread {

    private Integer[] CHAVE;
    public Integer[] contadorDeImpar;
    private Semaphore semaforoVetor;
    private Semaphore semaforoImpar;

    public GeradorImpares(Integer[] CHAVE, Integer[] contadorDeImpar, Semaphore semaforoVetor, Semaphore semaforoImpar) {
        this.CHAVE = CHAVE;
        this.contadorDeImpar = contadorDeImpar;
        this.semaforoVetor = semaforoVetor;
        this.semaforoImpar = semaforoImpar;
    }

    public void run() {
        Random r = new Random();
        int[] imparesDisponiveis = {1, 3, 5, 7, 9};

        while (true) {
            try {
                boolean status = false;

                semaforoVetor.acquire(); // esperar
                Thread.sleep(r.nextInt(2000) + 1000);
                for (int i = 0; i < this.CHAVE.length; i++) {
                    if (this.CHAVE[i] == null) {
                        this.CHAVE[i] = imparesDisponiveis[r.nextInt(5)];
                        semaforoImpar.acquire(); // esperar
                        this.contadorDeImpar[0] = this.contadorDeImpar[0] + 1;
                        semaforoImpar.release(); // sinalizar
                        status = true;
                        break;
                    }
                }
                semaforoVetor.release(); // sinalizar

                // Array cheio
                if (!status) return; // Array cheio

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
