package com.example.repasoexamenmoviles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Actualizar extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actualizar);

        EditText textoNombre = (EditText) findViewById(R.id.textActualizarNombre);
        EditText textoValor = (EditText)findViewById(R.id.textActualizarValor);

        Button actualizar = (Button)findViewById(R.id.btnActualizarBeca);

        Intent pantallaConsulta = getIntent();

        textoNombre.setText(pantallaConsulta.getStringExtra("nombreBeca"));
        textoValor.setText(pantallaConsulta.getStringExtra("valorBeca"));

        textoNombre.setEnabled(false);
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.db.execSQL("UPDATE BecaTabla SET Valor='" + textoValor.getText().toString() + "' WHERE Nombre='" +textoNombre.getText().toString()+"'");
            }
        });
    }
}
