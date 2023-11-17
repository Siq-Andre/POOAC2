package organizacoesTabajara.controller;

import organizacoesTabajara.Cliente.PessoaFisica;
import organizacoesTabajara.Cliente.PessoaJuridica;
import organizacoesTabajara.compra.Compra;
import organizacoesTabajara.endereco.Endereco;
import organizacoesTabajara.produto.Produto;

import javax.swing.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static organizacoesTabajara.controller.PjController.salvar;

public class CompraController {


    private static void salvar(Compra compra) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/organizacoesTabajara/baseDados/compras.txt", true))) {
            // Append no arquivo (true como segundo argumento para FileWriter)
            writer.write(compra.getIdentificador() + ", " + compra.getDocumentoCliente() + ", " + compra.getQuantidade() + ", " + compra.getProduto() + ", " + compra.getPrecoUnitario() + ", " + compra.getValorTotal()  + ", " + compra.getDataDeCompra());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar compra no arquivo.", "Sistema de Gestão", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void efetuarCompra(){

        boolean continuarCompra = true;
        double valorTotalCompra = 0;
        LocalDateTime dataDeCompra;
        String documento;

        documento = JOptionPane.showInputDialog(null, "Digite seu documento:", "Organizações Tabajara", JOptionPane.PLAIN_MESSAGE);
        while(continuarCompra) {

            boolean continuarProduto = true;
            ArrayList<Integer> quantidade = new ArrayList<>();
            ArrayList<Produto> produtos = new ArrayList<>();
            ArrayList<Double> precoUnitario = new ArrayList<>();
            ArrayList<Double> valorTotalItem = new ArrayList<>();

            while (continuarProduto) {
                String nomeProduto = JOptionPane.showInputDialog(null, "Qual o nome do produto?", "Organizações Tabajara", JOptionPane.PLAIN_MESSAGE);

                // Leitura do arquivo de produtos
                String arquivoProdutos = "src/organizacoesTabajara/baseDados/produtos.txt";
                try (BufferedReader leitor = new BufferedReader(new FileReader(arquivoProdutos))) {
                    String linha;
                    while ((linha = leitor.readLine()) != null) {
                        String[] partes = linha.split(",");
                        String nome = partes[1].trim();

                        if (nome.equalsIgnoreCase(nomeProduto)) {

                            LocalDate validade = LocalDate.parse(partes[4].trim());
                            double valor = Double.parseDouble(partes[3].trim());

                            // Produto encontrado, criar o objeto Produto
                            Produto produto = new Produto(partes[0].trim(), partes[1].trim(), partes[2].trim(), valor, validade);
                            produtos.add(produto);

                            // Obter a quantidade desejada pelo cliente
                            int quantidadeTemp = Integer.parseInt(JOptionPane.showInputDialog(null, "Quantidade desejada:", "Organizações Tabajara", JOptionPane.PLAIN_MESSAGE));
                            quantidade.add(quantidadeTemp);

                            // Calcular o valor total da compra
                            double valorTotalTemp = quantidadeTemp * produto.getPreco();
                            valorTotalItem.add(valorTotalTemp);
                            valorTotalCompra += valorTotalTemp;

                            // Exibir o valor total da compra
                            JOptionPane.showMessageDialog(null, "Valor total da compra: " + valorTotalCompra, "Organizações Tabajara", JOptionPane.INFORMATION_MESSAGE);

                            // Verificar se o cliente deseja continuar comprando
                            int resposta = JOptionPane.showConfirmDialog(null, "Deseja comprar outro produto?", "Organizações Tabajara", JOptionPane.YES_NO_OPTION);
                            continuarProduto = (resposta == JOptionPane.YES_OPTION);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Compra compra = new Compra(quantidade, produtos, precoUnitario, valorTotalItem,valorTotalCompra, documento);
            salvar(compra);
            valorTotalCompra = 0;
            int resposta = JOptionPane.showConfirmDialog(null, "Deseja realizar outra compra?", "Organizações Tabajara", JOptionPane.YES_NO_OPTION);
            continuarCompra = (resposta == JOptionPane.YES_OPTION);
        }
    }

    //listar todas as compras
    public static void listarCompras(){
        String arquivo = "src/organizacoesTabajara/baseDados/compras.txt";
        List<String> compras = new ArrayList<>();
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                compras.add(linha);
                compras.add("\n");
            }
            JOptionPane.showInputDialog(null, compras, "Organizações Tabajara", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }      
    }
    public static void buscarCompra(){
        String numero = JOptionPane.showInputDialog("Digite o número da compra:");
        String arquivo = "src/organizacoesTabajara/baseDados/compras.txt";
        int cont =0;
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] partes = linha.split(";");
                String numLinha = partes[0].trim();

                if (numLinha.equals(numero)) {
                    JOptionPane.showInputDialog(null, linha.toString(), "Organizações Tabajara", JOptionPane.PLAIN_MESSAGE);
                    cont++;
                } 
            }
            if (cont ==0) {
                JOptionPane.showInputDialog(null, "Essa compra não existe", "Organizações Tabajara", JOptionPane.PLAIN_MESSAGE);
            }
        }
        catch (IOException e) {
            e.printStackTrace(); 
        }
    }
}
