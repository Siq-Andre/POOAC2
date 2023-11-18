package organizacoesTabajara.controller;

import organizacoesTabajara.endereco.Endereco;
import organizacoesTabajara.Cliente.PessoaJuridica;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PjController {
    //função para salvar o cliente caso seja pessoa juridica
    protected static void salvar(PessoaJuridica pj) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("clientes.txt", true))) {
            Endereco endereco = pj.getEnderecoCliente();
            // Append no arquivo (true como segundo argumento para FileWriter)
            writer.write(pj.getRazaoSocial() + "; " + pj.getDocumento() + "; " +pj.getPrazoMaxPagamento() + "; " + endereco.paraString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar cliente no arquivo.", "Sistema de Gestão", JOptionPane.ERROR_MESSAGE);
        }
    }

}
