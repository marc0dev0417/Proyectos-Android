package com.example.practica8;

public class Encapsulador {
    private String textoDisco;
    private String textoGrupo;

    public Encapsulador(String textoDisco, String textoGrupo){
        this.textoDisco = textoDisco;
        this.textoGrupo = textoGrupo;
    }
    public String dameDisco(){
        return textoDisco;
    }
    public String dameGrupo(){
        return textoGrupo;
    }
    public void ponerDisco(String disco){
        textoDisco = disco;
    }
    public void ponerGrupo(String grupo){
        textoGrupo = grupo;
    }
}
