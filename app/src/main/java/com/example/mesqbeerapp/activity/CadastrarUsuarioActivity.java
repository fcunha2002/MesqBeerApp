package com.example.mesqbeerapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mesqbeerapp.R;

public class CadastrarUsuarioActivity extends AppCompatActivity {
    private EditText campoNome;
    private EditText campoEmail;
    private EditText campoSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        campoNome = findViewById(R.id.edtCadastrarUsuarioNome);
        campoEmail = findViewById(R.id.edtCadastrarUsuarioEmail);
        campoSenha = findViewById(R.id.edtCadastrarUsuarioSenha);
    }

    public void criar(View view) {
        String textoNome = campoNome.getText().toString();
        String textoEmail = campoEmail.getText().toString();
        String textoSenha = campoSenha.getText().toString();

        //Validar se os campos foram preenchidos
        if (!textoNome.isEmpty()){
            if (!textoEmail.isEmpty()){
                if (!textoSenha.isEmpty()){
//                    Usuario usuario = new Usuario();
//                    usuario.setNome(textoNome);
//                    usuario.setEmail(textoEmail);
//                    usuario.setSenha(textoSenha);
//                    cadastrarUsuario(usuario);
                } else {
                    Toast.makeText(CadastrarUsuarioActivity.this, "Preencha a Senha", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(CadastrarUsuarioActivity.this, "Preencha o Email", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(CadastrarUsuarioActivity.this, "Preencha o Nome", Toast.LENGTH_SHORT).show();
        }

//        //Implementar Toast com mensagem de sucesso
//        Intent i = new Intent(this, LoginActivity.class);
//        startActivity(i);
    }
}