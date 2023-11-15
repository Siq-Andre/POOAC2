package organizacoesTabajara.compra;

import organizacoesTabajara.produto.Produto;

import java.time.LocalDateTime;
public class Compra {
    protected int[] quantidade;
    protected Produto[] produto;
    protected double[] precoUnitario;
    protected double[] valorTotal;
    protected String identificador;
    protected LocalDateTime dataDeCompra;
    protected String documentoCliente; //esse atributo será preenchido com o CPF ou CNPJ do cliente
    
    
    public Compra(int[] quantidade, Produto[] produto, double[] precoUnitario, double[] valorTotal, String identificador,
                  LocalDateTime dataDeCompra, String documento) {
        this.quantidade = quantidade;
        this.produto = produto;
        this.precoUnitario = precoUnitario;
        this.valorTotal = valorTotal;
        this.identificador = identificador;
        this.dataDeCompra = dataDeCompra;
        this.documentoCliente = documento;
    }

    public String getDocumentoCliente(){
        return this.documentoCliente;
    }
    public int[] getQuantidade(){
        return this.quantidade;
    }

    public Produto[] getProduto() {
        return produto;
    }

    public double[] getPrecoUnitario() {
        return precoUnitario;
    }

    public double[] getValorTotal() {
        return valorTotal;
    }

    public String getIdentificador() {
        return identificador;
    }

    public LocalDateTime getDataDeCompra() {
        return dataDeCompra;
    }
}


