package com.example.practica8;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ActividadAnnadir extends AppCompatActivity {

    EditText disco, grupo;
    RecyclerView reciclador;


    private RecyclerView.Adapter adaptador;
    private RecyclerView.LayoutManager gestor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_annadir);

        disco = (EditText)findViewById(R.id.txt_annadirDisco);
        grupo = (EditText)findViewById(R.id.txt_annadirGrupo);
        reciclador = (RecyclerView)findViewById(R.id.miReciclador);
        Button botonInsertar = (Button)findViewById(R.id.botonInsertar);

        botonInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // annadir(v);
               MainActivity.db.execSQL("INSERT INTO MisDiscos VALUES ('" + disco.getText().toString() + "','" + grupo.getText().toString()+"')");

                Toast.makeText(getApplicationContext(),"Se añadió el disco "+ disco.getText().toString(), Toast.LENGTH_LONG).show();


            }
        });
    }
}
