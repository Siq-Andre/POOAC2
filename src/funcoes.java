import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class funcoes {

    //funçao para cadastrar os dados de um novo cliente
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

        //verificação se o cliente é pessoa fisica ou juridica. Se o documento possui mais de 8 digitos, é um CNPJ
        if (documento.length() > 8){
            PessoaJuridica pj = new PessoaJuridica(nome, documento, endereco);
            String razaoSocial = JOptionPane.showInputDialog("Digite a razão social da empresa: ");
            int prazoMaxPagamento = Integer.parseInt(JOptionPane.showInputDialog("Digite o prazo máximo de pagamento da empresa (em dias): "));
            salvar(pj);
        }
        //se nao for um CNPJ, então é um CPF
        else{
            PessoaFisica pf = new PessoaFisica(nome, documento, endereco);
            int qtdeMaxParcelas = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade maxima de parcelas a serem pagas: "));
            salvar(pf);
        }
        JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", "Organizações Tabajara", JOptionPane.INFORMATION_MESSAGE);
    }

    //função para salvar o cliente caso seja pessoa juridica
    private static void salvar(PessoaJuridica pj) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("clientes.txt", true))) {
            String endereco = pj.enderecoCliente.paraString();
            // Append no arquivo (true como segundo argumento para FileWriter)
            writer.write(pj.nome + ", " + pj.getDocumento() + ", " + pj.getRazaoSocial() + ", " +pj.getPrazoMaxPagamento() + ", " + endereco);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar cliente no arquivo.", "Sistema de Gestão", JOptionPane.ERROR_MESSAGE);
        }
    }

    //função para salvar o cliente caso seja pessoa fisica
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


    public static void cadastrarProduto(){
        String codigo = JOptionPane.showInputDialog("Digite o código do produto:");
        String nome = JOptionPane.showInputDialog("Digite o nome do produto:");
        String descricao = JOptionPane.showInputDialog("Digite a descricao do produto: ");
        double preco = Double.parseDouble(JOptionPane.showInputDialog("Digite o preco do produto: "));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
        LocalDateTime validade = LocalDateTime.parse(JOptionPane.showInputDialog("Digite a validade do produto se houver: "));
        Produto produto = new Produto(codigo, nome, descricao, preco, validade);
        salvar(produto);
    }

    private static void salvar(Produto produto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("produtos.txt", true))) {
            // Append no arquivo (true como segundo argumento para FileWriter)
            writer.write(produto.getCodigo() + ", " + produto.getNome() + ", " + produto.getDescricao() + ", " + produto.getPreco() + ", " + produto.getValidade());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar produto no arquivo.", "Sistema de Gestão", JOptionPane.ERROR_MESSAGE);
        }
    }
}
