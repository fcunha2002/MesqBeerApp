package com.example.mesqbeerapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mesqbeerapp.R;
import com.example.mesqbeerapp.model.Usuario;
import com.example.mesqbeerapp.util.ConfiguracaoFirebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginActivity extends AppCompatActivity {
    private EditText campoEmail;
    private EditText campoSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        campoEmail = findViewById(R.id.edtLoginEmail);
        campoSenha = findViewById(R.id.edtLoginSenha);

    }

    public void entrar(View view) {
        String textoEmail = campoEmail.getText().toString();
        String textoSenha = campoSenha.getText().toString();

        //Validar se os campos foram preenchidos
        if (!textoEmail.isEmpty()) {
            if (!textoSenha.isEmpty()) {
                Usuario usuario = new Usuario();
                usuario.setEmail(textoEmail);
                usuario.setSenha(textoSenha);
                validarUsuario(usuario);
            } else {
                Toast.makeText(LoginActivity.this, "Preencha a Senha", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(LoginActivity.this, "Preencha o Email", Toast.LENGTH_SHORT).show();
        }
    }

    public void cadastrar(View view) {
        Intent i = new Intent(this, CadastrarUsuarioActivity.class);
        startActivity(i);
    }

    private void validarUsuario(Usuario u) {
        FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(u.getEmail(), u.getSenha())
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        abrirTelaPrincipal();
                    } else {
                        String excecao;
                        try {
                            throw task.getException();
                        } catch (FirebaseAuthInvalidUserException e) {
                            excecao = "Usuário não está cadastrado!";
                        } catch (FirebaseAuthInvalidCredentialsException e) {
                            excecao = "Email ou senha inválidos!";
                        } catch (Exception e) {
                            excecao = "Erro ao validar usuário: " + e.getMessage();
                            e.printStackTrace();
                        }
                        Toast.makeText(LoginActivity.this,
                                excecao,
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }

    private void abrirTelaPrincipal() {
        Intent i = new Intent(this, MenuActivity.class);
        startActivity(i);
    }
}