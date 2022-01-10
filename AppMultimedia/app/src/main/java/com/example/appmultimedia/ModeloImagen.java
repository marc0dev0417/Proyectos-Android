package com.example.appmultimedia;

public class ModeloImagen {
    private int id;
    private String srcOriginal;
    private String descripcion;

    public ModeloImagen(int id, String srcOriginal, String descripcion) {
        this.id = id;
        this.srcOriginal = srcOriginal;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSrcOriginal() {
        return srcOriginal;
    }

    public void setSrcOriginal(String srcOriginal) {
        this.srcOriginal = srcOriginal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
