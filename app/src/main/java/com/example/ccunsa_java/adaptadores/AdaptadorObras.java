package com.example.ccunsa_java.adaptadores;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ccunsa_java.R;
import com.example.ccunsa_java.objetos.ObraDeArte;

import java.util.List;

public class AdaptadorObras extends RecyclerView.Adapter<AdaptadorObras.ViewHolder>{
    private List<ObraDeArte> listaObras;
    private Context contexto;
    private OnObraClickListener listener;

    public AdaptadorObras(List<ObraDeArte> listaObras, Context contexto, OnObraClickListener listener) {
        this.listaObras = listaObras;
        this.contexto = contexto;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_lista_obras, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTituloObra.setText(listaObras.get(position).getTitulo());
        Log.d(TAG,"onBindViewHolder" + listaObras.get(position).getTitulo());
        holder.txtDescripcionObra.setText(listaObras.get(position).getDescripcion());
        Log.d(TAG,"onBindViewHolder" + listaObras.get(position).getDescripcion());
        Glide.with(contexto)
                .load(listaObras.get(position).getUrlImagen())
                .centerCrop()
                .into(holder.imgFotoObra);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    Log.d(TAG,"Se pulsó sobre item" + holder.getAdapterPosition());
                    listener.onObraClick(listaObras.get(holder.getAdapterPosition()));
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return listaObras.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgFotoObra;
        private TextView txtTituloObra;
        private TextView txtDescripcionObra;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            imgFotoObra = itemView.findViewById(R.id.imgFotoObra);
            txtTituloObra = itemView.findViewById(R.id.txtTituloObra);
            txtDescripcionObra = itemView.findViewById(R.id.txtDescripcionObra);
        }
    }
}
