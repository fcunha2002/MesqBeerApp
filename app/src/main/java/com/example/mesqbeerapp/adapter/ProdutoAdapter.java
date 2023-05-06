package com.example.mesqbeerapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mesqbeerapp.R;
import com.example.mesqbeerapp.model.Produto;

import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.MyViewHolder>{
    List<Produto> lista;

    public ProdutoAdapter(List<Produto> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.card_produto, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Produto p = lista.get(position);

        //holder.tvNome.setText(p.getNome());
        //holder.tvTextoProduto.setText(p.getTexto());
        //holder.ivImagem.setImageResource(p.getImagem());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        /*private TextView tvNome;
        private TextView tvTextoProduto;
        private ImageView ivImagem;
        private Button btGostar;
        private Button btComentar;*/

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //tvNome = itemView.findViewById(R.id.tvNome);
            //tvTextoProduto = itemView.findViewById(R.id.tvTextoProduto);
            //ivImagem = itemView.findViewById(R.id.ivImagem);
            //btGostar = itemView.findViewById(R.id.btGostar);
            //btComentar = itemView.findViewById(R.id.btComentario);
        }
    }

}
