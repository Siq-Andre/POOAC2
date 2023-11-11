public class PessoaJuridica extends Cliente{
    private String CNPJ;
    private String razaoSocial;
    private int prazoMaxPagamento;
    
    public PessoaJuridica(String nome, Endereco enderecoCliente, String CNPJ, String razaoSocial,
            int prazoMaxPagamento) {
        super(nome, enderecoCliente);
        this.CNPJ = CNPJ;
        this.razaoSocial = razaoSocial;
        this.prazoMaxPagamento = prazoMaxPagamento;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
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

    
    
}
