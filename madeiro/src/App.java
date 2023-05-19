import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {

        Item hamburger = new Item("hamburger", "Pão, carne 100g, queijo cheedar.", 34, true);
        Item batata = new Item("Batata Frita", "Batata frita no óleo quente", 9.49, true);
        ArrayList<Item> menuList = new ArrayList<Item>();
        menuList.add(hamburger);
        menuList.add(batata);

        for (int i = 0; i < menuList.size(); i++) {
            System.out.println(menuList.get(i));
          }
    }
}

