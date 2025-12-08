import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Combinadora extends Thread{
    private final Semaphore semBarreiraEscrever;
    private final Semaphore semBarreiraLer;
    private final String[][] filaDeArquivosGerados;
    private final int qtdDeLoop;

    public Combinadora(
            Semaphore semBarreiraEscrever,
            Semaphore semBarreiraLer,
            String[][] filaDeArquivosGerados,
            int qtdDeLoop
    ) {
        this.semBarreiraEscrever = semBarreiraEscrever;
        this.semBarreiraLer = semBarreiraLer;
        this.filaDeArquivosGerados = filaDeArquivosGerados;
        this.qtdDeLoop = qtdDeLoop;
    }

    public void run() {
        int i;
        int repeticao = 0;
        int numeroDeRepeticoes = 1000000;

        while (repeticao < this.qtdDeLoop) {
            try {
                this.semBarreiraLer.acquire();

                System.out.println("Iniciando Combinatoria " + repeticao);

                // Ordena arquivo
                int[][] numeros = new int[4][numeroDeRepeticoes];
                for (int arquivo = 0; arquivo < 4; arquivo++) {
                    FileReader arqReader = new FileReader(this.filaDeArquivosGerados[arquivo][0]);
                    Scanner in = new Scanner(arqReader);
                    i = 0;
                    while (in.hasNext()){
                        numeros[arquivo][i] = Integer.parseInt(in.nextLine());
                        i++;
                    }
                }

                int[] indexListas = new int[4];
                ArrayList<Integer> numerosMergeados = new ArrayList<>();

                // Ordena os números em um array só
                while (
                        indexListas[0] != numeroDeRepeticoes &&
                        indexListas[1] != numeroDeRepeticoes &&
                        indexListas[2] != numeroDeRepeticoes &&
                        indexListas[3] != numeroDeRepeticoes
                ) {
                    int[] comparativos = {
                            numeros[0][indexListas[0]],
                            numeros[1][indexListas[1]],
                            numeros[2][indexListas[2]],
                            numeros[3][indexListas[3]]
                    };
                    int menorValor = comparativos[0];
                    for (i = 0; i < 4; i++) if (comparativos[i] < menorValor) menorValor = comparativos[i];
                    for (i = 0; i < 4; i++) if (comparativos[i] == menorValor) indexListas[i]++;
                    numerosMergeados.add(menorValor);
                }

                // Gera o Arquivo Mergeado
                FileWriter arqWriterOrdenado = new FileWriter("src\\Files\\Mergeado\\Mergeado"+repeticao+".txt");
                PrintWriter gravarArqOrdenado = new PrintWriter(arqWriterOrdenado);
                for (int numero : numerosMergeados) {
                    gravarArqOrdenado.println(numero);
                }
                arqWriterOrdenado.close();

                System.out.println("Finalizando Combinatoria " + repeticao);

                this.semBarreiraEscrever.release();
            } catch (Exception e) {
                e.printStackTrace();
            }

            repeticao++;
        }
    }
}
