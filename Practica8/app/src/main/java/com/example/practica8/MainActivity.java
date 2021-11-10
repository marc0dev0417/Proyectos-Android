package com.example.practica8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    protected static SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cambiar();
        db = openOrCreateDatabase("MisDiscos", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS MisDiscos(Disco VARCHAR, Grupo VARCHAR);");

    }
    public void cambiar(){
        Button btnAnnadir = (Button)findViewById(R.id.botonAnadir);
        Button btnBorrar = (Button)findViewById(R.id.botonBorrar);
        Button btnActualizar = (Button)findViewById(R.id.botonActualizar);
        Button btnConsultar = (Button)findViewById(R.id.botonConsultar);

        //Escuchador para el boton de añadir
        btnAnnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {
                Intent actividadAnnadir = new Intent(vista.getContext(), ActividadAnnadir.class);
                startActivity(actividadAnnadir);
            }
        });
        //Escuchador para el boton de borrar
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {
                Intent actividadBorrar = new Intent(vista.getContext(), ActividadBorrar.class);
                startActivity(actividadBorrar);
            }
        });
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {
                Intent actividadActualizar = new Intent(vista.getContext(), ActividadActualizar.class);
                startActivity(actividadActualizar);
            }
        });
        //Escuchador para el boton de Consultar
        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {
                Intent actividadConsultar = new Intent(vista.getContext(), DiscoReciclador.class);
                startActivity(actividadConsultar);
            }
        });
    }
}