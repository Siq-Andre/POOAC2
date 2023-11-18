package organizacoesTabajara.compra;

import organizacoesTabajara.produto.Produto;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;

public class Compra {
    protected ArrayList<Integer> quantidade;
    protected ArrayList<Produto> produtos;
    protected ArrayList<Double> precoUnitario;
    protected ArrayList<Double> valorTotalItem;
    protected double valorTotalCompra;
    protected double valorPago;
    protected static int identificador;
    protected LocalDate dataDeCompra;
    protected String documentoCliente; //esse atributo será preenchido com o CPF ou CNPJ do cliente

    public Compra(ArrayList<Integer> quantidade, ArrayList<Produto> produtos, ArrayList<Double> precoUnitario, ArrayList<Double> valorTotalItem, double valorTotalCompra, String documento) {
        this.quantidade = quantidade;
        this.produtos = produtos;
        this.precoUnitario = precoUnitario;
        this.valorTotalItem = valorTotalItem;
        this.valorTotalCompra = valorTotalCompra;
        this.identificador += 1;
        this.dataDeCompra = LocalDate.now();
        this.documentoCliente = documento;
        this.valorPago = 0;
    }

    public String getDocumentoCliente(){
        return this.documentoCliente;
    }
    public ArrayList<Integer> getQuantidade(){
        return this.quantidade;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public ArrayList<String> paraStringProdutos() {
        ArrayList<String> produtosString = new ArrayList<>();

        for (Produto produto : produtos) {
            produtosString.add(produto.paraString());
        }

        return produtosString;
    }

    public ArrayList<Double> getPrecoUnitario() {
        return precoUnitario;
    }

    public ArrayList<Double> getValorTotal() {
        return valorTotalItem;
    }
    public Double getValorTotalCompra() {
        return valorTotalCompra;
    }

    public int getIdentificador() {
        return identificador;
    }

    public LocalDate getDataDeCompra() {
        return dataDeCompra;
    }

    public double getTotalPagar(){
        return (this.valorTotalCompra - this.valorPago);
    }
    public double getValorPago() {
        return valorPago;
    }
}


