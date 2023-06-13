import java.util.ArrayList;

public class Pedido extends Mesa {
    ArrayList<Item> pedido = new ArrayList<>();
    Boolean pedidoFinal = false;

    public void addItem(int pd, ArrayList<Item> menuList) {
        this.pedido.add(menuList.get(pd - 1));
        this.setTotalPedido();
    }

    public void remov_item(int pd, ArrayList<Item> menuList) {
        for (int i = 0; i < this.pedido.size(); i++) {
            if (menuList.get(pd - 1).getName() == this.pedido.get(i).getName()) {
                this.pedido.remove(i);
                setTotalPedido();
                Uteis.limpa_term();
                System.out.println("\n1 " + menuList.get(pd - 1).getName() + " foi removido do pedido!\n");
                break;
            } else if (i == this.pedido.size() - 1
                    && menuList.get(pd - 1).getName() != this.pedido.get(i).getName()) {
                Uteis.limpa_term();
                System.out.println("\nNão existe esse item no pedido!\n");
            }
        }
    }

    public void setTotalPedido() {
        this.totalPedido = 0;

        for (int j = 0; j < this.pedido.size(); j++) {
            this.totalPedido += this.pedido.get(j).getValue();
        }
    }

    public double getTotalPedido() {
        return this.totalPedido;
    }

    public String exibPedi(ArrayList<Item> menuList) {
        StringBuilder pedido = new StringBuilder();

        pedido.setLength(0);
        if (this.pedido.size() != 0) {
            for (int i = 0; i < this.pedido.size(); i++) {
                String preco = Uteis.format_prec(this.pedido.get(i).getValue());
                boolean unicidade = true;
                String num_name = (i + 1) + " - " + this.pedido.get(i).getName();
                int tam = 30 - num_name.length() + 10;

                if (i >= 0) {
                    for (int j = 0; j != i; j++) {
                        if (this.pedido.get(i).getName() == this.pedido.get(j)
                                .getName()) {

                            unicidade = false;
                        }
                    }
                    if (unicidade) {
                        int qnt = quantity(this.pedido.get(i).getName());

                        pedido.append(qnt + "x " + this.pedido.get(i).getName() + Uteis.dots(tam) + "R$"
                                + preco + "\n");
                    }
                }
            }
            pedido.append(Uteis.space_bet(48, "\nTotal:", "R$" + Uteis.format_prec(this.totalPedido) + "\n"));
        } else {
            pedido.append("\nPedido vazio\n");
        }
        return pedido.toString();
    }

    public void cancelPedido() {
        this.pedido.clear();

        System.out.println("\nPedido cancelado!\n");
    }

    public int quantity(String item) {
        int qtd = 0;
        for (int i = 0; i < this.pedido.size(); i++) {
            if (item == this.pedido.get(i).getName()) {
                qtd++;
            }
        }
        return qtd;
    } 
}