package organizacoesTabajara.Cliente;

import organizacoesTabajara.endereco.Endereco;

public class PessoaJuridica extends Cliente{
    private String razaoSocial;
    private int prazoMaxPagamento;

    public PessoaJuridica(String nome, String documento, Endereco enderecoCliente) {
        super(nome, documento, enderecoCliente);
        this.razaoSocial = nome;
    }

    public String getCNPJ() {
        return documento;
    }

    public void setCNPJ(String CNPJ) {
        this.documento = CNPJ;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public int getPrazoMaxPagamento() {
        return prazoMaxPagamento;
    }

    public void setPrazoMaxPagamento(int prazoMaxPagamento) {
        this.prazoMaxPagamento = prazoMaxPagamento;
    }

    public void paraString(){
        System.out.println("Nome: " + razaoSocial  + "\nCNPJ: " + documento + "\nRua: " + enderecoCliente.getRua() + "\nNúmero: " + enderecoCliente.getNumero() + "\nBairro: " + enderecoCliente.getBairro() + "\nCEP: " + enderecoCliente.getCEP() + "\nCidade: " + enderecoCliente.getCidade() + "\nEstado: " + enderecoCliente.getEstado());
    }
    
}
