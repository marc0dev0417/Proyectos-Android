package com.example.numerosprimos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    TextView textoWriter, textoResultado;
    LinkedList<Integer> listaPrimos = new LinkedList<Integer>();

    public boolean esPrimo(long numero) {
        boolean esPrimoActual = true;

        if (numero < 2) {
            esPrimoActual = false;
        } else {
            for (int x = 2; x * x <= numero; x++) {
                if (numero % x == 0) {
                    esPrimoActual = false;
                    break;
                }
            }
        }
        return esPrimoActual;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoWriter = (TextView) findViewById(R.id.textoWriter);
        textoResultado = (TextView) findViewById(R.id.textoResultado);
    }
    public void calcular(View vista) {

        int contador = 0;
        int posicion = Integer.valueOf(textoWriter.getText().toString());
        if(listaPrimos.isEmpty()) {
            listaPrimos.add(-1);
            listaPrimos.add(2);
        }
        if (posicion < listaPrimos.size()) {
            textoResultado.setText(String.valueOf(listaPrimos.get(posicion)));
        } else {
         //   listaPrimos.clear();
           // listaPrimos.add(-1);
            for (int x = listaPrimos.getLast(); listaPrimos.size() <= posicion;) {
                x++;
                if (esPrimo(x)) {
                    listaPrimos.add(x);
                }
            }
            textoResultado.setText(String.valueOf(listaPrimos.getLast()));
        }
    }
}