package com.example.recyclerview;

import android.widget.ImageView;
import android.widget.TextView;

public class Encapsulador {
    private int idImagen;
    private String textoTitulo;
    private String textoContenido;

    public Encapsulador(int idImagen, String textoTitulo, String textoContenido){
        this.idImagen = idImagen;
        this.textoTitulo = textoTitulo;
        this.textoContenido = textoContenido;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public String getTextoTitulo() {
        return textoTitulo;
    }

    public String getTextoContenido() {
        return textoContenido;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }

    public void setTextoTitulo(String textoTitulo) {
        this.textoTitulo = textoTitulo;
    }

    public void setTextoContenido(String textoContenido) {
        this.textoContenido = textoContenido;
    }
}
