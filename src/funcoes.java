import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public abstract class funcoes {

    public static void cadastrarCliente() {
        String nome = JOptionPane.showInputDialog("Digite o nome do cliente:");
        String documento = JOptionPane.showInputDialog("Digite o CPF ou CNPJ do cliente:");
        String rua = JOptionPane.showInputDialog("Digite a rua: ");
        String numero = JOptionPane.showInputDialog("Digite o numero: ");
        String bairro = JOptionPane.showInputDialog("Digite o bairro: ");
        String cep = JOptionPane.showInputDialog("Digite o cep: ");
        String estado = JOptionPane.showInputDialog("Digite o estado: ");
        String cidade = JOptionPane.showInputDialog("Digite a cidade: ");
        Endereco endereco = new Endereco(rua, numero, bairro, cep, cidade, estado);
        Cliente cliente = new Cliente(nome, documento, endereco);

        if (documento.length() > 8){
            PessoaJuridica pj = (PessoaJuridica)cliente;
            String razaoSocial = JOptionPane.showInputDialog("Digite a razão social da empresa: ");
            int prazoMaxPagamento = Integer.parseInt(JOptionPane.showInputDialog("Digite o prazo máximo de pagamento da empresa (em dias): "));
            salvar(pj);
        }
        else{
            PessoaFisica pf = (PessoaFisica)cliente;
            int qtdeMaxParcelas = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade maxima de parcelas a serem pagas: "));
            salvar(pf);
        }
        // Salvar os dados no arquivo

        JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", "Organizações Tabajara", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void salvar(PessoaJuridica pj) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("clientes.txt", true))) {
            String endereco = pj.enderecoCliente.paraString();
            // Append no arquivo (true como segundo argumento para FileWriter)
            writer.write(pj.nome + ", " + pj.getDocumento() + ", " + endereco);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar cliente no arquivo.", "Sistema de Gestão", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void salvar(PessoaFisica pf) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("clientes.txt", true))) {
            String endereco = pf.enderecoCliente.paraString();
            // Append no arquivo (true como segundo argumento para FileWriter)
            writer.write(pf.getNome() + ", " + pf.getDocumento() + ", " + pf.getQtdeMaxParcelas() + ", " + endereco);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar cliente no arquivo.", "Sistema de Gestão", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void salvar(Compra compra) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("baseDados/compras.txt", true))) {
            // Append no arquivo (true como segundo argumento para FileWriter)
            writer.write(compra.getDocumentoCliente() + ", " + compra.quantidade + ", " + compra.produto + ", " + compra.precoUnitario + ", " + compra.valorTotal + ", " + compra.identificador + ", " + compra.dataDeCompra);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar compra no arquivo.", "Sistema de Gestão", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void salvar(Produto produto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("baseDados/produtos.txt", true))) {
            // Append no arquivo (true como segundo argumento para FileWriter)
            writer.write(produto.getCodigo() + ", " + produto.getNome() + ", " + produto.getDescricao() + ", " + produto.getPreco() + ", " + produto.getValidade());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar produto no arquivo.", "Sistema de Gestão", JOptionPane.ERROR_MESSAGE);
        }
    }
}
