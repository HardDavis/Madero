import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        Mesa mesas = new Mesa();
        Menu menu = new Menu();
        int opt, opt_me;

        ArrayList<Mesa> mesaList = new ArrayList<>();
        ArrayList<Item> menuList = new ArrayList<>();

        mesas.cria_mesas(mesaList);
        menu.cria_itens(menuList);

        ArrayList<String> options = new ArrayList<>();
        options.add("1. Consultar menu");
        // options.add("1.1. Hamburgers")
        // options.add("1.2. Bebidas")
        // options.add("1.3. Aperitivos")

        options.add("2. Fazer pedido/abrir conta");
        // options.add("2.1. Adicionar item");
        // options.add("2.1.1. Adicionais");
        // options.add("2.2. Remover item");
        // options.add("2.3 Finalizar pedido");
        // options.add("2.4 Cancelar pedido");
        // options.add("2.5 Voltar");

        options.add("3. Verificar pedido");
        options.add("4. Fechar conta");
        // options.add("4.1. Pagar conta");
        // options.add("4.1.2 Selecionar opção de pagamento")
        // options.add("4.1.2.1 PIX")
        // options.add("4.1.2.2 Dinheiro")
        // options.add("4.1.2.3 Cartão")
        // options.add("4.1.2.3.2 Crédito")
        // options.add("4.1.2.3.3 Debito")
        // options.add("4.1.X. Emitir nota");
        // options.add("4.2 Voltar");
        options.add("0. Voltar");

        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            mesas.exibir_mesas(mesaList);

            System.out.print("\nEscolha a mesa: ");
            opt_me = scan.nextInt();

            System.out.print("\033[H\033[2J");
            System.out.flush();

            boolean aux;

            if (opt_me != 0) {
                aux = true;

                do {
                    for (int i = 0; i < options.size(); i++) {
                        System.out.println(options.get(i));
                    }

                    System.out.print("\nEscolha uma opção: ");
                    opt = scan.nextInt();

                    // SELEÇÃO DAS OPÇÕES A PARTIR DAQUI
                    if (opt == 1) { // --> 1. EXIBE MENU

                        do {
                            System.out.print("\nMENU:\n");
                            menu.exibir_menu(menuList);
                            System.out.println("\n0 - Voltar");

                            System.out.print("\nEscolha um item: ");
                            opt = scan.nextInt();
                            if (opt != 0) {// --> EXIBI AS INFORÇÕES DO ITEM SELECIONADO
                                System.out.println(menuList.get(opt - 1));
                            }
                        } while (opt != 0);

                    } else if (opt == 2) { // --> 2. FAZER PEDIDO/ABRIR CONTA
                        int pd;
                        do {
                            System.out.println(
                                    "1. Adicionar item\n2. Remover item\n3. Finalizar pedido\n4. Cancelar pedido\n5. Voltar");

                            // options.add("2.1. Adicionar item");
                            // options.add("2.2. Remover item");
                            // options.add("2.3 Finalizar pedido");
                            // options.add("2.4 Cancelar pedido");
                            // options.add("2.5 Voltar");

                            System.out.print("\nEscolha uma opção: ");
                            opt = scan.nextInt();

                            if (opt == 1) { // 1. ADICIONA ITEM
                                do {
                                    menu.exibir_menu(menuList);

                                    System.out.print("\nEscolha o item a ser adicionado ao pedido: ");
                                    pd = scan.nextInt();

                                    if (pd != 0) {
                                        mesas.addItem(opt, opt_me, menuList, mesaList);
                                    }
                                } while (pd != 0);

                                mesas.exibPedi(opt_me, mesaList); // mostrar lista

                            } else if (opt == 2) { // 2. REMOVE ITEM
                                System.out.print("Qual item deseja remover: ");
                                pd = scan.nextInt();

                                mesas.remov_item(pd);
                                mesas.exibPedi(opt_me, mesaList); // mostrar lista

                            } else if (opt == 3) { // 3. FINALIZAR PEDIDO

                            } else if (opt == 4) { // 4. CANCELAR PEDIDO

                            } else if (opt > 4 || opt < 0) { // OPÇÕES INVÁLIDAS
                                System.out.println("Opção inválida");
                            }

                        } while (opt != 0);

                    } else if (opt == 3) { // --> 3. VERIFICAR PEDIDOS.
                        mesas.exibPedi(opt_me, mesaList);

                    } else if (opt == 4) { // --> 4. FECHAR CONTA.

                    } else if (opt == 0) { // --> VOLTAR PARA SELEÇÃO DE MESA.
                        aux = false;
                    }
                } while (aux);
            }

        } while (opt_me != 0); // --> ENCERRAR PROGRAMA

        scan.close();
    }
}