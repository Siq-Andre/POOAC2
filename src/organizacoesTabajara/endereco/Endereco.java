package organizacoesTabajara.endereco;

public class Endereco {
    private String rua;
    private String numero;
    private String bairro;
    private String CEP;
    private String cidade;
    private String estado;
    
    public Endereco(String rua, String numero, String bairro, String CEP, String cidade, String estado) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.CEP = CEP;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String paraString()
    {
        return ("Rua: " + rua + " Número: " + numero + " Bairro: " + bairro + " CEP: " + CEP + " Cidade: " + cidade + " Estado: " + estado);
    }
}
