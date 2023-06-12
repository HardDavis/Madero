import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Mesa {
    Scanner scan = new Scanner(System.in);
    boolean status = true;
    private double totalPedido;
    ArrayList<Item> pedido = new ArrayList<Item>();

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

    public void mudaStts(int opt_me, ArrayList<Mesa> mesaList) {
        if (this.status) {
            mesaList.get(opt_me - 1).status = false;
        } else {
            mesaList.get(opt_me - 1).status = true;
        }
    }

    public void addItem(int pd, int opt_me, ArrayList<Item> menuList, ArrayList<Mesa> mesaList) {
        mesaList.get(opt_me - 1).pedido.add(menuList.get(pd - 1));
        mesaList.get(opt_me - 1).setTotalPedido(opt_me, mesaList);
    }

    public void remov_item(int pd, int opt_me, ArrayList<Item> menuList, ArrayList<Mesa> mesaList) {
        for (int i = 0; i < mesaList.get(opt_me - 1).pedido.size(); i++) {
            if (menuList.get(pd - 1).getName() == mesaList.get(opt_me - 1).pedido.get(i).getName()) {
                mesaList.get(opt_me - 1).pedido.remove(i);
                limpa_term();
                System.out.println("\n1 " + menuList.get(pd - 1).getName() + " foi removido do pedido!\n");
                break;
            } else if (i == mesaList.get(opt_me - 1).pedido.size() - 1
                    && menuList.get(pd - 1).getName() != mesaList.get(opt_me - 1).pedido.get(i).getName()) {
                limpa_term();
                System.out.println("\nNão existe esse item no pedido!\n");
            }
        }
    }

    public void setTotalPedido(int opt_me, ArrayList<Mesa> mesaList) {
        mesaList.get(opt_me - 1).totalPedido = 0;

        for (int j = 0; j < mesaList.get(opt_me - 1).pedido.size(); j++) {
            mesaList.get(opt_me - 1).totalPedido += mesaList.get(opt_me - 1).pedido.get(j).getValue();
        }
    }

    public double getTotalPedido(int opt_me, ArrayList<Mesa> mesaList) {
        return mesaList.get(opt_me - 1).totalPedido;
    }

    public String exibPedi(int opt_me, ArrayList<Mesa> mesaList, ArrayList<Item> menuList) {
        StringBuilder pedido = new StringBuilder();

        pedido.setLength(0);
        if (mesaList.get(opt_me - 1).pedido.size() != 0) {
            for (int i = 0; i < mesaList.get(opt_me - 1).pedido.size(); i++) {
                String preco = format_prec(mesaList.get(opt_me - 1).pedido.get(i).getValue());
                boolean unicidade = true;
                String num_name = (i + 1) + " - " + mesaList.get(opt_me - 1).pedido.get(i).getName();
                int tam = 30 - num_name.length() + 10;

                if (i >= 0) {
                    for (int j = 0; j != i; j++) {
                        if (mesaList.get(opt_me - 1).pedido.get(i).getName() == mesaList.get(opt_me - 1).pedido.get(j)
                                .getName()) {

                            unicidade = false;
                        }
                    }
                    if (unicidade) {
                        int qnt = quantity(opt_me, mesaList, mesaList.get(opt_me - 1).pedido.get(i).getName());

                        pedido.append(qnt + "x " + mesaList.get(opt_me - 1).pedido.get(i).getName() + dots(tam) + "R$"
                                + preco + "\n");
                    }
                }
            }
            pedido.append(space_bet(48, "\nTotal:", "R$" + format_prec(mesaList.get(opt_me - 1).totalPedido) + "\n"));
            /* pedido.append("\nTotal: " + "R$" + format_prec(mesaList.get(opt_me - 1).totalPedido)  + "\n"); */
        } else {
            pedido.append("\nPedido vazio\n");
        }
        System.out.println(pedido);
        return pedido.toString();
    }

    public void cancelPedido(int opt_me, ArrayList<Mesa> mesaList) {
        mesaList.get(opt_me - 1).pedido.clear();
        System.out.println("\nPedido cancelado!\n");
    }

    public boolean fecharConta(int opt_me, ArrayList<Mesa> mesaList, ArrayList<Item> menuList) {
        int form_pag;
        boolean pag_stts;
        String valor = format_prec(getTotalPedido(opt_me, mesaList));

        exibPedi(opt_me, mesaList, menuList);

        do {
            System.out.print("\nFORMAS DE PAGAMENTO:\n" +
                    "   1. Dinheiro\n" +
                    "   2. Cartão\n" +
                    "   3. Pix\n" +
                    "\n0. Voltar\n" +
                    "\nSelecione a forma de pagamento: ");
            form_pag = scan.nextInt();

            if (form_pag == 1) {
                limpa_term();
                String pagName = "Dinheiro";
                System.out.print("FORMA DE PAGAMENTO: DINHEIRO\n");

                System.out.println("Valor: R$" + valor);
                form_pag = confirma_pag(pagName, form_pag, opt_me, mesaList);

            } else if (form_pag == 2) {
                int car_tip;

                while (true) {
                    limpa_term();
                    System.out.print("FORMA DE PAGAMENTO: CARTÃO\n");

                    System.out.print("   1. Débito\n" +
                            "   2. Crédito\n" +
                            "\n0. Voltar\n" +
                            "\nSelecione o tipo do cartão: ");
                    car_tip = scan.nextInt();

                    if (car_tip == 1) {
                        limpa_term();
                        String pagName = "Cartão Débito";
                        System.out.print("FORMA DE PAGAMENTO: Cartão Débito\n");
                        System.out.println("Valor: R$" + valor);
                        form_pag = confirma_pag(pagName, form_pag, opt_me, mesaList);
                        if (form_pag == 0 || form_pag == 10) {
                            break;
                        }

                    } else if (car_tip == 2) {
                        limpa_term();
                        String pagName = "Cartão Crédito";
                        System.out.print("FORMA DE PAGAMENTO: Cartão Crédito\n");
                        System.out.println("Valor: R$" + valor);
                        form_pag = confirma_pag(pagName, form_pag, opt_me, mesaList);
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
                limpa_term();
                String pagName = "PIX";
                System.out.print("FORMA DE PAGAMENTO: PIX\n");

                System.out.println("Valor: R$" + valor);
                System.out.println("Chave pix: theflavor@gmail.com");

                form_pag = confirma_pag(pagName, form_pag, opt_me, mesaList);

            } else {
                limpa_term();
                System.out.println("Opção Inválida.\n");
            }

        } while (form_pag != 0 && form_pag != 10);
        if (form_pag == 0) {
            pag_stts = false;
        } else {
            pag_stts = true;
        }
        limpa_term();
        return pag_stts;
    }

    private int confirma_pag(String pagName, int form_pag, int opt_me, ArrayList<Mesa> mesaList) {
        String confirma;
        do {
            System.out.print("\nDigite \"PAGAR\" para confirmar o pagamento ou \"CANCELAR\" para voltar: ");
            confirma = scan.next();

            if ("PAGAR".equals(confirma.toUpperCase().trim())) {
                limpa_term();
                System.out.println("Pagamento realizado");
                System.out.println("Deseja emitir a Nota Fiscal?\n" +
                        "     1. Sim\n" +
                        "     2. Não");
                System.out.print("\nDigite: ");
                int exbiNF = scan.nextInt();
                if (exbiNF == 1) {
                    String a = "";
                    while (a == "") {
                        System.out.println(mesaList.get(opt_me - 1).nota_fiscal(opt_me, pagName, mesaList, pedido));
                        System.out.print("\nDigite qualquer coisa para sair: ");
                        a = scan.next();
                    }
                }
                mesaList.get(opt_me - 1).pedido.clear();
                mesaList.get(opt_me - 1).mudaStts(opt_me, mesaList);
                form_pag = 10;

            } else if ("CANCELAR".equals(confirma.toUpperCase().trim())) {
                form_pag = 0;
            } else {
                System.out.print("\nOpção inválida.");
            }
        } while (!"PAGAR".equals(confirma.toUpperCase().trim()) && !"CANCELAR".equals(confirma.toUpperCase().trim()));

        return form_pag;
    }

    public String nota_fiscal(int opt_me, String pagName, ArrayList<Mesa> mesaList, ArrayList<Item> menuList) {

        LocalDateTime dataHoraAtual = LocalDateTime.now();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy\t\t\t      HH:mm:ss");
        String dataHoraFormatada = dataHoraAtual.format(formatador);
        
        StringBuffer notinha = new StringBuffer();
        
        String line = "----------------------------------------------\n";
        int tam = line.length();

        notinha.append(space_cen(tam, "The Flavor") + line);
        notinha.append(exibPedi(opt_me, mesaList, menuList) + line);
        notinha.append(space_bet(tam-1, pagName, "R$" + format_prec(mesaList.get(opt_me - 1).getTotalPedido(opt_me, mesaList))));
        notinha.append("\n" + dataHoraFormatada);

        return notinha.toString();
    }
    
    private String space_bet(int tam, String a, String b){
        int space_bet = (tam - a.length() - b.length());
        String frm_str = String.format("%s" + "%" + space_bet + "s" + "%s", a, "", b);
        
        return frm_str;
    }

    private String space_cen(int tam, String a){
        int space_cen = (tam - a.length()) / 2;
        String frm_str = String.format("%" + space_cen + "s%s%" + space_cen + "s" + "\n", "", a, "");
        
        return frm_str;
    }

    private static String dots(int tamanho) {
        StringBuilder sequencia = new StringBuilder();
        for (int i = 0; i < tamanho; i++) {
            sequencia.append(".");
        }
        return sequencia.toString();
    }

    private int quantity(int opt_me, ArrayList<Mesa> mesaList, String item) {
        int qtd = 0;
        for (int i = 0; i < mesaList.get(opt_me - 1).pedido.size(); i++) {
            if (item == mesaList.get(opt_me - 1).pedido.get(i).getName()) {
                qtd++;
            }
        }
        return qtd;
    }

    private static void limpa_term() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private String format_prec(double val) {
        String preco = (val < 10.00) ? String.format("0%.2f", val) : String.format("%.2f", val);

        return preco;
    }
}