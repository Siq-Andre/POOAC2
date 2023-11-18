package organizacoesTabajara.controller;

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

public class ProdutoController {
    public static void cadastrarProduto(){
        String codigo = JOptionPane.showInputDialog("Digite o código do produto:");
        String nome = JOptionPane.showInputDialog("Digite o nome do produto:");
        if(produtoJaRegistrado(nome)){
            JOptionPane.showMessageDialog(null, "Produto com nome ou codigo já registrado");
            cadastrarProduto(); // Chama o metodo novamente para o usuário digitar novos dados
        }
        String descricao = JOptionPane.showInputDialog("Digite a descricao do produto: ");
        double preco = Double.parseDouble(JOptionPane.showInputDialog("Digite o preco do produto: "));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");//formatação pra data de validade

        String validade = JOptionPane.showInputDialog("Digite a validade do produto (dd/mm/aaaa), se não houver digite 0: ");
        LocalDate validadeFormatada = null;

        if (!validade.equals("0")){//formata a data de validade se tiver
            validadeFormatada = LocalDate.parse(validade, formatter);
        }

        Produto produto = new Produto(codigo, nome, descricao, preco, validadeFormatada);
        salvar(produto);

        JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!", "Organizações Tabajara", JOptionPane.INFORMATION_MESSAGE);
    }
    private static void salvar(Produto produto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/organizacoesTabajara/baseDados/produtos.txt", true))) {
            // Append no arquivo (true como segundo argumento para FileWriter)
            writer.write(produto.getCodigo() + ", " + produto.getNome() + ", " + produto.getDescricao() + ", " + produto.getPreco() + ", " + produto.getValidade());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar produto no arquivo.", "Organizações Tabajara", JOptionPane.ERROR_MESSAGE);
        }
    }
    //funçao para listar todos os produtos
    public static void listarProdutos(){
        String arquivo = "src/organizacoesTabajara/baseDados/produtos.txt";
        List<String> produtos = new ArrayList<>();
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                produtos.add(linha);//adiciona cada intem do arquivo produtos.txt à String produto
                produtos.add("\n"); //pula linha para melhor exibição 
            }
            JOptionPane.showMessageDialog(null, produtos, "Organizações Tabajara", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace(); 
        }      
  
    }
    //função para listar um produto especifico
    public static void buscarProduto()
    {        
        String nome = JOptionPane.showInputDialog("Digite o produto:");
        String arquivo = "src/organizacoesTabajara/baseDados/produtos.txt";        
        List<String> produtosEncontrados = new ArrayList<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] partes = linha.split(";");
                String nomeLinha = partes[1].trim();//posição do vetor onde esta o nome do produto

                if (nomeLinha.equals(nome)) { //verifica se o nome inserido é igual
                    produtosEncontrados.add(linha);
                }                
            } 
            if (produtosEncontrados.isEmpty()) {//caso não haja produto com o nome igual ao inserido
                JOptionPane.showMessageDialog(null, "Não há esse produto disponível.", "Organizações Tabajara", JOptionPane.PLAIN_MESSAGE);
            } else {
                StringBuilder mensagem = new StringBuilder();
                for (String produto : produtosEncontrados) { 
                    mensagem.append(produto).append("\n");
                }
                JOptionPane.showMessageDialog(null, mensagem.toString(), "Organizações Tabajara", JOptionPane.PLAIN_MESSAGE);
            }
        }
        catch (IOException e) {
            e.printStackTrace(); 
        }
    }

    public static void produtoVencido(){
        String arquivo = "src/organizacoesTabajara/baseDados/produtos.txt";
        List<String> produtosVencidos = new ArrayList<>();
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] partes = linha.split(";");
                LocalDate dataValidade = LocalDate.parse(partes[5], DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                // Chame a função para verificar se o produto está vencido
                if(LocalDate.now().isAfter(dataValidade)){//compara a data de hoje com a data de validade do produto
                    produtosVencidos.add(linha);
                    produtosVencidos.add("\n");
                }                 
            }
            JOptionPane.showMessageDialog(null, produtosVencidos, "Organizações Tabajara", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace(); 
        }      
    }
    private static boolean produtoJaRegistrado( String nome) {
        String arquivo = "src/organizacoesTabajara/baseDados/produtos.txt";
        try(BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while((linha = leitor.readLine()) != null) {
                String[] parts = linha.split(";");
                    String nomeRegistrado = parts[1].trim();

                // Verifica se o nome ja esta cadastrado
                if (nomeRegistrado.equals(nome)) {
                    return true; // produto já registrado
                }
            }
        }catch (IOException e) {
            e.printStackTrace(); 
        }

        return false; // produto não encontrado no arquivo

    }
}
