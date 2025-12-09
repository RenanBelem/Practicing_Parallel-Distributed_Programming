package com.company;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class GeradorPares extends Thread {

    private Integer[ ] CHAVE;
    public Integer[] contadorDePar;
    private Semaphore semaforoVetor;
    private Semaphore semaforoPar;

    public GeradorPares(Integer[] CHAVE, Integer[] contadorDePar, Semaphore semaforoVetor, Semaphore semaforoPar) {
        this.CHAVE = CHAVE;
        this.contadorDePar = contadorDePar;
        this.semaforoPar = semaforoPar;
        this.semaforoVetor = semaforoVetor;
    }

    public void run() {
        Random r = new Random();
        int[] paresDisponiveis = {0, 2, 4, 6, 8};
        while (true) {
            try {
                boolean status = false;

                semaforoVetor.acquire(); // esperar
                Thread.sleep(r.nextInt(2000) + 1000);
                for (int i = 0; i < this.CHAVE.length; i++) {
                    if (this.CHAVE[i] == null) {
                        this.CHAVE[i] = paresDisponiveis[r.nextInt(5)];
                        semaforoPar.acquire(); // esperar
                        this.contadorDePar[0] = this.contadorDePar[0] + 1;
                        semaforoPar.release(); // executar
                        status = true;
                        break;
                    }
                }
                semaforoVetor.release(); // sinalizar

                if (!status) return; // Array cheio

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
