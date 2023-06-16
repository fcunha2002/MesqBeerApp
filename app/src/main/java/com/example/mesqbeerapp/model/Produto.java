package com.example.mesqbeerapp.model;

import android.graphics.Bitmap;

import com.example.mesqbeerapp.util.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

public class Produto implements Serializable {

    private String nome;
    private String descricao;
    private String id;
    private String tipoProduto;
    private TamanhoProduto tamanhoProduto;

    public Produto() {
        this.tamanhoProduto = new TamanhoProduto();
    }

    public void salvar(){
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();

        //Gera uma chave única (ID) com o comando push()
        DatabaseReference produtoRef = firebaseRef.child("produtos").push();
        // Pega a chave única (ID) gerada com o comando push()
        this.id = produtoRef.getKey();

        produtoRef = firebaseRef.child("produtos").child(getId());

        produtoRef.setValue(this);

        salvarImagem();
    }

    private void salvarImagem(){
            //Converter os dados da imagem para armazenar no Firebase
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Bitmap imagem = this.getTamanhoProduto().getImagem();
            imagem.compress(Bitmap.CompressFormat.JPEG, 70, baos);
            byte[] dadosImagem = baos.toByteArray();

            //Salvar imagem no Firebase
            StorageReference storageReference = ConfiguracaoFirebase.getFirebaseStorage();
            StorageReference imagemRef = storageReference
                    .child("imagens")
                    .child(this.getId() + ".jpeg");
            imagemRef.putBytes(dadosImagem);

    }

    public Produto(String nome, String descricao, String id)
    {
        this.nome = nome;
        this.descricao = descricao;
        this.id = id;
    }

    public Produto(String nome, String descricao, String id, String tipoProduto, TamanhoProduto tamanhoProduto) {
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

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public TamanhoProduto getTamanhoProduto() {
        return tamanhoProduto;
    }

    public void setTamanhoProduto(TamanhoProduto tamanhoProduto) {
        this.tamanhoProduto = tamanhoProduto;
    }

}
