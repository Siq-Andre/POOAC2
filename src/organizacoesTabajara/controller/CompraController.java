package organizacoesTabajara.controller;

import organizacoesTabajara.compra.Compra;
import organizacoesTabajara.produto.Produto;

import javax.swing.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CompraController {


    private static void salvar(Compra compra) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/organizacoesTabajara/baseDados/compras.txt", true))) {
            // Append no arquivo (true como segundo argumento para FileWriter)
            writer.write(compra.getIdentificador() + ", " + compra.getDocumentoCliente() + ", " + compra.getQuantidade() + ", " + compra.paraStringProdutos() + ", " + compra.getPrecoUnitario() + ", " + compra.getValorTotal() + ", " + compra.getValorTotalCompra()  + ", " + compra.getDataDeCompra()  + ", " + compra.getValorPago());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar compra no arquivo.", "Sistema de Gestão", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void efetuarCompra(){

        boolean continuarCompra = true;
        double valorTotalCompra = 0;
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
                        String[] partes = linha.split(";");
                        String nome = partes[1].trim();

                        if (nome.equalsIgnoreCase(nomeProduto)) {
                            System.out.println(partes[4]);
                            LocalDate validade = !partes[4].equals(" null")?LocalDate.parse(partes[4].trim()):null;
                            double valor = Double.parseDouble(partes[3].trim());

                            // Produto encontrado, criar o objeto Produto
                            Produto produto = new Produto(partes[0].trim(), partes[1].trim(), partes[2].trim(), valor, validade);
                            produtos.add(produto);
                            precoUnitario.add(produto.getPreco());

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

    public static void Pagar(){
        String id = JOptionPane.showInputDialog(null, "Digite o código da compra: ", "Organizações Tabajara", JOptionPane.PLAIN_MESSAGE);

        String arquivoCompras = "src/organizacoesTabajara/baseDados/compras.txt";
        String arquivoTemporario = "src/organizacoesTabajara/baseDados/temp_compras.txt";

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivoCompras));
             BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivoTemporario))) {

            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] partes = linha.split(";");
                String codigo = partes[0].trim();

                if (codigo.equals(id)) {
                    String valorPagamento = JOptionPane.showInputDialog(null, "Digite o valor a pagar: ", "Organizações Tabajara", JOptionPane.PLAIN_MESSAGE);
                    double novoValor = Double.parseDouble(partes[8]) + Double.parseDouble(valorPagamento);

                    if (novoValor>Double.parseDouble(partes[6])){
                        JOptionPane.showMessageDialog(null, "Novo valor excede o valor original da compra! \n o valor pago até o momento foi R$" + partes[8] + "\n o valor da compra é de R$" + partes[6] + "\n o valor faltante é R$" + String.valueOf(Double.parseDouble(partes[6]) - Double.parseDouble(partes[8])).replace("-", ""), "Organizações Tabajara", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    partes[8] = String.valueOf(novoValor);

                    linha = String.join(";", partes);

                    JOptionPane.showMessageDialog(null, "Pagamento realizado com sucesso!", "Organizações Tabajara", JOptionPane.INFORMATION_MESSAGE);
                }

                escritor.write(linha + System.lineSeparator()); // Escreve a linha no arquivo temporário
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Renomeia o arquivo temporário para substituir o original
        try {
            java.nio.file.Files.move(java.nio.file.Paths.get(arquivoTemporario), java.nio.file.Paths.get(arquivoCompras), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
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
            JOptionPane.showMessageDialog(null, compras, "Organizações Tabajara", JOptionPane.PLAIN_MESSAGE);
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
                    JOptionPane.showMessageDialog(null, linha.toString(), "Organizações Tabajara", JOptionPane.PLAIN_MESSAGE);
                    cont++;
                } 
            }
            if (cont ==0) {
                JOptionPane.showMessageDialog(null, "Essa compra não existe", "Organizações Tabajara", JOptionPane.PLAIN_MESSAGE);
            }
        }
        catch (IOException e) {
            e.printStackTrace(); 
        }
    }

    public static void comprasNaoPagas(){
        String arquivo = "src/organizacoesTabajara/baseDados/compras.txt";
        List<String> comprasNaoPagas = new ArrayList<>();
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] partes = linha.split(";");
                double valorTotalCompra = Double.parseDouble(partes[6].trim());
                double valorPago = Double.parseDouble(partes[8].trim());
                if(valorPago != valorTotalCompra){
                    comprasNaoPagas.add(linha);
                    comprasNaoPagas.add("\n");
                }                 
            }
            JOptionPane.showMessageDialog(null, comprasNaoPagas, "Organizações Tabajara", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace(); 
        }      
    }
    public static void compraMaisCara(){
        String arquivo = "src/organizacoesTabajara/baseDados/compras.txt";
        String infoCompra = null;
        double valor;
        double maisCara = -1;
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] partes = linha.split(";");
                valor = Double.parseDouble(partes[6].trim());
                if (maisCara == -1){
                    maisCara = Double.parseDouble(partes[6].trim());
                    infoCompra = linha;
                }
                if(valor > maisCara){
                    infoCompra = linha;
                    maisCara = valor;
                }                                
            }
            JOptionPane.showMessageDialog(null, infoCompra, "Organizações Tabajara", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace(); 
        }      
    }
    public static void compraMaisBarata(){
        String arquivo = "src/organizacoesTabajara/baseDados/compras.txt";
        String infoCompra = null;
        double valor;
        double maisBarata = -1;
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] partes = linha.split(";");
                valor = Double.parseDouble(partes[6].trim());
                if (maisBarata == -1){
                    maisBarata = Double.parseDouble(partes[6].trim());
                    infoCompra = linha;
                }
                if(valor < maisBarata){
                    infoCompra = linha;
                    maisBarata = valor;
                }                                
            }
            JOptionPane.showMessageDialog(null, infoCompra, "Organizações Tabajara", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace(); 
        }      
    }
    public static void ultimosPagamentos(){
        String arquivo = "src/organizacoesTabajara/baseDados/compras.txt";
        List<String> compras = new ArrayList<>();
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                compras.add(linha);
                compras.add("\n");
                if(compras.size() > 10){
                    compras.remove(0);
                }
            }
            JOptionPane.showMessageDialog(null, compras, "Organizações Tabajara", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }      
    }
    public static void compraMes(){ //minha ideia aqui é fazer dois whiles. Um percorre o arquivo todo como normal e outro a cada mes faz uma somatoria dos valores de compra
        String arquivo = "src/organizacoesTabajara/baseDados/compras.txt";
        List<Double> valorMeses = new ArrayList<>();
        double somatoriaMes;
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] partes = linha.split(";");
                LocalDate mes = LocalDate.parse(partes[7], DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                
            }
            JOptionPane.showMessageDialog(null, valorMeses, "Organizações Tabajara", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace(); 
        }      
    }
}
