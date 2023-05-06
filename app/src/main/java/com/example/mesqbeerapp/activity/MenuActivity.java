package com.example.mesqbeerapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mesqbeerapp.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void catalogo(View view) {
            Intent i = new Intent(this, ListarProdutosActivity.class);
            startActivity(i);
        }
    }
