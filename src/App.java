import java.util.ArrayList;
import java.util.Scanner;

public class App {

    private static void limpa_term() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        Mesa mesas = new Mesa();
        Menu menu = new Menu();
/*         Progesso po = new Progesso();*/
        int opt, opt_me;

        ArrayList<Mesa> mesaList = new ArrayList<>();
        ArrayList<Item> menuList = new ArrayList<>();

        mesas.cria_mesas(mesaList);
        menu.cria_itens(menuList);

        String opcoes = "1. Consultar menu\n" +
                "2. Fazer pedido/abrir conta\n" +
                "3. Verificar pedido\n" +
                "4. Fechar conta\n" +
                "\n0. Voltar";

        do {
            limpa_term();

            mesas.exibir_mesas(mesaList); // EXIBE MESAS

            System.out.print("\nEscolha a mesa: "); // ESCOLHA DE MESA
            opt_me = scan.nextInt();

            limpa_term();

            if (opt_me != 0) {
                boolean aux = true;

                    limpa_term();
                do {
                    System.out.println(opcoes);

                    System.out.print("\nEscolha uma opção: ");
                    opt = scan.nextInt();

                    // SELEÇÃO DAS OPÇÕES A PARTIR DAQUI
                    if (opt == 1) { // --> 1. EXIBE MENU
                        limpa_term();
                        do {
                            menu.exibir_menu(menuList);

                            System.out.print(
                                    "\nDigite o número do item para mostrar suas informações ou 0 para voltar: ");
                            opt = scan.nextInt();

                            if (opt != 0) {// --> EXIBI AS INFORÇÕES DO ITEM SELECIONADO
                                limpa_term();
                                System.out.println(menuList.get(opt - 1));
                            }

                        } while (opt != 0);
                        
                    } else if (opt == 2) { // --> 2. FAZER PEDIDO/ABRIR CONTA
                        limpa_term();
                        int pd;
                        boolean volta = false;
                        
                        do {
                            mesas.exibPedi(opt_me, mesaList, menuList);

                            if (mesaList.get(opt_me - 1).pedido.size() != 0) {
                                System.out.println(
                                        "1. Adicionar item\n2. Remover item\n3. Finalizar pedido\n4. Cancelar pedido\n\n0 - Voltar");

                                System.out.print("\nEscolha uma opção: ");
                                opt = scan.nextInt();
                            } else if (mesaList.get(opt_me - 1).pedido.size() == 0 /* && !volta */){
                                opt = 1;
                            }

                            if (opt == 1) { // 1. ADICIONA ITEM
                                limpa_term();
                                do {
                                    mesas.exibPedi(opt_me, mesaList, menuList);
                                    menu.exibir_menu(menuList);

                                    System.out.print("\nEscolha o item a ser adicionado ao pedido: ");
                                    pd = scan.nextInt();
                                    limpa_term();

                                    if (pd != 0) {
                                        mesas.addItem(pd, opt_me, menuList, mesaList);
                                    } /* else {
                                        volta = true;
                                    } */
                                } while (pd != 0);

                            } else if (opt == 2) { // 2. REMOVE ITEM
                                limpa_term();
                                menu.exibir_menu(menuList);

                                
                                do {
                                    System.out.print("\nQual item deseja remover: ");
                                    pd = scan.nextInt();

                                    if (pd > 0 && pd <= menuList.size()) {
                                        mesas.remov_item(pd, opt_me, menuList, mesaList);
                                        mesas.setTotalPedido(opt_me, mesaList);
                                    } else if (pd == 0) {
                                        break;
                                    } else {
                                        System.out.println("Opção inválida.");
                                    }

                                } while (pd < 0 || pd > menuList.size());

                            } else if (opt == 3) { // 3. FINALIZAR PEDIDO
                                limpa_term();
                                mesas.setTotalPedido(opt_me, mesaList);
                                mesas.mudaStts(opt_me, mesaList);

                                opt = 0;

                            } else if (opt == 4) { // 4. CANCELAR PEDIDO
                                limpa_term();
                                mesas.cancelPedido(opt_me, mesaList);
                                mesas.setTotalPedido(opt_me, mesaList);
                                opt = 0;

                            } else if (opt > 4 || opt < 0) { // OPÇÕES INVÁLIDAS
                                limpa_term();
                                System.out.println("\nOpção inválida\n");
                            }

                        } while (opt != 0);

                    } else if (opt == 3) { // --> 3. VERIFICAR PEDIDOS.
                        limpa_term();
                        mesas.setTotalPedido(opt_me, mesaList);
                        mesas.exibPedi(opt_me, mesaList, menuList);

                    } else if (opt == 4) { // --> 4. FECHAR CONTA.
                        limpa_term();
                        boolean pag_stts = mesas.fecharConta(opt_me, mesaList, menuList);

                        if (pag_stts) {
                            aux = false;
                        }

                    } /* else if (opt == 5) {
                        po.tempo();
                    } */ else if (opt == 0) { // --> VOLTAR PARA SELEÇÃO DE MESA.
                        aux = false;
                    }
                } while (aux);
            }

        } while (opt_me != 0); // --> ENCERRAR PROGRAMA

        scan.close();
    }
}