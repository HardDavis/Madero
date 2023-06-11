import java.util.ArrayList;
import java.util.Scanner;

public class Mesa {
    boolean status = true;
    private double totalPedido;
    ArrayList<Item> pedido = new ArrayList<Item>();
    Scanner scan = new Scanner(System.in);

    public void cria_mesas(ArrayList<Mesa> mesaList) {
        for (int i = 0; i < 4; i++) {
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
    }

    public void remov_item(int pd, int opt_me, ArrayList<Item> menuList, ArrayList<Mesa> mesaList) {
        for (int i = 0; i < mesaList.get(opt_me - 1).pedido.size(); i++) {
            if (menuList.get(pd - 1).getName() == mesaList.get(opt_me - 1).pedido.get(i).getName()) {
                mesaList.get(opt_me - 1).pedido.remove(i);
                System.out.println("\n1 " + menuList.get(pd - 1).getName() + " foi removido do pedido!\n");
                break;
            } else if (i == mesaList.get(opt_me - 1).pedido.size() - 1
                    && menuList.get(pd - 1).getName() != mesaList.get(opt_me - 1).pedido.get(i).getName()) {
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

    public void exibPedi(int opt_me, ArrayList<Mesa> mesaList, ArrayList<Item> menuList) {
        StringBuilder pedido = new StringBuilder();
        pedido.setLength(0);
        if (mesaList.get(opt_me - 1).pedido.size() != 0) {
            for (int i = 0; i < mesaList.get(opt_me - 1).pedido.size(); i++) {
                String preco = (mesaList.get(opt_me - 1).pedido.get(i).getValue() < 10.00)
                        ? String.format("0%.2f", mesaList.get(opt_me - 1).pedido.get(i).getValue())
                        : String.format("%.2f", mesaList.get(opt_me - 1).pedido.get(i).getValue());
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
            pedido.append("\nTotal: " + "R$" + String.format("%.2f", mesaList.get(opt_me - 1).totalPedido) + "\n");
        } else {
            pedido.append("\nPedido vazio\n");
        }
        System.out.println(pedido);
    }
    
    public void cancelPedido(int opt_me, ArrayList<Mesa> mesaList) {
        mesaList.get(opt_me - 1).pedido.clear();
        System.out.println("\nPedido cancelado!\n");
    }

    public boolean fecharConta(int opt_me, ArrayList<Mesa> mesaList, ArrayList<Item> menuList) {
        int form_pag;
        boolean pag_stts;
        String valor = (getTotalPedido(opt_me, mesaList) < 10) ? String.format("0%.2f", getTotalPedido(opt_me, mesaList)) : String.format("%.2f", getTotalPedido(opt_me, mesaList));

        exibPedi(opt_me, mesaList, menuList);

        do {
            System.out.print("\nFORMAS DE PAGAMENTO:\n" +
                    "1. Dinheiro\n" +
                    "2. Cartão\n" +
                    "3. Pix\n" +
                    "\n0. Voltar\n" +
                    "\nSelecione a forma de pagamento: ");
            form_pag = scan.nextInt();

            if (form_pag == 1) {
                System.out.println("\nFORMA DE PAGAMENTO: DINHEIRO\n");

                System.out.println("Valor: R$" + valor);
                form_pag = confirma_pag(form_pag, opt_me, mesaList);

            } else if (form_pag == 2) {
                int car_tip;

                while (true) {
                    System.out.println("\nFORMA DE PAGAMENTO: CARTÃO\n");

                    System.out.print("1. Débito\n" +
                            "2. Crédito\n" +
                            "\n0. Voltar\n" +
                            "\nSelecione o tipo do cartão: ");
                    car_tip = scan.nextInt();

                    if (car_tip == 1) {
                        System.out.println("\nFORMA DE PAGAMENTO: Cartão Débito\n");
                        System.out.println("Valor: R$" + valor);
                        form_pag = confirma_pag(form_pag, opt_me, mesaList);
                        if (form_pag == 0 || form_pag == 10) {
                            break;
                        }

                    } else if (car_tip == 2) {
                        System.out.println("\nFORMA DE PAGAMENTO: Cartão Crédito\n");
                        System.out.println("Valor: R$" + valor);
                        form_pag = confirma_pag(form_pag, opt_me, mesaList);
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
                System.out.println("\nFORMA DE PAGAMENTO: PIX\n");

                System.out.println("Valor: R$" + valor);
                System.out.println("Chave pix: theflavor@gmail.com");

                form_pag = confirma_pag(form_pag, opt_me, mesaList);

            } else {
                System.out.println("Opção Inválida.\n");
            }

        } while (form_pag != 0 && form_pag != 10);
        if (form_pag == 0) {
            pag_stts = false;
        } else {
            pag_stts = true;
        }
        return pag_stts;
    }

    private int confirma_pag(int form_pag, int opt_me, ArrayList<Mesa> mesaList) {
        String confirma;
        do {
            System.out.print("Digite \"PAGAR\" para confirmar o pagamento ou \"CANCELAR\" para voltar: ");
            confirma = scan.next();

            if ("PAGAR".equals(confirma.toUpperCase())) {
                mesaList.get(opt_me - 1).pedido.clear();
                mesaList.get(opt_me - 1).mudaStts(opt_me, mesaList);
                System.out.println("Pagamento realizado");
                form_pag = 10;
            } else if ("CANCELAR".equals(confirma.toUpperCase())) {
                form_pag = 0;
            } else {
                System.out.print("Opção inválida. ");
            }
        } while (!"PAGAR".equals(confirma.toUpperCase()) && !"CANCELAR".equals(confirma.toUpperCase()));

        return form_pag;
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
}