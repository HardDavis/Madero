public class Progesso {
    public static void main(String[] args) {
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
