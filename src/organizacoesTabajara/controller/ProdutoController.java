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

public class ProdutoController {
    public static void cadastrarProduto(){
        String codigo = JOptionPane.showInputDialog("Digite o código do produto:");
        String nome = JOptionPane.showInputDialog("Digite o nome do produto:");
        String descricao = JOptionPane.showInputDialog("Digite a descricao do produto: ");
        double preco = Double.parseDouble(JOptionPane.showInputDialog("Digite o preco do produto: "));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String validade = JOptionPane.showInputDialog("Digite a validade do produto (dd/mm/aaaa), se não houver digite 0: ");
        LocalDate validadeFormatada = null;

        if (!validade.equals("0")){
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
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                JOptionPane.showInputDialog(null, linha, "Organizações Tabajara", JOptionPane.PLAIN_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace(); // ou trate a exceção de acordo com a sua necessidade
        }      
  
    }
    //função para listar um produto especifico
    public static void buscarProduto()
    {        
        String nome = JOptionPane.showInputDialog("Digite o produto:");
        String arquivo = "src/organizacoesTabajara/baseDados/produtos.txt";
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] partes = linha.split(",");
                String nomeLinha = partes[0].trim();

                if (!nomeLinha.equals(nome)) {
                    JOptionPane.showInputDialog(null, linha, "Organizações Tabajara", JOptionPane.PLAIN_MESSAGE);
                }
                else{
                    JOptionPane.showInputDialog(null, "Não há clientes com esse nome.", "Organizações Tabajara", JOptionPane.PLAIN_MESSAGE);
                }
            } 
        }
        catch (IOException e) {
            e.printStackTrace(); // ou trate a exceção de acordo com a sua necessidade
        }
    }

}
