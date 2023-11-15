package organizacoesTabajara.controller;

import organizacoesTabajara.Cliente.PessoaFisica;
import organizacoesTabajara.Cliente.PessoaJuridica;
import organizacoesTabajara.compra.Compra;
import organizacoesTabajara.endereco.Endereco;

import javax.swing.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static organizacoesTabajara.controller.PjController.salvar;

public class CompraController {


    private static void salvar(Compra compra) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/organizacoesTabajara/baseDados/compras.txt", true))) {
            // Append no arquivo (true como segundo argumento para FileWriter)
            writer.write(compra.getDocumentoCliente() + ", " + compra.getQuantidade() + ", " + compra.getProduto() + ", " + compra.getPrecoUnitario() + ", " + compra.getValorTotal() + ", " + compra.getIdentificador() + ", " + compra.getDataDeCompra());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar compra no arquivo.", "Sistema de Gestão", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void efetuarCompra(){

        boolean continuar = true;



        while (continuar){
    
        }

        //Compra compra = new Compra();

        JOptionPane.showMessageDialog(null, "Compra cadastrada com sucesso!", "Organizações Tabajara", JOptionPane.INFORMATION_MESSAGE);
    }

    //listar todas as compras
    public static void listarCompras(){
         String arquivo = "src/organizacoesTabajara/baseDados/compras.txt";
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                JOptionPane.showInputDialog(null, linha, "Organizações Tabajara", JOptionPane.PLAIN_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace(); // ou trate a exceção de acordo com a sua necessidade
        }      
    }
}
