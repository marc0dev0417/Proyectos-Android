package com.example.appmultimedia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;


public class AdaptadorImagen extends RecyclerView.Adapter<ImagenHolder>  {

    private Context contexto;
    private List<ModeloImagen> listaModelo;

    public AdaptadorImagen(Context contexto, List<ModeloImagen> listaModelo) {
        this.contexto = contexto;
        this.listaModelo = listaModelo;
    }

    @NonNull
    @Override
    public ImagenHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(contexto).inflate(R.layout.imagen, parent, false);

        return new ImagenHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ImagenHolder holder, int position) {
        Glide.with(contexto).load(listaModelo.get(position).getSrcOriginal()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return listaModelo.size();
    }
}
   class ImagenHolder extends RecyclerView.ViewHolder{
    public ImageView imageView;

    public ImagenHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageViewItem);
    }
}