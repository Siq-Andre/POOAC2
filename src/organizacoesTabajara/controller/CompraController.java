package organizacoesTabajara.controller;

import organizacoesTabajara.Cliente.PessoaFisica;
import organizacoesTabajara.Cliente.PessoaJuridica;
import organizacoesTabajara.compra.Compra;
import organizacoesTabajara.endereco.Endereco;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static organizacoesTabajara.controller.PjController.salvar;

public class CompraController {


    private static void salvar(Compra compra) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("baseDados/compras.txt", true))) {
            // Append no arquivo (true como segundo argumento para FileWriter)
            writer.write(compra.getDocumentoCliente() + ", " + compra.getQuantidade() + ", " + compra.getProduto() + ", " + compra.getPrecoUnitario() + ", " + compra.getValorTotal() + ", " + compra.getIdentificador() + ", " + compra.getDataDeCompra());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar compra no arquivo.", "Sistema de Gestão", JOptionPane.ERROR_MESSAGE);
        }
    }

    /*private static void efetuarCompra(){

        String nome = JOptionPane.showInputDialog("Digite o nome do cliente:");
        String documento = JOptionPane.showInputDialog("Digite o CPF ou CNPJ do cliente:");
        String rua = JOptionPane.showInputDialog("Digite a rua: ");
        String numero = JOptionPane.showInputDialog("Digite o numero: ");
        String bairro = JOptionPane.showInputDialog("Digite o bairro: ");
        String cep = JOptionPane.showInputDialog("Digite o cep: ");
        String estado = JOptionPane.showInputDialog("Digite o estado: ");
        String cidade = JOptionPane.showInputDialog("Digite a cidade: ");
        Endereco endereco = new Endereco(rua, numero, bairro, cep, cidade, estado);

        //verificação se o cliente é pessoa fisica ou juridica. Se o documento possui mais de 11 digitos, é um CNPJ
        if (documento.length() > 11){
            PessoaJuridica pj = new PessoaJuridica(nome, documento, endereco);
            String razaoSocial = JOptionPane.showInputDialog("Digite a razão social da empresa: ");
            int prazoMaxPagamento = Integer.parseInt(JOptionPane.showInputDialog("Digite o prazo máximo de pagamento da empresa (em dias): "));
            salvar(pj);
        }
        //se nao for um CNPJ, então é um CPF
        else{
            PessoaFisica pf = new PessoaFisica(nome, documento, endereco);
            int qtdeMaxParcelas = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade maxima de parcelas a serem pagas: "));
            PfController.salvar(pf);
        }
        JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", "Organizações Tabajara", JOptionPane.INFORMATION_MESSAGE);
    }*/
}
