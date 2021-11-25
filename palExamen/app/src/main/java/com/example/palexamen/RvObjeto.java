package com.example.palexamen;
//Encapsulador
public class RvObjeto {
    private String txt1;
    private String txt2;

    public RvObjeto(String txt1, String txt2) {
        this.txt1 = txt1;
        this.txt2 = txt2;
    }

    public String getTxt1() {
        return txt1;
    }

    public void setTxt1(String txt1) {
        this.txt1 = txt1;
    }

    public String getTxt2() {
        return txt2;
    }

    public void setTxt2(String txt2) {
        this.txt2 = txt2;
    }
}
