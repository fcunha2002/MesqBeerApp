package com.example.mesqbeerapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mesqbeerapp.R;
import com.example.mesqbeerapp.model.Usuario;
import com.example.mesqbeerapp.util.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class MenuActivity extends AppCompatActivity {
    private FirebaseAuth autenticacao;
    private TextView ola;
    private Usuario usr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ola = findViewById(R.id.tvOla);

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        usr = new Usuario();
        usr.setId(autenticacao.getUid());
        buscarUsuario();
    }

    public void buscarUsuario(){
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
        DatabaseReference usuarioRef = firebaseRef.child("usuarios").child(usr.getId());

        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usr = dataSnapshot.getValue(Usuario.class);
                ola.setText("Olá, " + usr.getNome());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void catalogo(View view) {
            Intent i = new Intent(this, ListarProdutosActivity.class);
            startActivity(i);
        }

    public void cadastro(View view) {
            Intent i = new Intent(this, CadastrarProdutoActivity.class);
            startActivity(i);
        }

}
