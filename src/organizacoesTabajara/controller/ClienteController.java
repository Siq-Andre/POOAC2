package organizacoesTabajara.controller;

import organizacoesTabajara.endereco.Endereco;
import organizacoesTabajara.Cliente.PessoaFisica;
import organizacoesTabajara.Cliente.PessoaJuridica;

import javax.swing.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static organizacoesTabajara.controller.PfController.salvar;
import static organizacoesTabajara.controller.PjController.salvar;

public class ClienteController {
    //funçao para cadastrar os dados de um novo cliente
    public static void cadastrarCliente() {
        String nome = JOptionPane.showInputDialog("Digite o nome do cliente:");
        String documento = JOptionPane.showInputDialog("Digite o CPF ou CNPJ do cliente:");
        if(clienteJaRegistrado(nome, documento)){//verificacao para nome ou documento repetido
            JOptionPane.showMessageDialog(null, "Cliente com nome ou documento já registrado. Por favor, digite novamente.");
            cadastrarCliente(); // Chama o método novamente para o usuário digitar novos dados
    }
        String rua = JOptionPane.showInputDialog("Digite a rua: ");
        String numero = JOptionPane.showInputDialog("Digite o numero: ");
        String bairro = JOptionPane.showInputDialog("Digite o bairro: ");
        String cep = JOptionPane.showInputDialog("Digite o cep: ");
        String estado = JOptionPane.showInputDialog("Digite o estado: ");
        String cidade = JOptionPane.showInputDialog("Digite a cidade: ");
        Endereco endereco = new Endereco(rua, numero, bairro, cep, cidade, estado);

        //verificação se o cliente é pessoa fisica ou juridica. Se o documento possui mais de 11 digitos, é um CNPJ
        if (documento.length() > 11){ //verificacao de que o documento tem mais digitos que um cpf
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

    public static void deletarClientePorDocumento(){
        String documento = JOptionPane.showInputDialog("Digite o CPF ou CNPJ do cliente a ser excluído:");
        String arquivo = "src/organizacoesTabajara/baseDados/clientes.txt";

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo));
             BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivo + ".temp"))) {

            String linhaAtual;

            while ((linhaAtual = leitor.readLine()) != null) {
                String[] partes = linhaAtual.split(";");
                String cnpjLinha = partes[1].trim();

                // Verifica se o documento na linha é diferente do documeno a ser removido
                if (!cnpjLinha.equals(documento)) {
                    // Se for diferente, escreve a linha no arquivo temporário
                    escritor.write(linhaAtual);
                    escritor.newLine();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Renomeia o arquivo temporário para substituir o original
        renomearArquivoTemporario(arquivo);
    }

    public static void deletarClientePorNome(){
        String nome = JOptionPane.showInputDialog("Digite o nome do cliente a ser excluído:");
        String arquivo = "src/organizacoesTabajara/baseDados/clientes.txt";

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo));
             BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivo + ".temp"))) {

            String linhaAtual;

            while ((linhaAtual = leitor.readLine()) != null) {
                String[] partes = linhaAtual.split(";");
                String nomeLinha = partes[0].trim();

                if (!nomeLinha.equals(nome)) {// Verifica se o nome na linha é diferente do nome a ser removido
                    // Se for diferente, escreve a linha no arquivo temporário
                    escritor.write(linhaAtual);
                    escritor.newLine();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // Renomeia o arquivo temporário para substituir o original
        renomearArquivoTemporario(arquivo);
    }

    private static void renomearArquivoTemporario(String caminhoArquivo) {
        String caminhoArquivoTemporario = caminhoArquivo + ".temp"; //arquivo temporario para realizar os deletes
        String caminhoArquivoOriginal = caminhoArquivo;

        try {
            java.nio.file.Files.move(java.nio.file.Paths.get(caminhoArquivoTemporario),
                    java.nio.file.Paths.get(caminhoArquivoOriginal),
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //funçao para buscar cliente especifico
    public static void buscarCliente(){
        String nome = JOptionPane.showInputDialog("Digite o nome do cliente:").toLowerCase();
        String arquivo = "src/organizacoesTabajara/baseDados/clientes.txt";
        List<String> clientesEncontrados = new ArrayList<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] partes = linha.split(";");
                String nomeLinha = partes[0].trim().toLowerCase();

                if (nomeLinha.startsWith(nome)) { //verifica se o nome começa com os caracteres inseridos pelo usuario
                    clientesEncontrados.add(linha);
                }
            }

            if (clientesEncontrados.isEmpty()) { //caso não haja clientes com os caracteres inseridos
                JOptionPane.showMessageDialog(null, "Não há clientes com esse nome.", "Organizações Tabajara", JOptionPane.INFORMATION_MESSAGE);
            } else {
                StringBuilder mensagem = new StringBuilder();
                for (String cliente : clientesEncontrados) {
                    mensagem.append(cliente).append("\n");
                }
                JOptionPane.showMessageDialog(null, mensagem.toString(), "Organizações Tabajara", JOptionPane.PLAIN_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }

    private static boolean clienteJaRegistrado(String nome, String documento) {
        String arquivo = "src/organizacoesTabajara/baseDados/clientes.txt";
        try(BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while((linha = leitor.readLine()) != null) {
                String[] parts = linha.split(";");
                    String nomeRegistrado = parts[0].trim();
                    String documentoRegistrado = parts[1].trim();

                // Verifica se o nome ou o documento já estão registrados
                if (nomeRegistrado.equals(nome) || documentoRegistrado.equals(documento)) {
                    return true; // Cliente já registrado
                }
            }
        }catch (IOException e) {
            e.printStackTrace(); // Trate a exceção de acordo com suas necessidades
        }

        return false; // Cliente não encontrado no arquivo

    }
    
}
