package com.example.mapainteractivomultimedia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * The type Adaptador imagen sonido.
 */
public class AdaptadorImagenSonido extends RecyclerView.Adapter<ImagenSonidoHolder>{
    private Context contextoImagenSonido;
    private List<SonidoImagen> listaSonidoImagen;


    /**
     * Instantiates a new Adaptador imagen sonido.
     *
     * @param contexto the contexto
     * @param lista    the lista
     *   lista para cargar las imagenes en el Recycler
     */
    public AdaptadorImagenSonido(Context contexto ,List<SonidoImagen> lista){
        this.contextoImagenSonido = contexto;
        this.listaSonidoImagen = lista;
    }
    @NonNull
    @Override
    public ImagenSonidoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.imagen, parent, false);
        ImagenSonidoHolder imagSonHolder = new ImagenSonidoHolder(vista);
        return imagSonHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImagenSonidoHolder holder, int position) {
        Glide.with(contextoImagenSonido).load(listaSonidoImagen.get(position).getSrcOriginalImagen()).into(holder.imageView);
    }
    @Override
    public int getItemCount() {
        return listaSonidoImagen.size();
    }
}

/**
 * The type Imagen sonido holder.
 */
class ImagenSonidoHolder extends RecyclerView.ViewHolder{

    /**
     * The Image view.
     */
    public ImageView imageView;
    /**
     * The Descripcion imagen.
     */
    public TextView descripcionImagen;
    /**
     * The Texto imagen.
     */
    public TextView textoImagen;

    /**
     * Instantiates a new Imagen sonido holder.
     *
     * @param itemView the item view
     */
    public ImagenSonidoHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.idImagen);
        descripcionImagen = itemView.findViewById(R.id.idDescripcion);
        textoImagen = itemView.findViewById(R.id.idTexto);
    }
}

