package com.example.mesqbeerapp.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mesqbeerapp.R;
import com.example.mesqbeerapp.model.Produto;
import com.example.mesqbeerapp.util.Permissao;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class CadastrarProdutoActivity extends AppCompatActivity {
    private static final int SELECAO_CAMERA = 100;

    private String[] permissoes = new String[]{
            //Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.CAMERA
    };

    public EditText nomeProduto;
    public EditText descricaoProduto;
    public EditText precoProduto;
    public EditText quantidadeProduto;
    public EditText estoqueProduto;
    public Spinner tipoProduto;
    public ImageView fotoProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_produto);

        //Validar permissões
        Permissao.validarPermissoes(permissoes, this, 1);

        nomeProduto = findViewById(R.id.edtCadastrarProdutoNome);
        descricaoProduto = findViewById(R.id.edtCadastrarProdutoDescricao);

        precoProduto = findViewById(R.id.edtCadastrarProdutoPreco);
        quantidadeProduto = findViewById(R.id.edtCadastrarProdutoQuantidade);
        estoqueProduto = findViewById(R.id.edtCadastrarProdutoEstoque);
        fotoProduto = findViewById(R.id.imvCadastrarProdutoFoto);

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
        p.getTamanhoProduto().setPreco(Double.parseDouble(precoProduto.getText().toString()));
        p.getTamanhoProduto().setQuantidade(Integer.parseInt(quantidadeProduto.getText().toString()));
        p.getTamanhoProduto().setEstoque(Integer.parseInt(estoqueProduto.getText().toString()));
        p.salvar();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int permissaoResultado : grantResults){
            if (permissaoResultado == PackageManager.PERMISSION_DENIED){
                alertaValidacaoPermissao();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            Bitmap imagem = null;

            try {
                switch (requestCode){
                    case SELECAO_CAMERA:
                        imagem = (Bitmap) data.getExtras().get("data");
                        break;
//                    case SELECAO_GALERIA:
//                        Uri uriImagemSelec = data.getData();
//                        ImageDecoder.Source source = ImageDecoder.createSource(getContentResolver(), uriImagemSelec);
//                        imagem = ImageDecoder.decodeBitmap(source);
//                        break;
                }

                if (imagem != null){
                    fotoProduto.setImageBitmap(imagem);

//                    //Converter os dados da imagem para armazenar no Firebase
//                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                    imagem.compress(Bitmap.CompressFormat.JPEG, 70, baos);
//                    byte[] dadosImagem = baos.toByteArray();
//
//                    //Salvar imagem no Firebase
//                    StorageReference imagemRef = storageReference
//                            .child("imagens")
//                            .child(usr.getId() + ".jpeg");
//                    UploadTask uploadTask = imagemRef.putBytes(dadosImagem);
//                    uploadTask.addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(ConfiguracoesActivity.this,
//                                    "Erro ao fazer upload da foto.",
//                                    Toast.LENGTH_LONG).show();
//                        }
//                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            Toast.makeText(ConfiguracoesActivity.this,
//                                    "Foto armazenada com sucesso.",
//                                    Toast.LENGTH_LONG).show();
//                        }
//                    });

                }

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    public void abreCamera(View view) {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (i.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(i, SELECAO_CAMERA);
        }
    }

    private void alertaValidacaoPermissao(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissões Negadas");
        builder.setMessage("Para utilizar o app é necessário aceitar as permissões de acesso.");
        builder.setCancelable(false);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}