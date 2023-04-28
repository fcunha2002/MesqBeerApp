package com.example.mesqbeerapp.model;

public class Produto {

    private String nome = "";
    private String descricao = "";
    private long id = 0;
    private TipoProduto tipoProduto;
    private TamanhoProduto tamanhoProduto;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public TamanhoProduto getTamanhoProduto() {
        return tamanhoProduto;
    }

    public void setTamanhoProduto(TamanhoProduto tamanhoProduto) {
        this.tamanhoProduto = tamanhoProduto;
    }

    public Produto(String nome, String descricao, long id)
    {
        this.nome = nome;
        this.descricao = descricao;
        this.id = id;
    }

    public Produto(String nome, String descricao, long id, TipoProduto tipoProduto, TamanhoProduto tamanhoProduto) {
        this.nome = nome;
        this.descricao = descricao;
        this.id = id;
        this.tipoProduto = tipoProduto;
        this.tamanhoProduto = tamanhoProduto;
    }
}
