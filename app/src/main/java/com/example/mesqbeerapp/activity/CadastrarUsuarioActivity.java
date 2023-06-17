package com.example.mesqbeerapp.activity;

import androidx.annotation.NonNull;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastrarUsuarioActivity extends AppCompatActivity {
    private EditText campoNome;
    private EditText campoEmail;
    private EditText campoSenha;
    private FirebaseAuth autenticacao;

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
                    Usuario usuario = new Usuario();
                    usuario.setNome(textoNome);
                    usuario.setEmail(textoEmail);
                    usuario.setSenha(textoSenha);
                    cadastrarUsuario(usuario);
                } else {
                    Toast.makeText(CadastrarUsuarioActivity.this, "Preencha a Senha", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(CadastrarUsuarioActivity.this, "Preencha o Email", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(CadastrarUsuarioActivity.this, "Preencha o Nome", Toast.LENGTH_SHORT).show();
        }
    }

    // Método que faz a conexão com o Firebase e salva um novo usuário na plataforma
    //Ferramenta Authentication
    public void cadastrarUsuario(final Usuario u){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(u.getEmail(), u.getSenha())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            try {
                                //Após cadastrar o usuário na área de autenticação,
                                //grava o restante dos dados no banco de dados
                                u.setId(autenticacao.getUid());
                                u.salvar();

                                Toast.makeText(CadastrarUsuarioActivity.this,
                                        "Usuário cadastrado com sucesso!",
                                        Toast.LENGTH_SHORT).show();
                                finish();
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                        } else {
                            String excecao;
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e){
                                //Senha com menos de 6 dígitos
                                excecao = "Digite uma senha mais forte!";
                            } catch (FirebaseAuthInvalidCredentialsException e){
                                excecao = "Digite um email válido!";
                            } catch (FirebaseAuthUserCollisionException e){
                                excecao = "Esta conta já foi cadastrada!";
                            } catch (Exception e) {
                                excecao = "Erro ao cadastrar usuário: " + e.getMessage();
                                e.printStackTrace();
                            }
                            Toast.makeText(CadastrarUsuarioActivity.this,
                                    excecao,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}