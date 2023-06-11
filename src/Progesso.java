import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Progesso {
    public void tempo() {
         // Obtém a data e hora atual
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        
        // Define um formato desejado
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        
        // Formata a data e hora atual
        String dataHoraFormatada = dataHoraAtual.format(formatador);
        
        // Exibe a data e hora formatada
        System.out.println("Data e hora atual: " + dataHoraFormatada);
    }

    public void progresso() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Seu pedido foi adicionado a fila de preparo.");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 3; i++) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Seu pedido está preparando.");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Seu pedido está preparando..");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Seu pedido está preparando...");

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("Seu pedido está feito!");
    }
}
