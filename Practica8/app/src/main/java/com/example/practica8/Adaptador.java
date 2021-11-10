package com.example.practica8;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.MyHolder> {

    private List<Encapsulador> entradas;

    public Adaptador(List<Encapsulador>entradas){
        this.entradas = entradas;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.disco, parent, false);
        MyHolder mvh = new MyHolder(vista);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador.MyHolder holder, int position) {

        holder.disco.setText(entradas.get(position).dameDisco());
        holder.grupo.setText(entradas.get(position).dameGrupo());
    }

    @Override
    public int getItemCount() {
        return entradas.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        public TextView disco;
        public TextView grupo;

        public MyHolder(View vista) {
            super(vista);

            disco = (TextView) vista.findViewById(R.id.disco);
            grupo = (TextView) vista.findViewById(R.id.grupo);
        }
    }
}
