import java.time.LocalDateTime; //importando o pacote para usar data como atributo
public class Cliente{
    private String nome;
    private Endereco enderecoCliente; //usando a classe Endereco como atributo
    private LocalDateTime dataDeCadastro;
    
    public Cliente(String nome, Endereco enderecoCliente) {
        this.nome = nome;
        this.enderecoCliente = enderecoCliente;
        this.dataDeCadastro = LocalDateTime.now();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(Endereco enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public LocalDateTime getDataDeCadastro() {
        return dataDeCadastro;
    }

    //não há setDataDeCadastro. A data de cadastro permanece a mesma desde a criação do objeto
    
    public void paraString(){
        System.out.println("Nome: " + nome + "\nRua: " + enderecoCliente.getRua() + "\nNúmero: " + enderecoCliente.getNumero() + "\nBairro: " + enderecoCliente.getBairro() + "\nCEP: " + enderecoCliente.getCEP() + "\nCidade: " + enderecoCliente.getCidade() + "\nEstado: " + enderecoCliente.getEstado());
    }
}
  
