public class Uteis {
    public static void limpa_term() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static String format_prec(double val) {
        String preco = (val < 10.00) ? String.format("0%.2f", val) : String.format("%.2f", val);

        return preco;
    }

    public static String dots(int tamanho) {
        StringBuilder sequencia = new StringBuilder();
        for (int i = 0; i < tamanho; i++) {
            sequencia.append(".");
        }
        return sequencia.toString();
    }

    public static String space_bet(int tam, String a, String b) {
        int space_bet = (tam - a.length() - b.length());
        String frm_str = String.format("%s" + "%" + space_bet + "s" + "%s", a, "", b);

        return frm_str;
    }

    public static String space_cen(int tam, String a) {
        int space_cen = (tam - a.length()) / 2;
        String frm_str = String.format("%" + space_cen + "s%s%" + space_cen + "s" + "\n", "", a, "");

        return frm_str;
    }
}
