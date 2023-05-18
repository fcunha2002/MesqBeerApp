package com.example.mesqbeerapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mesqbeerapp.R;

public class CadastrarUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);
    }

    public void criar(View view) {
        //Implementar Toast com mensagem de sucesso
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}