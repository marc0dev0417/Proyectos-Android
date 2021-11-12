package com.example.practica8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected static SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Solo creo una vez la base de datos

        /*
        Si hubiera creado la base de datos en varias funcionalides se resetea la base datos.

        Solamente se crea una vez para evitar ese problema, por ello he utilizado el modificador de acceso protected para usarlo
            en distintas ventanas
         */
        cambiar();
        db = openOrCreateDatabase("MisDiscos", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS MisDiscos(Disco VARCHAR, Grupo VARCHAR);");

    }
    //Metodo para cambiar de actividades
    public void cambiar(){
        Button btnAnnadir = (Button)findViewById(R.id.botonCambiarAnadir);
        Button btnBorrar = (Button)findViewById(R.id.botonCambiarBorrar);
        Button btnConsultar = (Button)findViewById(R.id.botonCambiarConsultar);

        /*
        Cada actividad tendrá su clase custom
         */

        //Escuchador para el boton de añadir
        btnAnnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {
                Intent actividadAnnadir = new Intent(vista.getContext(), ActividadAnnadir.class);
                Toast.makeText(getApplicationContext(), "Actividad Añadir", Toast.LENGTH_LONG).show();
                startActivity(actividadAnnadir);
            }
        });
        //Escuchador para el boton de borrar
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {
                Intent actividadBorrar = new Intent(vista.getContext(), ActividadBorrar.class);
                Toast.makeText(getApplicationContext(), "Actividad Borrar", Toast.LENGTH_LONG).show();
                startActivity(actividadBorrar);
            }
        });
        //Escuchador para el boton de Consultar
        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {
                Intent actividadConsultar = new Intent(vista.getContext(), DiscoReciclador.class);
                Toast.makeText(getApplicationContext(), "Actividad Consultar", Toast.LENGTH_LONG).show();
                startActivity(actividadConsultar);
            }
        });
    }
}