import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Mesa {
    Scanner scan = new Scanner(System.in);
    boolean status = true;
    public double totalPedido;
    ArrayList<Pedido> pedidos = new ArrayList<>();

    public void cria_mesas(ArrayList<Mesa> mesaList) {
        for (int i = 0; i < 5; i++) {
            mesaList.add(new Mesa());
        }
    }

    public void exibir_mesas(ArrayList<Mesa> mesaList) {
        for (int i = 0; i < mesaList.size(); i++) {
            if (mesaList.get(i).status) {
                System.out.print("Mesa " + (i + 1) + " - LIVRE\n");
            } else {
                System.out.print("Mesa " + (i + 1) + " - OCUPADA\n");
            }
        }
    }

    public void mudaStts() {
        if (this.status) {
            this.status = false;
        } else {
            this.status = true;
        }
    }

    /*
     * public void addItem(int pd, ArrayList<Item> menuList) {
     * this.pedido.add(menuList.get(pd - 1));
     * this.setTotalPedido();
     * }
     * 
     * public void remov_item(int pd, ArrayList<Item> menuList) {
     * for (int i = 0; i < this.pedido.size(); i++) {
     * if (menuList.get(pd - 1).getName() == this.pedido.get(i).getName()) {
     * this.pedido.remove(i);
     * Uteis.limpa_term();
     * System.out.println("\n1 " + menuList.get(pd - 1).getName() +
     * " foi removido do pedido!\n");
     * break;
     * } else if (i == this.pedido.size() - 1
     * && menuList.get(pd - 1).getName() != this.pedido.get(i).getName()) {
     * Uteis.limpa_term();
     * System.out.println("\nNão existe esse item no pedido!\n");
     * }
     * }
     * }
     * 
     * public void setTotalPedido() {
     * this.totalPedido = 0;
     * 
     * for (int j = 0; j < this.pedido.size(); j++) {
     * this.totalPedido += this.pedido.get(j).getValue();
     * }
     * }
     * 
     * public double getTotalPedido() {
     * return this.totalPedido;
     * }
     * 
     * public String exibPedi(ArrayList<Item> menuList) {
     * StringBuilder pedido = new StringBuilder();
     * 
     * pedido.setLength(0);
     * if (this.pedido.size() != 0) {
     * for (int i = 0; i < this.pedido.size(); i++) {
     * String preco = Uteis.format_prec(this.pedido.get(i).getValue());
     * boolean unicidade = true;
     * String num_name = (i + 1) + " - " + this.pedido.get(i).getName();
     * int tam = 30 - num_name.length() + 10;
     * 
     * if (i >= 0) {
     * for (int j = 0; j != i; j++) {
     * if (this.pedido.get(i).getName() == this.pedido.get(j)
     * .getName()) {
     * 
     * unicidade = false;
     * }
     * }
     * if (unicidade) {
     * int qnt = quantity(this.pedido.get(i).getName());
     * 
     * pedido.append(qnt + "x " + this.pedido.get(i).getName() + Uteis.dots(tam) +
     * "R$"
     * + preco + "\n");
     * }
     * }
     * }
     * pedido.append(Uteis.space_bet(48, "\nTotal:", "R$" +
     * Uteis.format_prec(this.totalPedido) + "\n"));
     * } else {
     * pedido.append("\nPedido vazio\n");
     * }
     * return pedido.toString();
     * }
     * 
     * public void cancelPedido() {
     * this.pedido.clear();
     * System.out.println("\nPedido cancelado!\n");
     * }
     */

    /*
     * public void criaPedido(int pd, ArrayList<Item> menuList){
     * pedido.add(new Pedido());
     * 
     * pedido.get(0).addItem(pd, menuList);
     * }
     */

    public void setTotalPedido() {
        this.totalPedido = 0;

        for (Pedido ped : pedidos) {
            for (int i = 0; i < ped.pedido.size(); i++) {
                this.totalPedido += ped.pedido.get(i).getValue();
            }
        }
    }


    public double getTotalPedido() {
        return this.totalPedido;
    }
    

    public String mostraPedidos(ArrayList<Item> menuList) {
        StringBuilder MPedidos = new StringBuilder();
        for (Pedido ped : pedidos) {
            MPedidos.append(ped.exibPedi(menuList) + "\n");
        }

        return MPedidos.toString();
    }

    public boolean fecharConta(ArrayList<Item> menuList) {
        int form_pag;
        boolean pag_stts;
        String valor = Uteis.format_prec(getTotalPedido());

        mostraPedidos(menuList);

        do {
            System.out.print("\nFORMAS DE PAGAMENTO:\n" +
                    "   1. Dinheiro\n" +
                    "   2. Cartão\n" +
                    "   3. Pix\n" +
                    "\n0. Voltar\n" +
                    "\nSelecione a forma de pagamento: ");
            form_pag = scan.nextInt();

            if (form_pag == 1) {
                Uteis.limpa_term();
                String pagName = "Dinheiro";
                System.out.print("FORMA DE PAGAMENTO: DINHEIRO\n");

                System.out.println("Valor: R$" + valor);
                form_pag = confirma_pag(pagName, form_pag, menuList);

            } else if (form_pag == 2) {
                int car_tip;

                while (true) {
                    Uteis.limpa_term();
                    System.out.print("FORMA DE PAGAMENTO: CARTÃO\n");

                    System.out.print("   1. Débito\n" +
                            "   2. Crédito\n" +
                            "\n0. Voltar\n" +
                            "\nSelecione o tipo do cartão: ");
                    car_tip = scan.nextInt();

                    if (car_tip == 1) {
                        Uteis.limpa_term();
                        String pagName = "Cartão Débito";
                        System.out.print("FORMA DE PAGAMENTO: Cartão Débito\n");
                        System.out.println("Valor: R$" + valor);
                        form_pag = confirma_pag(pagName, form_pag, menuList);
                        if (form_pag == 0 || form_pag == 10) {
                            break;
                        }

                    } else if (car_tip == 2) {
                        Uteis.limpa_term();
                        String pagName = "Cartão Crédito";
                        System.out.print("FORMA DE PAGAMENTO: Cartão Crédito\n");
                        System.out.println("Valor: R$" + valor);
                        form_pag = confirma_pag(pagName, form_pag, menuList);
                        if (form_pag == 0 || form_pag == 10) {
                            break;
                        }

                    } else if (car_tip < 0 || car_tip > 2) {
                        System.out.println("Opção inválida");
                    } else {
                        break;
                    }
                }

            } else if (form_pag == 3) {
                Uteis.limpa_term();
                String pagName = "PIX";
                System.out.print("FORMA DE PAGAMENTO: PIX\n");

                System.out.println("Valor: R$" + valor);
                System.out.println("Chave pix: theflavor@gmail.com");

                form_pag = confirma_pag(pagName, form_pag, menuList);

            } else {
                Uteis.limpa_term();
                System.out.println("Opção Inválida.\n");
            }

        } while (form_pag != 0 && form_pag != 10);
        if (form_pag == 0) {
            pag_stts = false;
        } else {
            pag_stts = true;
        }
        Uteis.limpa_term();
        return pag_stts;
    }

    private int confirma_pag(String pagName, int form_pag, ArrayList<Item> menuList) {
        String confirma;
        do {
            System.out.print("\nDigite \"PAGAR\" para confirmar o pagamento ou \"CANCELAR\" para voltar: ");
            confirma = scan.next();

            if ("PAGAR".equals(confirma.toUpperCase().trim())) {
                Uteis.limpa_term();
                System.out.println("Pagamento realizado\n");
                System.out.println("Deseja emitir a Nota Fiscal?\n" +
                        "     1. Sim\n" +
                        "     2. Não");
                System.out.print("\nDigite: ");
                int exbiNF = scan.nextInt();
                if (exbiNF == 1) {
                    String a = "";
                    while (a == "") {
                        System.out.println(this.nota_fiscal(pagName, menuList));
                        System.out.print("\nDigite qualquer coisa para sair: ");
                        a = scan.next();
                    }
                }
                this.pedidos.clear();
                this.mudaStts();
                form_pag = 10;

            } else if ("CANCELAR".equals(confirma.toUpperCase().trim())) {
                form_pag = 0;
            } else {
                System.out.print("\nOpção inválida.");
            }
        } while (!"PAGAR".equals(confirma.toUpperCase().trim()) && !"CANCELAR".equals(confirma.toUpperCase().trim()));

        return form_pag;
    }

    public String nota_fiscal(String pagName, ArrayList<Item> menuList) {

        LocalDateTime dataHoraAtual = LocalDateTime.now();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy\t\t\t      HH:mm:ss\n");
        String dataHoraFormatada = dataHoraAtual.format(formatador);

        StringBuffer notinha = new StringBuffer();

        String line = "----------------------------------------------\n";
        int tam = line.length();

        notinha.append("\n" + line);
        notinha.append(Uteis.space_cen(tam, "The Flavor") + line);
        notinha.append(mostraPedidos(menuList) + line);
        notinha.append(Uteis.space_bet(tam - 1, pagName,
                "R$" + Uteis.format_prec(this.getTotalPedido())));
        notinha.append("\n" + dataHoraFormatada);
        notinha.append(line);
        notinha.append(Uteis.space_cen(tam, "Volte Sempre!"));
        notinha.append(line);

        return notinha.toString();
    }

    /*
     * public int quantity(String item) {
     * int qtd = 0;
     * for (int i = 0; i < this.pedido.size(); i++) {
     * if (item == this.pedido.get(i).getName()) {
     * qtd++;
     * }
     * }
     * return qtd;
     * }
     */
}