package com.example.mesqbeerapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import com.example.mesqbeerapp.R;
import com.example.mesqbeerapp.adapter.ProdutoAdapter;
import com.example.mesqbeerapp.model.Produto;
import com.example.mesqbeerapp.model.TamanhoProduto;

import java.util.ArrayList;

public class ListarProdutosActivity extends AppCompatActivity {
    private RecyclerView rvProdutos;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<Produto> listaProdutos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_produtos);

        rvProdutos = findViewById(R.id.rvProdutos);

        //Criar um Layout Manager
        layoutManager = new LinearLayoutManager(this);
        rvProdutos.setLayoutManager(layoutManager);

        buscarProdutos();

        //Criar o Adapter
        ProdutoAdapter adapter = new ProdutoAdapter(listaProdutos);
        rvProdutos.setAdapter(adapter);

    }

    private void buscarProdutos() {
        Produto p;

        p = new Produto("Heineken Long Neck 355 ml", "Produto para maiores de 18 anos.", 1);
        p.setTamanhoProduto(new TamanhoProduto(R.drawable.heinekensf, 8.25));
        listaProdutos.add(p);

        p = new Produto("Eisenbahn Long Neck 355 ml", "Produto para maiores de 18 anos.", 1);
        p.setTamanhoProduto(new TamanhoProduto(R.drawable.eisenbahnsf, 9.25));
        listaProdutos.add(p);

        p = new Produto("Spaten Long Neck 355 ml", "Produto para maiores de 18 anos.", 1);
        p.setTamanhoProduto(new TamanhoProduto(R.drawable.spaten, 10.25));
        listaProdutos.add(p);

        p = new Produto("Tiger Long Neck 355 ml", "Produto para maiores de 18 anos.", 1);
        p.setTamanhoProduto(new TamanhoProduto(R.drawable.tigersf, 11.25));
        listaProdutos.add(p);
    }

}