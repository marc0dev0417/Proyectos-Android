package com.example.repasoexamenmoviles;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Annadir extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.annadir);

        EditText textoNombre = (EditText) findViewById(R.id.textAnnadirNombre);
        EditText textoValor = (EditText)findViewById(R.id.textAnnadirValor);

        Button anadirBecas = (Button)findViewById(R.id.btnAnnadirBecas);

        anadirBecas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.db.execSQL("INSERT INTO BecaTabla VALUES ('" + textoNombre.getText().toString() + "','" + textoValor.getText().toString()+"')");
            }
        });

    }
}
