package com.example.mesqbeerapp.model;

public class TamanhoProduto {

    private int quantidade;
    private double preco;
    private int estoque;
    private int imagem;
    private UnidadeMedida unidadeMedida;

    public TamanhoProduto() {
    }

    public TamanhoProduto(int imagem, double preco) {
        this.imagem = imagem;
        this.preco = preco;
    }

    public TamanhoProduto(int quantidade, double preco, int estoque, int imagem) {
        this.quantidade = quantidade;
        this.preco = preco;
        this.estoque = estoque;
        this.imagem = imagem;
    }

    public TamanhoProduto(int quantidade, double preco, int estoque, int imagem, UnidadeMedida unidadeMedida) {
        this.quantidade = quantidade;
        this.preco = preco;
        this.estoque = estoque;
        this.imagem = imagem;
        this.unidadeMedida = unidadeMedida;
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

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

}
