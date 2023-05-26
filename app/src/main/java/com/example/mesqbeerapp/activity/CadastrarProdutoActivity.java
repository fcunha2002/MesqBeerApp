package com.example.mesqbeerapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.mesqbeerapp.R;
import com.example.mesqbeerapp.model.Produto;

public class CadastrarProdutoActivity extends AppCompatActivity {
    public EditText nomeProduto;
    public EditText descricaoProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_produto);

        nomeProduto = findViewById(R.id.edtCadastrarProdutoNome);
        descricaoProduto = findViewById(R.id.edtCadastrarProdutoDescricao);
    }

    public void salvar(View view) {
        Produto p = new Produto();

        p.setNome(nomeProduto.getText().toString());
        p.setDescricao(descricaoProduto.getText().toString());
        p.setId("X2");
        p.salvar();


    }
}