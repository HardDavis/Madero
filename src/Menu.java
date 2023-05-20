import java.util.ArrayList;

public class Menu{
    
    public void exibir_menu(ArrayList<Item> menuList){
        for (int i = 0; i < menuList.size(); i++) {
            System.out.println((i+1) + " - " + menuList.get(i).getName());
        }
    }

    public void item_info(int index, ArrayList<Item> menuList){
        System.out.println(menuList.get(index - 1));
    }


    public void cria_itens(ArrayList<Item> menuList){
        menuList.add(0, new Item("Porção de Batatas Fritas", 10, "Batata", "100g", true));
        menuList.add(1, new Item("CHEESEBURGER MADERO FIT", 34.40, "pão crocante integral, creme de palmito, queijo tipo cheddar, tomate e alface", "1 hamburger de 130g", true));
    }
}
