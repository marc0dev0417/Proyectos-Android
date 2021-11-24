package com.example.repasoexamenmoviles;

public class Encapsulador {
    private String textoNombreBeca;
    private String textovalorBeca;

    public Encapsulador(String textoNombreBeca, String textovalorBeca){
       this.textoNombreBeca = textoNombreBeca;
       this.textovalorBeca = textovalorBeca;
    }
    public String dameNombreBeca(){
        return textoNombreBeca;
    }
    public String dameValorBeca(){
        return textovalorBeca;
    }
    public void ponerNombreBeca(String nombre){
        textoNombreBeca = nombre;
    }
    public void ponerGrupo(String valor){
        textovalorBeca = valor;
    }
}
