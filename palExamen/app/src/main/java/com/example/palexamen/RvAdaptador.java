package com.example.palexamen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RvAdaptador extends RecyclerView.Adapter<RvAdaptador.MyHolder> {
    private List<RvObjeto> entrada = new ArrayList<>();

    public RvAdaptador(List<RvObjeto> entrada) {
        this.entrada = entrada;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerlista,parent,false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.RVAdaptadortv1.setText(entrada.get(position).getTxt1());
        holder.RVAdaptadortv2.setText(entrada.get(position).getTxt2());
    }
    /*
     * Pon esto
     * */
    @Override
    public int getItemCount() {
        return entrada.size();
    }
    /*
     * Pon esto
     * */
    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView RVAdaptadortv1;
        public TextView RVAdaptadortv2;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            RVAdaptadortv1 = itemView.findViewById(R.id.RVL_tv1);
            RVAdaptadortv2 = itemView.findViewById(R.id.RVL_tv2);
        }
    }
}
