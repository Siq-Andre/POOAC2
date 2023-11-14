import java.time.LocalDateTime;

public class Produto {
    protected String codigo;
    protected String nome;
    protected String descricao;
    protected double preco;
    protected LocalDateTime validade;
    
    //construtor para produto com data de validade
    public Produto(String codigo, String nome, String descricao, double preco, LocalDateTime validade) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.validade = validade;
    }

    //construtor para produtos sem data de validade
    public Produto(String codigo, String nome, String descricao, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.validade = null;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public LocalDateTime getValidade() {
        return validade;
    }

    public void setValidade(LocalDateTime validade) {
        this.validade = validade;
    }

    //metodo para identificar se o produto esta vencido
    public boolean validade(){
        if(LocalDateTime.now().isAfter(validade)){
            return true;
        }
        else{
            return false;
        }
    }
}
