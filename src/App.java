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

        /*
         * while (true) {
         * 
         * ArrayList<String> options = new ArrayList<>();
         * options.add("1. Consultar menu");
         * // options.add("1.1. Hamburgers")
         * // options.add("1.2. Bebidas")
         * // options.add("1.3. Aperitivos")
         * 
         * options.add("2. Fazer pedido/abrir conta");
         * // options.add("2.1. Adicionar item");
         * // options.add("2.1.1. Adicionais");
         * // options.add("2.2. Remover item");
         * // options.add("2.3 Finalizar pedido");
         * // options.add("2.4 Cancelar pedido");
         * // options.add("2.5 Voltar");
         * 
         * options.add("3. Verificar pedido");
         * options.add("4. Fechar conta");
         * // options.add("4.1. Pagar conta");
         * // options.add("4.1.2 Selecionar opção de pagamento")
         * // options.add("4.1.2.1 PIX")
         * // options.add("4.1.2.2 Dinheiro")
         * // options.add("4.1.2.3 Cartão")
         * // options.add("4.1.2.3.2 Crédito")
         * // options.add("4.1.2.3.3 Debito")
         * // options.add("4.1.X. Emitir nota");
         * // options.add("4.2 Voltar");
         * options.add("0. Voltar");
         * }
         */

        String opcoes = "1. Consultar menu\n" +
                "2. Fazer pedido/abrir conta\n" +
                "3. Verificar pedido\n" +
                "4. Fechar conta\n" +
                "0. Voltar";

        do {
            System.out.print("\033[H\033[2J"); // LIMPA TERMINAL
            System.out.flush();

            mesas.exibir_mesas(mesaList); // EXIBE MESAS

            System.out.print("\nEscolha a mesa: "); // ESCOLHA DE MESA
            opt_me = scan.nextInt();

            System.out.print("\033[H\033[2J"); // LIMPA TERMINAL
            System.out.flush();

            if (opt_me != 0) {
                boolean aux = true;

                do {
                    System.out.println(opcoes);

                    System.out.print("\nEscolha uma opção: ");
                    opt = scan.nextInt();

                    // SELEÇÃO DAS OPÇÕES A PARTIR DAQUI
                    if (opt == 1) { // --> 1. EXIBE MENU
                        do {
                            System.out.print("\nMENU:\n");
                            menu.exibir_menu(menuList);

                            System.out.print(
                                    "\nDigite o número do item para mostrar suas informações ou 0 para voltar: ");
                            opt = scan.nextInt();

                            if (opt != 0) {// --> EXIBI AS INFORÇÕES DO ITEM SELECIONADO
                                System.out.println(menuList.get(opt - 1));
                            }

                        } while (opt != 0);

                    } else if (opt == 2) { // --> 2. FAZER PEDIDO/ABRIR CONTA
                        int pd;
                        do {
                            System.out.println(
                                    "1. Adicionar item\n2. Remover item\n3. Finalizar pedido\n4. Cancelar pedido\n\n0 - Voltar");

                            System.out.print("\nEscolha uma opção: ");
                            opt = scan.nextInt();

                            if (opt == 1) { // 1. ADICIONA ITEM
                                do {
                                    menu.exibir_menu(menuList);

                                    System.out.print("\nEscolha o item a ser adicionado ao pedido: ");
                                    pd = scan.nextInt();

                                    if (pd != 0) {
                                        mesas.addItem(pd, opt_me, menuList, mesaList);
                                    } else {
                                        System.out.print("\033[H\033[2J"); // LIMPA TERMINAL
                                        System.out.flush();

                                        mesas.setTotalPedido(opt_me, mesaList);

                                        System.out.println("\n");
                                        mesas.exibPedi(opt_me, mesaList);
                                    }
                                } while (pd != 0);

                            } else if (opt == 2) { // 2. REMOVE ITEM
                                mesas.exibPedi(opt_me, mesaList); // mostrar lista

                                System.out.print("Qual item deseja remover: ");
                                pd = scan.nextInt();

                                mesas.remov_item(pd, opt_me, menuList, mesaList);

                            } else if (opt == 3) { // 3. FINALIZAR PEDIDO
                                mesas.setTotalPedido(opt_me, mesaList);
                                mesas.mudaStts(opt_me, mesaList);

                                opt = 0;

                            } else if (opt == 4) { // 4. CANCELAR PEDIDO

                            } else if (opt > 4 || opt < 0) { // OPÇÕES INVÁLIDAS
                                System.out.println("Opção inválida");
                            }

                        } while (opt != 0);

                    } else if (opt == 3) { // --> 3. VERIFICAR PEDIDOS.
                        mesas.setTotalPedido(opt_me, mesaList);
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