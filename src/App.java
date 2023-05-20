import java.util.ArrayList;
import java.util.Scanner;

public class App {
    
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        Mesa mesas = new Mesa();
        Menu menu = new Menu();
        int opt, opt_me;
        
        ArrayList<Mesa> mesaList = new ArrayList<Mesa>();
        ArrayList<Item> menuList = new ArrayList<Item>();
        
        mesas.cria_mesas(mesaList);
        menu.cria_itens(menuList);
        
        ArrayList<String> options = new ArrayList<String>();
        options.add("1. Mostrar menu");
        options.add("2. Fazer pedido/abrir conta");
            //options.add("2.2. Anotar pedido");
            //    options.add("2.2.1. Informação sobre item");
            //    options.add("2.2.2. Adicionar item");
            //    options.add("2.2.3. Remover item");
            //options.add("2.3. Cancelar pedido");
            //options.add("2.4. Finalizar pedido");
        options.add("3. Verificar pedidos");
        options.add("4. Fechar conta");
            //options.add("4.1. Pagar conta");
            //options.add("4.2. Emitir nota");
        options.add("5. Voltar");

        do{
            System.out.print("\033[H\033[2J");
            System.out.flush();

            mesas.exibir_mesas(mesaList);
            System.out.print("\nEcolha a mesa: ");
            opt_me = scan.nextInt();
            boolean g;
            if(opt_me != 0){
                g = true;
                do{
                    for (int i = 0; i < options.size(); i++){
                        System.out.println(options.get(i));
                    }
                    System.out.print("\nEscolha uma opção: ");
                    opt = scan.nextInt();
    
                    if(opt == 1){
                        menu.exibir_menu(menuList);
                    }else if(opt == 5){
                        g = false;
                    }
                }while(g != false);
            }else{
                g = false;
            }

        }while(opt_me != 0);

        scan.close();
    }
}
