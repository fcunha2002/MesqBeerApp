package com.example.mesqbeerapp.model;

import com.example.mesqbeerapp.util.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;

public class Produto implements Serializable {

    private String nome;
    private String descricao;
    private String id;
    private TipoProduto tipoProduto;
    private TamanhoProduto tamanhoProduto;

    public Produto() {
    }

    public void salvar(){
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();

        //Gera uma chave única (ID) com o comando push()
        DatabaseReference produtoRef = firebaseRef.child("produtos").push();
        // Pega a chave única (ID) gerada com o comando push()
        this.id = produtoRef.getKey();

        produtoRef = firebaseRef.child("produtos").child(getId());

        produtoRef.setValue(this);
    }

    public Produto(String nome, String descricao, String id)
    {
        this.nome = nome;
        this.descricao = descricao;
        this.id = id;
    }

    public Produto(String nome, String descricao, String id, TipoProduto tipoProduto, TamanhoProduto tamanhoProduto) {
        this.nome = nome;
        this.descricao = descricao;
        this.id = id;
        this.tipoProduto = tipoProduto;
        this.tamanhoProduto = tamanhoProduto;
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

}
