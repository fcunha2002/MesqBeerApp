package com.example.mesqbeerapp.model;

import android.graphics.Bitmap;

import com.example.mesqbeerapp.util.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Produto implements Serializable {

    private String nome;
    private String descricao;
    private String id;
    private String tipoProduto;
    private int quantidade;
    private double preco;
    private int estoque;
    private Bitmap imagem;
    private UnidadeMedida unidadeMedida;

    public Produto() {}

    public void atribuiId(){
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();

        //Gera uma chave única (ID) com o comando push()
        DatabaseReference produtoRef = firebaseRef.child("produtos").push();
        // Pega a chave única (ID) gerada com o comando push()
        this.id = produtoRef.getKey();
    }

    public void salvar(){
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
        DatabaseReference produtoRef = firebaseRef.child("produtos").child(getId());

        //Equivalente ao Insert
        produtoRef.setValue(this);

    }

    public Produto(String nome, String descricao, String id, Bitmap imagem, double preco)
    {
        this.nome = nome;
        this.descricao = descricao;
        this.id = id;
        this.imagem = imagem;
        this.preco = preco;
    }

    public Produto(String nome, String descricao, String id, String tipoProduto) {
        this.nome = nome;
        this.descricao = descricao;
        this.id = id;
        this.tipoProduto = tipoProduto;
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

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
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

    @Exclude //Esta anotação faz com que o campo não seja gravado no
    //banco de dados do firebase
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
