package com.example.mesqbeerapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostagemAdapter extends RecyclerView.Adapter<PostagemAdapter.MyViewHolder>{
    List<Postagem> lista;

    public PostagemAdapter(List<Postagem> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.card_postagem, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Postagem p = lista.get(position);

        //holder.tvNome.setText(p.getNome());
        //holder.tvTextoPostagem.setText(p.getTexto());
        //holder.ivImagem.setImageResource(p.getImagem());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNome;
        private TextView tvTextoPostagem;
        private ImageView ivImagem;
        private Button btGostar;
        private Button btComentar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //tvNome = itemView.findViewById(R.id.tvNome);
            //tvTextoPostagem = itemView.findViewById(R.id.tvTextoPostagem);
            //ivImagem = itemView.findViewById(R.id.ivImagem);
            //btGostar = itemView.findViewById(R.id.btGostar);
            //btComentar = itemView.findViewById(R.id.btComentario);
        }
    }

}
