import java.util.ArrayList;

public class Mesa {
    boolean status;
    double totalPedido;
    ArrayList<Item> pedido = new ArrayList<Item>();

    public Mesa() {
        this.status = true;
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

    public void cria_mesas(ArrayList<Mesa> mesaList) {
        for (int i = 0; i < 4; i++) {
            mesaList.add(new Mesa());
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
        for (int j = 0; j < mesaList.get(opt_me - 1).pedido.size(); j++) {
            if (mesaList.get(opt_me - 1).pedido.get(j).getName() == menuList.get(pd - 1).getName()) {
                mesaList.get(opt_me - 1).pedido.get(j).setQnt(mesaList.get(opt_me - 1).pedido.get(j).getQnt() + 1);
                break;
            }
        }
        mesaList.get(opt_me - 1).pedido.add(menuList.get(pd - 1));
    }


    public void setTotalPedido(int opt_me, ArrayList<Mesa> mesaList) {
        mesaList.get(opt_me - 1).totalPedido = 0;

        for (int j = 0; j < mesaList.get(opt_me - 1).pedido.size(); j++) {
            mesaList.get(opt_me - 1).totalPedido += mesaList.get(opt_me - 1).pedido.get(j).getValue();
        }
    }


    public void remov_item(int pd, int opt_me, ArrayList<Item> menuList, ArrayList<Mesa> mesaList) {
        for (int j = 0; j < mesaList.get(opt_me - 1).pedido.size(); j++) {
            if (mesaList.get(opt_me - 1).pedido.get(j).getName() == menuList.get(pd - 1).getName()) {
                mesaList.get(opt_me - 1).pedido.get(j).setQnt(mesaList.get(opt_me - 1).pedido.get(j).getQnt() - 1);
                break;
            }
        }
        mesaList.get(opt_me - 1).pedido.remove(pd - 1);
    }


    public void exibPedi(int opt_me, ArrayList<Mesa> mesaList) {
        for (int i = 0; i < mesaList.get(opt_me - 1).pedido.size(); i++) {
            int qnt = 0;
            qnt = mesaList.get(opt_me - 1).pedido.get(i).getQnt();
            String num_name = (i + 1) + " - " + mesaList.get(opt_me - 1).pedido.get(i).getName();
            int tam = 30 - num_name.length() + 10;

            System.out.println(qnt + "x " + mesaList.get(opt_me - 1).pedido.get(i).getName() + dots(tam) + "R$" + mesaList.get(opt_me - 1).pedido.get(i).getValue());
        }
        System.out.println("\nTotal: " + "R$" + String.format("%.2f", mesaList.get(opt_me - 1).totalPedido) + "\n");
    }


    public static String dots(int tamanho) {
        StringBuilder sequencia = new StringBuilder();
        for (int i = 0; i < tamanho; i++) {
            sequencia.append(".");
        }
        return sequencia.toString();
    }
}
