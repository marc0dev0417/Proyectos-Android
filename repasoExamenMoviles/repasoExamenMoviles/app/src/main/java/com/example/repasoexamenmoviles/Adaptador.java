package com.example.repasoexamenmoviles;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.MyHolder>{

    private List<Encapsulador> entradas;

    public Adaptador(List<Encapsulador>entradas){
        this.entradas = entradas;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila, parent, false);
        MyHolder mvh = new MyHolder(vista);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador.MyHolder holder, int position) {

        holder.nombre.setText(entradas.get(position).dameNombreBeca());
        holder.valor.setText(entradas.get(position).dameValorBeca());
    }

    @Override
    public int getItemCount() {
        return entradas.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        public TextView nombre;
        public TextView valor;

        public MyHolder(View vista) {
            super(vista);

            nombre = (TextView) vista.findViewById(R.id.nombreBeca);
            valor = (TextView) vista.findViewById(R.id.valorBeca);
        }
    }
}
