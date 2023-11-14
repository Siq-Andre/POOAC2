import java.time.LocalDateTime; //importando o pacote para usar data como atributo
public class Cliente{
    protected String nome;
    protected String documento;
    protected Endereco enderecoCliente; //usando a classe Endereco como atributo
    protected LocalDateTime dataDeCadastro;
    
    public Cliente(String nome,String documento, Endereco enderecoCliente) {
        this.nome = nome;
        this.enderecoCliente = enderecoCliente;
        this.dataDeCadastro = LocalDateTime.now();
        this.documento = documento;
        this.dataDeCadastro = LocalDateTime.now();
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEnderecoCliente() {
        return this.enderecoCliente;
    }

    public void setEnderecoCliente(Endereco enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public LocalDateTime getDataDeCadastro() {
        return this.dataDeCadastro;
    }

    public String getDocumento(){
        return this.documento;
    }

    //não há setDataDeCadastro. A data de cadastro permanece a mesma desde a criação do objeto



    public void paraString(){
        System.out.println("Nome: " + nome + "\nRua: " + enderecoCliente.getRua() + "\nNúmero: " + enderecoCliente.getNumero() + "\nBairro: " + enderecoCliente.getBairro() + "\nCEP: " + enderecoCliente.getCEP() + "\nCidade: " + enderecoCliente.getCidade() + "\nEstado: " + enderecoCliente.getEstado());
    }
}
  
