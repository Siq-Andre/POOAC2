public class PessoaFisica extends Cliente {
    private String CPF;
    private int qtdeMaxParcelas;
    public PessoaFisica(String nome, Endereco enderecoCliente, String CPF, int qtdeMaxParcelas) {
        super(nome, enderecoCliente);
        this.CPF = CPF;
        this.qtdeMaxParcelas = qtdeMaxParcelas;
    }
    public String getCPF() {
        return CPF;
    }
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
    public int getQtdeMaxParcelas() {
        return qtdeMaxParcelas;
    }
    public void setQtdeMaxParcelas(int qtdeMaxParcelas) {
        this.qtdeMaxParcelas = qtdeMaxParcelas;
    }

    public void paraString(){
        System.out.println("Nome: " + getNome() +"\nCPF: " + CPF + "\nRua: " + enderecoCliente.getRua() + "\nNúmero: " + enderecoCliente.getNumero() + "\nBairro: " + enderecoCliente.getBairro() + "\nCEP: " + enderecoCliente.getCEP() + "\nCidade: " + enderecoCliente.getCidade() + "\nEstado: " + enderecoCliente.getEstado());
    }
    
}
