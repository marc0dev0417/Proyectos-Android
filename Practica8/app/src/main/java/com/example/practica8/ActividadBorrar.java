package com.example.practica8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActividadBorrar extends AppCompatActivity {

    String discoConsulta;
    String grupoConsulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_borrar);

        Button botonBorrar = (Button)findViewById(R.id.botonBorrar);
        Button botonVolver = (Button)findViewById(R.id.botonVolverBorrar);

        EditText disco = (EditText) findViewById(R.id.txt_borrarDisco);
        EditText grupo = (EditText) findViewById(R.id.txt_borrarGrupo);

        Intent pantallaConsulta = getIntent();


        //Traigo los valores de la actividad consulta
        discoConsulta = pantallaConsulta.getStringExtra("valorDisco");
        grupoConsulta = pantallaConsulta.getStringExtra("valorGrupo");


        botonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Si introducimos los valores incorrectos no se borra el disco
            if(!disco.getText().toString().equals("") && !grupo.getText().toString().equals("")) {
                //Sentencia para borrar los discos
                MainActivity.db.execSQL("DELETE FROM MisDiscos WHERE Disco ='" + disco.getText().toString() + "'AND Grupo ='" + grupo.getText().toString() + "'");
                Toast.makeText(getApplicationContext(), "Se ha eliminado el disco " + disco.getText().toString(), Toast.LENGTH_LONG).show();
            }
                    else if(disco.getText().toString().equals("") && grupo.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "No se ha eliminado " + disco.getText().toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallaPrincipal = new Intent(v.getContext(), MainActivity.class);
                Toast.makeText(getApplicationContext(), "Principal", Toast.LENGTH_LONG).show();
                startActivity(pantallaPrincipal);
            }
        });
    }
}
