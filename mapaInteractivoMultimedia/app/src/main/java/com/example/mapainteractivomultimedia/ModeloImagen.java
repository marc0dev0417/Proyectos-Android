package com.example.mapainteractivomultimedia;

/**
 * The type Modelo imagen.
 */
public class ModeloImagen {
    private int id;
    private String srcOriginal;
    private String descripcion;

    /**
     * Instantiates a new Modelo imagen.
     *
     * @param id          the id
     * @param srcOriginal the src original
     * @param descripcion the descripcion
     */
    public ModeloImagen(int id, String srcOriginal, String descripcion) {
        this.id = id;
        this.srcOriginal = srcOriginal;
        this.descripcion = descripcion;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets src original.
     *
     * @return the src original
     */
    public String getSrcOriginal() {
        return srcOriginal;
    }

    /**
     * Sets src original.
     *
     * @param srcOriginal the src original
     */
    public void setSrcOriginal(String srcOriginal) {
        this.srcOriginal = srcOriginal;
    }

    /**
     * Gets descripcion.
     *
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets descripcion.
     *
     * @param descripcion the descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
