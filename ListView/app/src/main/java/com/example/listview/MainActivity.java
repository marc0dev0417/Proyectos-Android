package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView texto= (TextView) findViewById(R.id.textView);

        texto.setText(R.string.elige);

        //Creamos un array con los elementos seleccionables
        String [] elementos = {getString(R.string.galicia), getString(R.string.asturias), getString(R.string.cantabria),
                getString(R.string.navarra),getString(R.string.aragon)};

        //Creamos un adaptador de texto (String)
        ArrayAdapter<String> adaptador;

        // Obtenemos una referencia a la lista
        ListView l = (ListView) findViewById(R.id.lista);

        //Creamos el adaptador
        adaptador = new ArrayAdapter<String>(this,R.layout.fila, elementos);

        //Añadimos el listener
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                texto.setText("Has elegido "+parent.getItemAtPosition(position).toString());
            }
        });
        //Le damos el adaptador a la lista
        l.setAdapter(adaptador);
    }
}