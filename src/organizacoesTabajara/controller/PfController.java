package organizacoesTabajara.controller;

import organizacoesTabajara.endereco.Endereco;
import organizacoesTabajara.Cliente.PessoaFisica;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PfController {

    //função para salvar o cliente caso seja pessoa fisica
    protected static void salvar(PessoaFisica pf) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("clientes.txt", true))) {
            Endereco endereco = pf.getEnderecoCliente();
            // Append no arquivo (true como segundo argumento para FileWriter)
            writer.write(pf.getNome() + ", " + pf.getDocumento() + ", " + pf.getQtdeMaxParcelas() + ", " + endereco.paraString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar cliente no arquivo.", "Sistema de Gestão", JOptionPane.ERROR_MESSAGE);
        }
    }

}
