public class Cliente{
    private String nome;
    private Endereco enderecoCliente;
    private String dataCadastro;
    
    public Cliente(String nome, Endereco enderecoCliente, String dataCadastro) {
        this.nome = nome;
        this.enderecoCliente = enderecoCliente;
        this.dataCadastro = dataCadastro;
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

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    
   
    
}
