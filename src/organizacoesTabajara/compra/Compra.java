package organizacoesTabajara.compra;

import organizacoesTabajara.produto.Produto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Compra {
    protected ArrayList<Integer> quantidade;
    protected ArrayList<Produto> produto;
    protected ArrayList<Double> precoUnitario;
    protected ArrayList<Double> valorTotalItem;
    protected double valorTotalCompra;
    protected static int identificador;
    protected LocalDate dataDeCompra;
    protected String documentoCliente; //esse atributo será preenchido com o CPF ou CNPJ do cliente
    
    
    public Compra(ArrayList<Integer> quantidade, ArrayList<Produto> produto, ArrayList<Double> precoUnitario, ArrayList<Double> valorTotalItem, double valorTotalCompra, String documento) {
        this.quantidade = quantidade;
        this.produto = produto;
        this.precoUnitario = precoUnitario;
        this.valorTotalItem = valorTotalItem;
        this.valorTotalCompra = valorTotalCompra;
        this.identificador += 1;
        this.dataDeCompra = LocalDate.now();
        this.documentoCliente = documento;
    }

    public String getDocumentoCliente(){
        return this.documentoCliente;
    }
    public ArrayList<Integer> getQuantidade(){
        return this.quantidade;
    }

    public ArrayList<Produto> getProduto() {
        return produto;
    }

    public ArrayList<Double> getPrecoUnitario() {
        return precoUnitario;
    }

    public ArrayList<Double> getValorTotal() {
        return valorTotalItem;
    }

    public int getIdentificador() {
        return identificador;
    }

    public LocalDate getDataDeCompra() {
        return dataDeCompra;
    }
}


