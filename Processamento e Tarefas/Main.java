import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        Semaphore semIdArquivo = new Semaphore(1);
        Semaphore semContadorDaBarreira = new Semaphore(1);
        Semaphore semBarreiraEntrada = new Semaphore(0);
        Semaphore semBarreiraSaida = new Semaphore(1);
        Semaphore semBarreiraEscrever = new Semaphore(0);
        Semaphore semBarreiraLer = new Semaphore(0);

        String[][] filaDeArquivosGerados = new String[4][1];
        int[] contadorBarreira = new int[1];
        int[] idArquivo = new int[1];
        int qtdDeLoop = 4;



        Trabalhadora trabalhadora1 = new Trabalhadora(
                semIdArquivo,
                semContadorDaBarreira,
                semBarreiraEntrada,
                semBarreiraSaida,
                semBarreiraEscrever,
                semBarreiraLer,
                filaDeArquivosGerados[0],
                contadorBarreira,
                idArquivo,
                qtdDeLoop
        );
        Trabalhadora trabalhadora2 = new Trabalhadora(
                semIdArquivo,
                semContadorDaBarreira,
                semBarreiraEntrada,
                semBarreiraSaida,
                semBarreiraEscrever,
                semBarreiraLer,
                filaDeArquivosGerados[1],
                contadorBarreira,
                idArquivo,
                qtdDeLoop
        );
        Trabalhadora trabalhadora3 = new Trabalhadora(
                semIdArquivo,
                semContadorDaBarreira,
                semBarreiraEntrada,
                semBarreiraSaida,
                semBarreiraEscrever,
                semBarreiraLer,
                filaDeArquivosGerados[2],
                contadorBarreira,
                idArquivo,
                qtdDeLoop
        );
        Trabalhadora trabalhadora4 = new Trabalhadora(
                semIdArquivo,
                semContadorDaBarreira,
                semBarreiraEntrada,
                semBarreiraSaida,
                semBarreiraEscrever,
                semBarreiraLer,
                filaDeArquivosGerados[3],
                contadorBarreira,
                idArquivo,
                qtdDeLoop
        );
        Combinadora combinadora = new Combinadora(
                semBarreiraEscrever,
                semBarreiraLer,
                filaDeArquivosGerados,
                qtdDeLoop
        );
        trabalhadora1.start();
        trabalhadora2.start();
        trabalhadora3.start();
        trabalhadora4.start();
        combinadora.start();

        try {
            trabalhadora1.join();
            trabalhadora2.join();
            trabalhadora3.join();
            trabalhadora4.join();
            combinadora.join();
            System.out.println("Sistema finalizado!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
