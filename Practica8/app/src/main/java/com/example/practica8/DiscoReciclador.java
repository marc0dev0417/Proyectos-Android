package com.example.practica8;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DiscoReciclador extends AppCompatActivity {

    RecyclerView reciclador;

    private RecyclerView.LayoutManager gestor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disco_reciclador);


        Button consultar = (Button)findViewById(R.id.botonConsultar);

        reciclador = (RecyclerView)findViewById(R.id.miReciclador);

                List<Encapsulador> datos = new ArrayList<>();

                //MainActivity.db.rawQuery("SELECT * FROM MisDiscos", null);

                Cursor cursor = MainActivity.db.rawQuery("SELECT * FROM MisDiscos", null);

                if (cursor.getCount() == 0 )
                {
                    datos.add(new Encapsulador("No hay registros", ""));
                }
                else
                {
                    while(cursor.moveToNext())
                    {
                        datos.add(new Encapsulador(cursor.getString(0),cursor.getString(1)));
                    }
                }
                reciclador.setHasFixedSize(true);

                gestor = new LinearLayoutManager(getApplicationContext());
                reciclador.setLayoutManager(gestor);

                Adaptador adaptador = new Adaptador(datos);
                reciclador.setAdapter(adaptador);

                cursor.close();
            }
}
