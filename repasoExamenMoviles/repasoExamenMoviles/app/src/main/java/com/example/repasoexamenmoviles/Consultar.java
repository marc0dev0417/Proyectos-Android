package com.example.repasoexamenmoviles;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Consultar extends AppCompatActivity {
    RecyclerView miRecicladorBeca;

    private RecyclerView.LayoutManager gestor;
    List<Encapsulador> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulta_beca);



        miRecicladorBeca = (RecyclerView) findViewById(R.id.miReciclador);

        gestor = new LinearLayoutManager(getApplicationContext());


       // actualizarBaseDatos();
    }

    @Override
    protected void onResume() {
        super.onResume();

        actualizarBaseDatos();
    }

    public void actualizarBaseDatos() {


        //MainActivity.db.rawQuery("SELECT * FROM MisDiscos", null);

        Cursor cursor = MainActivity.db.rawQuery("SELECT * FROM BecaTabla", null);
        lista = new ArrayList<>();
        if (cursor.getCount() == 0) {
            lista.add(new Encapsulador("No hay registros", ""));
        } else {
            while (cursor.moveToNext()) {
                lista.add(new Encapsulador(cursor.getString(0), cursor.getString(1)));
            }
        }
        miRecicladorBeca.setHasFixedSize(true);

        gestor = new LinearLayoutManager(getApplicationContext());
        miRecicladorBeca.setLayoutManager(gestor);

        Adaptador adaptador = new Adaptador(lista);
        miRecicladorBeca.setAdapter(adaptador);

        cursor.close();

        miRecicladorBeca.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                View hijo = rv.findChildViewUnder(e.getX(), e.getY());

                if(hijo != null && gestureDetector.onTouchEvent(e)){
                    //Accedo a la actividad actualizar discos
                    Intent pantallaConsulta = new Intent(rv.getContext(), Actualizar.class);

                    //Formo un index para indentificar cada elemento del reciclerView
                    int index = rv.getChildAdapterPosition(hijo);


                    //Preparo los valores que voy a pasar en la otra actividad
                    pantallaConsulta.putExtra("nombreBeca",lista.get(index).dameNombreBeca());
                    pantallaConsulta.putExtra("valorBeca", lista.get(index).dameValorBeca());

                    Toast.makeText(getApplicationContext(), "Actividad Actualizar", Toast.LENGTH_LONG).show();
                    //Inicio la actividad consulta
                    startActivity(pantallaConsulta);
                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }

            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(),
                    new GestureDetector.SimpleOnGestureListener() {
                        @Override
                        public boolean onSingleTapUp(MotionEvent event) {
                            return true;
                        }
                    });
        });
    }

}
