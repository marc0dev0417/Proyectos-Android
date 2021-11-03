package com.example.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.Myholder>{

    private List<Encapsulador> entradas;

    public Adaptador(List<Encapsulador> entradas) {
        this.entradas = entradas;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.carta, parent, false);
        Myholder mvh = new Myholder(vista);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {
        holder.imagen.setImageResource(entradas.get(position).getIdImagen());
        holder.titulo.setText(entradas.get(position).getTextoTitulo());
        holder.texto.setText(entradas.get(position).getTextoContenido());
    }

    @Override
    public int getItemCount() {
        return entradas.size();
    }

    public static class Myholder extends RecyclerView.ViewHolder{
        public ImageView imagen;
        public TextView titulo;
        public TextView texto;

        public Myholder(View vista){
            super(vista);

            imagen = (ImageView)vista.findViewById(R.id.imagen);
            titulo = (TextView)vista.findViewById(R.id.titulo);
            texto = (TextView) vista.findViewById(R.id.texto);
        }
    }
}
