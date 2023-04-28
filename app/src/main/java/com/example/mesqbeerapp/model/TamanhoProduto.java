package com.example.mesqbeerapp.model;

//import java.awt.image.BufferedImage;

public class TamanhoProduto {

    private long id = 0;
    private int quantidade = 0;
    private double preco = 0.00;
    private int estoque = 0;
    private int imagem;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public TamanhoProduto(long id, int quantidade, double preco, int estoque, int imagem) {
        this.id = id;
        this.quantidade = quantidade;
        this.preco = preco;
        this.estoque = estoque;
        this.imagem = imagem;
    }
}
