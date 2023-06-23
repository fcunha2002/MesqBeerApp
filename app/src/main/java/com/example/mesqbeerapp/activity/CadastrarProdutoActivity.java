package com.example.mesqbeerapp.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.drawable.BitmapDrawable;
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
import com.example.mesqbeerapp.util.ConfiguracaoFirebase;
import com.example.mesqbeerapp.util.Permissao;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class CadastrarProdutoActivity extends AppCompatActivity {

    private final String[] permissoes = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
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
        adapter.setDropDownViewResource(android.R.layout.select_dialog_item);
        // Conecta o adapter no spinner
        tipoProduto.setAdapter(adapter);
    }

    public void salvar(View view) {
        Produto p = new Produto();

        p.setNome(nomeProduto.getText().toString());
        p.setDescricao(descricaoProduto.getText().toString());
        p.setTipoProduto(tipoProduto.getSelectedItem().toString());
        p.setPreco(Double.parseDouble(precoProduto.getText().toString()));
        p.setQuantidade(Integer.parseInt(quantidadeProduto.getText().toString()));
        p.setEstoque(Integer.parseInt(estoqueProduto.getText().toString()));
        p.setImagem(((BitmapDrawable)fotoProduto.getDrawable()).getBitmap());
        p.atribuiId();

        salvarImagem(p);
    }

    private void salvarImagem(Produto p){
        //Converter os dados da imagem para armazenar no Firebase
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap imagem = p.getImagem();
        imagem.compress(Bitmap.CompressFormat.JPEG, 70, baos);
        byte[] dadosImagem = baos.toByteArray();

        //Salvar imagem no Firebase
        StorageReference storageReference = ConfiguracaoFirebase.getFirebaseStorage();
        StorageReference imagemRef = storageReference
                .child("imagens")
                .child(p.getId() + ".jpeg");
        UploadTask uploadTask = imagemRef.putBytes(dadosImagem);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CadastrarProdutoActivity.this,
                    "Erro ao fazer upload da foto. Produto não cadastrado.",
                    Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                p.salvar();

                nomeProduto.setText("");
                descricaoProduto.setText("");
                precoProduto.setText("");
                quantidadeProduto.setText("");
                estoqueProduto.setText("");
                tipoProduto.setSelection(0);
                fotoProduto.setImageResource(R.drawable.logo);

                Toast.makeText(CadastrarProdutoActivity.this,
                        "Produto cadastrado com sucesso!",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int permissaoResultado : grantResults) {
            if (permissaoResultado == PackageManager.PERMISSION_DENIED) {
                alertaValidacaoPermissao();
            }
        }
    }

    ActivityResultLauncher<Intent> cameraResultLauncher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Bitmap imagem = null;

                    try {
                        imagem = (Bitmap) result.getData().getExtras().get("data");
                        if (imagem != null) {
                            fotoProduto.setImageBitmap(imagem);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    public void abreCamera(View view) {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (i.resolveActivity(getPackageManager()) != null) {
            cameraResultLauncher.launch(i);
        }
    }

    ActivityResultLauncher<Intent> galeriaResultLauncher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Bitmap imagem = null;

                    try {
                        Uri uriImagemSelec = (Uri) result.getData().getData();
                        ImageDecoder.Source source = null;
                        //Este código funciona apenas da versão 9.0 do Android em diante
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                            source = ImageDecoder.createSource(getContentResolver(), uriImagemSelec);
                            imagem = ImageDecoder.decodeBitmap(source);
                        }

                        if (imagem != null) {
                            fotoProduto.setImageBitmap(imagem);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    public void abreGaleria(View view) {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (i.resolveActivity(getPackageManager()) != null) {
            galeriaResultLauncher.launch(i);
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