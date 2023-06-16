package com.example.mesqbeerapp.model;

import android.graphics.Bitmap;

import com.google.firebase.database.Exclude;

public class TamanhoProduto {

    private int quantidade;
    private double preco;
    private int estoque;
    private Bitmap imagem;
    private UnidadeMedida unidadeMedida;

    public TamanhoProduto() {
    }

    public TamanhoProduto(Bitmap imagem, double preco) {
        this.imagem = imagem;
        this.preco = preco;
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

    @Exclude
    public Bitmap getImagem() {
        return imagem;
    }

    public void setImagem(Bitmap imagem) {
        this.imagem = imagem;
    }

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

}
