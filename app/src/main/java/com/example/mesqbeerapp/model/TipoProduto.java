package com.example.mesqbeerapp.model;

public class TipoProduto {

    private long id = 0;
    private String nome = "";

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoProduto(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
