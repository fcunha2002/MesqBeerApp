package com.example.mesqbeerapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.mesqbeerapp.R;
import com.example.mesqbeerapp.model.Produto;

public class CadastrarProdutoActivity extends AppCompatActivity {
    public EditText nomeProduto;
    public EditText descricaoProduto;
    public Spinner tipoProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_produto);

        nomeProduto = findViewById(R.id.edtCadastrarProdutoNome);
        descricaoProduto = findViewById(R.id.edtCadastrarProdutoDescricao);

        tipoProduto = findViewById(R.id.spnCadastrarProdutoTipo);
        // Cria um ArrayAdapter usando um string array e um layout padrão de spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tipo_produto_array, android.R.layout.simple_spinner_item);
        // Especifica o layout usado para mostrar a lista de tipos de produto
        //.simple_spinner_dropdown_item
        adapter.setDropDownViewResource(android.R.layout.select_dialog_item);
        // Conecta o adapter no spinner
        tipoProduto.setAdapter(adapter);
    }

    public void salvar(View view) {
        Produto p = new Produto();

        p.setNome(nomeProduto.getText().toString());
        p.setDescricao(descricaoProduto.getText().toString());
        p.setTipoProduto(tipoProduto.getSelectedItem().toString());
        p.salvar();


    }
}