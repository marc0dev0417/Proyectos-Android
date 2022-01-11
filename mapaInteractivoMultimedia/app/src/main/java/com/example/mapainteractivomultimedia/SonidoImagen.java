package com.example.mapainteractivomultimedia;

/**
 * The type Sonido imagen.
 */
public class SonidoImagen {
    private int idImagen;
    private String srcOriginalImagen;
    private String descripcionImagen;
    private String textoImagen;

    /**
     * Instantiates a new Sonido imagen.
     */
    public SonidoImagen(){}

    /**
     * Instantiates a new Sonido imagen.
     *
     * @param srcOriginalImagen the src original imagen
     */
    public SonidoImagen( String srcOriginalImagen){

        this.srcOriginalImagen = srcOriginalImagen;

    }

    /**
     * Gets id imagen.
     *
     * @return the id imagen
     */
    public int getIdImagen() {
        return idImagen;
    }

    /**
     * Sets id imagen.
     *
     * @param idImagen the id imagen
     */
    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }

    /**
     * Gets src original imagen.
     *
     * @return the src original imagen
     */
    public String getSrcOriginalImagen() {
        return srcOriginalImagen;
    }

    /**
     * Sets src original imagen.
     *
     * @param srcOriginalImagen the src original imagen
     */
    public void setSrcOriginalImagen(String srcOriginalImagen) {
        this.srcOriginalImagen = srcOriginalImagen;
    }

    /**
     * Gets descripcion imagen.
     *
     * @return the descripcion imagen
     */
    public String getDescripcionImagen() {
        return descripcionImagen;
    }

    /**
     * Sets descripcion imagen.
     *
     * @param descripcionImagen the descripcion imagen
     */
    public void setDescripcionImagen(String descripcionImagen) {
        this.descripcionImagen = descripcionImagen;
    }

    /**
     * Gets texto imagen.
     *
     * @return the texto imagen
     */
    public String getTextoImagen() {
        return textoImagen;
    }

    /**
     * Sets texto imagen.
     *
     * @param textoImagen the texto imagen
     */
    public void setTextoImagen(String textoImagen) {
        this.textoImagen = textoImagen;
    }
}
