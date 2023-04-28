package com.example.mesqbeerapp.model;

public class Produto {

    String nome = "";
    String descricao = "";
    long id = 0;

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

    public Produto(String nome, String descricao, long id)
    {
        this.nome = nome;
        this.descricao = descricao;
        this.id = id;
    }
}
