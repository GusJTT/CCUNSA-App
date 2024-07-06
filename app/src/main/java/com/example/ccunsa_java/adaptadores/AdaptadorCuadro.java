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

public class AdaptadorCuadro extends RecyclerView.Adapter<AdaptadorCuadro.ViewHolder>{
    private List<ObraDeArte> listaObrasDeArte;
    private Context contexto;
    private OnCuadroClickListener listener;

    public AdaptadorCuadro(List<ObraDeArte> listaObrasDeArte, Context contexto, OnCuadroClickListener listener) {
        this.listaObrasDeArte = listaObrasDeArte;
        this.contexto = contexto;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_lista_cuadros, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTituloCuadro.setText(listaObrasDeArte.get(position).getNombreCuadro());
        Log.d(TAG,"onBindViewHolder" + listaObrasDeArte.get(position).getNombreCuadro());
        holder.txtDescripcionCuadro.setText(listaObrasDeArte.get(position).getDescripcionCuadro());
        Log.d(TAG,"onBindViewHolder" + listaObrasDeArte.get(position).getDescripcionCuadro());
        Glide.with(contexto)
                .load(listaObrasDeArte.get(position).getFotoCuadro())
                .centerCrop()
                .into(holder.imgFotoCuadro);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    Log.d(TAG,"Se pulsó sobre item" + holder.getAdapterPosition());
                    listener.onCuadroClick(listaObrasDeArte.get(holder.getAdapterPosition()));
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return listaObrasDeArte.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgFotoCuadro;
        private TextView txtTituloCuadro;
        private TextView txtDescripcionCuadro;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            imgFotoCuadro = itemView.findViewById(R.id.imgFotoCuadro);
            txtTituloCuadro = itemView.findViewById(R.id.txtTituloCuadro);
            txtDescripcionCuadro = itemView.findViewById(R.id.txtDescripcionCuadro);
        }
    }
}
