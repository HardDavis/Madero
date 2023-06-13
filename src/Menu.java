import java.util.ArrayList;

public class Menu{

    public void exibir_menu(ArrayList<Item> menuList){
        StringBuilder menu = new StringBuilder();

        menu.append("MENU:");
        for (int i = 0; i < menuList.size(); i++) {
            String preco = (menuList.get(i).getValue() < 10.00) ? String.format("0%.2f", menuList.get(i).getValue()) : String.format("%.2f", menuList.get(i).getValue());
            String num_name = (i+1) + " - " + menuList.get(i).getName();
            int tam = 30 - num_name.length() + 10;
            menu.append("\n" + (i+1) + " - " + menuList.get(i).getName() + Uteis.dots(tam) + "R$" + preco);            
        }
        menu.append("\n\n0. Voltar");
        System.out.println(menu.toString());
    }

    public void item_info(int index, ArrayList<Item> menuList){
        System.out.println(menuList.get(index - 1));
    }

    public void cria_itens(ArrayList<Item> menuList){
        menuList.add(0, new Item("Porção de Batatas Fritas", 10, "Batata", "100g", true));
        menuList.add(1, new Item("Porção de Calabresa", 20, "Calabresa acebolada", "200g", true));
        menuList.add(2, new Item("Chesseburger Fit", 34.40, "Pão crocante integral, Creme de palmito, Queijo tipo cheddar, Tomate e alface", "1 hamburger de 130g", true));
        menuList.add(3, new Item("Laçador", 34.40, "Pão D'água, queijo prato, cebola e molho especial.", "1 hamburger de costela 130g", true));
        menuList.add(4, new Item("Fritz", 39.99, "Pão D'água, queijo prato, cebola na manteiga, rúcula e molho especial.", "1 hamburger de linguiça 130g", true));
        menuList.add(5, new Item("Garibaldi", 34.40, "Pão D'água, queijo prato, pasta de gorgonzola, provolone, cebola caramelada e shoyo.", "1 hamburger de 130g", true));
        menuList.add(6, new Item("Gringo", 34.40, "Pão D'água, queijo prato, bacon, radieei, molho campeiro.", "1 hamburger de costela 130g", true));
        menuList.add(7, new Item("Chá gelado", 08.90, "Natural, Cranberry, Limão, Maçã Verde", "440ml", true));
        menuList.add(8, new Item("Refrigerantes", 08.50, "Coca-Cola, Fanta(Laranja, Uva,) Sprite", "290ml", true));
        menuList.add(9, new Item("Suco De Fruta", 15.90, "Laranja, Uva, Limão", "440ml", true));
    }
}
