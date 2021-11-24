package com.example.repasoexamenmoviles;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Eliminar extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eliminar);

        EditText textoNombre = (EditText) findViewById(R.id.textEliminarNombre);
        //EditText textoValor = (EditText)findViewById(R.id.textEliminarValor);

        Button eliminarBecas = (Button)findViewById(R.id.btnEliminarBecas);

        eliminarBecas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.db.execSQL("DELETE FROM BecaTabla WHERE Nombre ='" + textoNombre.getText().toString()+"'");
            }
        });
    }
}
