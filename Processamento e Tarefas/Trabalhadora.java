import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Trabalhadora extends Thread{
    private final Semaphore semIdArquivo;
    private final Semaphore semContadorDaBarreira;
    private final Semaphore semBarreiraEntrada;
    private final Semaphore semBarreiraSaida;
    private final Semaphore semBarreiraFilaEscrever;
    private final Semaphore semBarreiraFilaLer;
    private final String[] filaDeArquivosGerados;
    private final int[] contadorBarreira;
    private final int[] idArquivo;
    private final int qtdDeLoop;

    public Trabalhadora(
            Semaphore semIdArquivo,
            Semaphore semContadorDaBarreira,
            Semaphore semBarreiraEntrada,
            Semaphore semBarreiraSaida,
            Semaphore semBarreiraFilaEscrever,
            Semaphore semBarreiraFilaLer,
            String[] filaDeArquivosGerados,
            int[] contadorBarreira,
            int[] idArquivo,
            int qtdDeLoop
    ) {
        this.semIdArquivo = semIdArquivo;
        this.semContadorDaBarreira = semContadorDaBarreira;
        this.semBarreiraEntrada = semBarreiraEntrada;
        this.semBarreiraSaida = semBarreiraSaida;
        this.semBarreiraFilaEscrever = semBarreiraFilaEscrever;
        this.semBarreiraFilaLer = semBarreiraFilaLer;
        this.filaDeArquivosGerados = filaDeArquivosGerados;
        this.contadorBarreira = contadorBarreira;
        this.idArquivo = idArquivo;
        this.qtdDeLoop = qtdDeLoop;
    }

    public void run() {
        Random r = new Random();
        int i;
        int repeticao = 0;
        int numeroDeRepeticoes = 1000000;

        while (repeticao < this.qtdDeLoop) {
            try {
                System.out.println("Trabalhadora " + repeticao);

                this.semIdArquivo.acquire();
                int idArquivo = this.idArquivo[0];
                this.idArquivo[0] = this.idArquivo[0] + 1;
                this.semIdArquivo.release();

                // Gera o Arquivo Desordenado
                FileWriter arqWriterDesordenado = new FileWriter("src\\Files\\Desordenado\\Desordenado"+idArquivo+".txt");
                PrintWriter gravarArq = new PrintWriter(arqWriterDesordenado);
                for (i = 0; i < numeroDeRepeticoes; i++) {
                    gravarArq.println(r.nextInt(1000000));
                }
                arqWriterDesordenado.close();

                // Ordena arquivo
                FileReader arqReader =  new FileReader("src\\Files\\Desordenado\\Desordenado"+idArquivo+".txt");
                Scanner in = new Scanner(arqReader);

                i = 0;
                int[] numeros = new int[numeroDeRepeticoes];
                while (in.hasNext()){
                    numeros[i] = Integer.parseInt(in.nextLine());
                    i++;
                }
                Arrays.sort(numeros);

                // Gera o Arquivo Ordenado
                String nomeDoArquivo = "src\\Files\\Ordenado\\Ordenado"+idArquivo+".txt";
                FileWriter arqWriterOrdenado = new FileWriter(nomeDoArquivo);
                PrintWriter gravarArqOrdenado = new PrintWriter(arqWriterOrdenado);
                for (i = 0; i < numeroDeRepeticoes; i++) {
                    gravarArqOrdenado.println(numeros[i]);
                }
                arqWriterOrdenado.close();

                this.semContadorDaBarreira.acquire();
                    this.contadorBarreira[0] = this.contadorBarreira[0] + 1;
                    if (this.contadorBarreira[0] == 4) {
                        this.semBarreiraSaida.acquire();
                        this.semBarreiraEntrada.release();
                    }
                this.semContadorDaBarreira.release();

                this.semBarreiraEntrada.acquire();
                this.semBarreiraEntrada.release();

                this.filaDeArquivosGerados[0] = nomeDoArquivo;

                // Último dos 4 arquivos
                if ((idArquivo + 1) % 4 == 0){
                    this.semBarreiraFilaLer.release();
                    this.semBarreiraFilaEscrever.acquire();
                }

                this.semContadorDaBarreira.acquire();
                    this.contadorBarreira[0] = this.contadorBarreira[0] - 1;
                    if (this.contadorBarreira[0] == 0) {
                        this.semBarreiraEntrada.acquire();
                        this.semBarreiraSaida.release();
                    }
                this.semContadorDaBarreira.release();

                this.semBarreiraSaida.acquire();
                this.semBarreiraSaida.release();

            } catch (Exception e) {
                e.printStackTrace();
            }

            repeticao++;
        }
    }
}
