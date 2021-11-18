package com.example.proyectominas;

import android.content.Context;
import android.graphics.Color;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;

public class Mina extends androidx.appcompat.widget.AppCompatButton {



    private boolean minado;
    private int contadorMinas = 0;

    public Mina(Context context) {
        super(context);

       // this.setBackgroundColor(Color.BLUE);
        this.setBackground(getResources().getDrawable(R.drawable.borde_boton));

        this.setMinHeight(0);

       // this.setBackgroundResource(R.drawable.spiker);
      if(estaMinado()) {
          //this.setBackgroundColor(Color.RED);
          //this.setBackgroundResource(R.drawable.spiker);

      }
    }
    public boolean estaMinado(){
        return minado;
    }
    public void ponEstadoMinado(boolean estado){
        this.minado = estado;
    }
}
