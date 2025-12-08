package com.company;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Basquete extends Thread {
    private Semaphore barreira1;
    private Semaphore barreira2;
    private Semaphore mutex;
    private int[] contador;
    private int[] numero;
    private int pontosMutaveis;
    private int pontosAnteriores;
    private int cesta;
    private boolean[] fimDoJogo;
    private int[] tentativaArremesso;
    private int ordem;
    private String nome;

    public Basquete(Semaphore entrada, Semaphore saida, Semaphore mutex, int[] contador,
                    int[] n, boolean[] fimDoJogo, int[] tentativaArremesso, int ordem, String nome)
    {
        this.barreira1 = entrada; this.barreira2 = saida;this.mutex = mutex;this.contador = contador;
        this.numero = n;this.pontosMutaveis = 0;this.cesta = 24;this.fimDoJogo = fimDoJogo;
        this.tentativaArremesso = tentativaArremesso;this.ordem = ordem;this.nome = nome;
    }

    public void ArremessoNaCesta()
    {
        Random random = new Random();
        try
        {
            this.tentativaArremesso[this.ordem] = random.nextInt(this.cesta) + 1;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public int acertaCesta()
    {
        int acertou = -1;
        for (int i = 0; i < this.numero[0]; i++)
        {
            if (this.tentativaArremesso[i] % 3 == 0)
            {
                acertou = this.tentativaArremesso[i];
            }
        }
        return acertou;
    }

    public void VerificaSeAcertou()
    {
        try
        {
            if(this.tentativaArremesso[this.ordem] == this.acertaCesta())
            {
                this.pontosMutaveis += 3;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void run()
    {
        try
        {
            while(!fimDoJogo[0])
            {
                this.ArremessoNaCesta();
                mutex.acquire();
                this.contador[0]++;
                if(this.contador[0] == this.numero[0])
                {
                    System.out.println();
                    this.barreira2.acquire();
                    this.barreira1.release();
                }
                this.mutex.release();
                this.barreira1.acquire();
                this.barreira1.release();
                this.VerificaSeAcertou();
                this.mutex.acquire();
                this.contador[0]--;
                if(this.contador[0] == 0)
                {
                    this.barreira1.acquire();
                    this.barreira2.release();
                    System.out.println();
                }
                this.mutex.release();
                this.barreira2.acquire();
                this.barreira2.release();
                if(this.tentativaArremesso[this.ordem] == this.acertaCesta())
                {
                    if (pontosMutaveis!=0) {
                        this.pontosAnteriores = (this.pontosMutaveis - 3);}
                    if (pontosMutaveis==0) {
                        this.pontosAnteriores = 0;}
                    System.out.println(" |" + this.pontosAnteriores + "| pontos / "
                            + this.nome + " --> Arremessa a bola na cesta de 3 e... Acertou!!!!" +
                            " (+3)! |" + this.pontosMutaveis + "| pontos agora!");
                }
                if(this.pontosMutaveis == 9)
                {
                    System.out.println("\n" + this.nome +" acertou 3 cestas e chegou a 9 pontos, FIM DE JOGO!!!" +
                            "\nParabéns " + this.nome +"! Joga muito.");
                    fimDoJogo[0] = true;
                    System.exit(0);
                }
                else
                {
                    System.out.println(" |" + this.pontosMutaveis + "| pontos / " + this.nome
                            + " --> Arremessa a bola na cesta de 3 e... Errou!! A bola ficou a "
                            + this.tentativaArremesso[this.ordem] + "cm de entrar na cesta!");
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}