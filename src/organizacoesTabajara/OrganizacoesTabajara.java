package organizacoesTabajara;

import organizacoesTabajara.utils.TabajaraUtils;

public class OrganizacoesTabajara extends TabajaraUtils {
    public static void main(String[] args) {
        int opcao;
        do {
            opcao = exibirMenu();
            executarOpcao(opcao);
        } while (opcao != 8);
    }
}
