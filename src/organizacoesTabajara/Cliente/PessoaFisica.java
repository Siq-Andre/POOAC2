package organizacoesTabajara.Cliente;

import organizacoesTabajara.endereco.Endereco;

public class PessoaFisica extends Cliente {
    private int qtdeMaxParcelas;

    public PessoaFisica(String nome, String documento, Endereco enderecoCliente, int qtdeMaxParcelas) {
        super(nome, documento, enderecoCliente);
        this.qtdeMaxParcelas = qtdeMaxParcelas;
    }

    public String getCPF() {
        return documento;
    }
    public void setCPF(String CPF) {
        this.documento = CPF;
    }
    public int getQtdeMaxParcelas() {
        return qtdeMaxParcelas;
    }
    public void setQtdeMaxParcelas(int qtdeMaxParcelas) {
        this.qtdeMaxParcelas = qtdeMaxParcelas;
    }

    public void paraString(){
        System.out.println("Nome: " + getNome() +"\nCPF: " + documento + "\nRua: " + enderecoCliente.getRua() + "\nNúmero: " + enderecoCliente.getNumero() + "\nBairro: " + enderecoCliente.getBairro() + "\nCEP: " + enderecoCliente.getCEP() + "\nCidade: " + enderecoCliente.getCidade() + "\nEstado: " + enderecoCliente.getEstado());
    }
    
}
