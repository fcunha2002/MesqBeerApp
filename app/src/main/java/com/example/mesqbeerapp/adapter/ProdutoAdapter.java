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
import com.example.mesqbeerapp.util.Formatacao;

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

        holder.tvNomeProduto.setText(p.getNome());
        holder.tvDescrProduto.setText(p.getDescricao());
        holder.ivImagem.setImageBitmap(p.getImagem());
        holder.tvPrecoProduto.setText(Formatacao.formataMoeda(p.getPreco()));

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNomeProduto;
        private TextView tvDescrProduto;
        private TextView tvPrecoProduto;
        private ImageView ivImagem;
        private Button btAdicionar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNomeProduto = itemView.findViewById(R.id.tvNomeProduto);
            tvDescrProduto = itemView.findViewById(R.id.tvDescrProduto);
            tvPrecoProduto = itemView.findViewById(R.id.tvPrecoProduto);
            ivImagem = itemView.findViewById(R.id.ivImagem);
            btAdicionar = itemView.findViewById(R.id.btAdicionar);
        }


    }

}
