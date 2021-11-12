package com.example.practica8;

/*
    Esto equivale a la creacion de una clase Disco, en este caso se ha llamada Encapsulador
 */

public class Encapsulador {

    //Atributos
    private String textoDisco;
    private String textoGrupo;

    //Constructor
    public Encapsulador(String textoDisco, String textoGrupo){
        this.textoDisco = textoDisco;
        this.textoGrupo = textoGrupo;
    }

    //Getters => Dame los valores :)
    public String dameDisco(){
        return textoDisco;
    }
    public String dameGrupo(){
        return textoGrupo;
    }
    //Setters => Modificar los valores de los objetos instaciados por la clase :)
    public void ponerDisco(String disco){
        textoDisco = disco;
    }
    public void ponerGrupo(String grupo){
        textoGrupo = grupo;
    }
}
