package com.example.aplicacion1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    TextView tv;
    TextView tvDecreciente;
    Button b;
    int contadorCreciente = 0;
    int contadorDecreciente = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Iniciar la variable relacionandola con el xml
        tv = (TextView) findViewById(R.id.textoVista); //Que nos devuelva una vista
        tvDecreciente = (TextView) findViewById(R.id.segundoTexto);


    }
    public void click(View v ){

        contadorCreciente++;
        tv.setText(String.valueOf(contadorCreciente));

        contadorDecreciente--;
        tvDecreciente.setText(String.valueOf(contadorDecreciente));

    }
}