import java.util.ArrayList;

public class Mesa {
    boolean status = true;
    double totalPedido;
    ArrayList<Item> pedido = new ArrayList<Item>();

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

    public void exibPedi(int opt_me, ArrayList<Mesa> mesaList, ArrayList<Item> menuList) {

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

                        System.out.println(
                                qnt + "x " + mesaList.get(opt_me - 1).pedido.get(i).getName() + dots(tam) + "R$"
                                        + /* mesaList.get(opt_me - 1).pedido.get(i).getValue() */preco);
                    }
                }
            }
        } else {
            System.out.println("\nAinda não foi feito nenhum pedido!");
        }
        System.out.println("\nTotal: " + "R$" + String.format("%.2f", mesaList.get(opt_me - 1).totalPedido) + "\n");
    }

    public void cancelPedido(int opt_me, ArrayList<Mesa> mesaList) {
        mesaList.get(opt_me - 1).pedido.clear();
        System.out.println("Pedido cancelado!");
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
