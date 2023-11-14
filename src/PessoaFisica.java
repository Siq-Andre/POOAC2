public class PessoaFisica extends Cliente {
    private int qtdeMaxParcelas;

    public PessoaFisica(String nome, String documento, Endereco enderecoCliente) {
        super(nome, documento, enderecoCliente);
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
