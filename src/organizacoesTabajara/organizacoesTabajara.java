package organizacoesTabajara;

import javax.swing.JOptionPane;

import static organizacoesTabajara.controller.ClienteController.*;
import static organizacoesTabajara.controller.CompraController.Pagar;
import static organizacoesTabajara.controller.CompraController.efetuarCompra;
import static organizacoesTabajara.controller.ProdutoController.cadastrarProduto;
import static organizacoesTabajara.controller.ProdutoController.produtoVencido;
import static organizacoesTabajara.controller.CompraController.listarCompras;
import static organizacoesTabajara.controller.CompraController.buscarCompra;

public class organizacoesTabajara {

    public static void main(String[] args) {
        int opcao;
        do {
            opcao = exibirMenu();
            executarOpcao(opcao);
        } while (opcao != 8);
    }

    private static int exibirMenu() {
        String menu = "Sistema de Gestão\n" +
                "1. Cadastrar Cliente\n" +
                "2. Deletar Cliente por CPF ou CNPJ\n" +
                "3. Deletar Cliente por Nome\n" +
                "4. Cadastrar Produto\n" +
                "5. Efetuar Compra\n" +
                "6. Atualizar Situação de Pagamento\n" +
                "7. Relatórios\n" +
                "   (a) Clientes por Nome\n" +
                "   (b) Todos os Produtos\n" +
                "   (c) Buscar Produto por Nome\n" +
                "   (d) Produtos Perecíveis Vencidos\n" +
                "   (e) Todas as Compras\n" +
                "   (f) Buscar Compra por Número\n" +
                "   (g) Compras não Pagas\n" +
                "   (h) 10 últimas Compras Pagas\n" +
                "   (i) Compra mais Cara\n" +
                "   (j) Compra mais Barata\n" +
                "   (k) Valor Total de Compras por Mês nos últimos 12 meses\n" +
                "8. Sair";

        String opcaoStr = JOptionPane.showInputDialog(null, menu, "Organizações Tabajara", JOptionPane.PLAIN_MESSAGE);
        return Integer.parseInt(opcaoStr);
    }

    private static void executarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarCliente();
                break;
            case 2:
                deletarClientePorDocumento();
                break;
            case 3:
                deletarClientePorNome();
                break;
            case 4:
                cadastrarProduto();
                break;
            case 5:
                efetuarCompra();
                break;
            case 6:
                Pagar();
                break;
            case 7:
                //exibirRelatorios();

                //funções para serem chamadas no exibir relatorio
                //buscarCliente(); //(a)
                //listarProdutos(); //(b)
                //buscarProduto();  //(c)
                //produtoVencido(); //(d)
                //listarCompras(); //(e)
                //buscarCompra(); //(f)
                break;
            case 8:
                JOptionPane.showMessageDialog(null, "Sistema encerrado.", "Organizações Tabajara", JOptionPane.INFORMATION_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.", "Organizações Tabajara", JOptionPane.ERROR_MESSAGE);
        }
    }
}

