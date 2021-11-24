package com.example.repasoexamenmoviles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView miRecicladorBeca;

    private RecyclerView.LayoutManager gestor;
    List<Encapsulador> lista = new ArrayList<>();
    public static SQLiteDatabase db;

    LinearLayout linearVertical;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase("Becas", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS BecaTabla(nombre VARCHAR, valor VARCHAR);");

        linearVertical = (LinearLayout) findViewById(R.id.linearVertical);

        annadirProgramaticamente();

        miRecicladorBeca = (RecyclerView) findViewById(R.id.miReciclador);

        gestor = new LinearLayoutManager(getApplicationContext());

        ConnectivityManager administradorConectividad =
                (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo conexionActiva = administradorConectividad.getActiveNetworkInfo();

        boolean isConnected = conexionActiva != null &&
                conexionActiva.isConnectedOrConnecting();

        Log.d("msg", String.valueOf(isConnected));

        new MainActivity.MiTarea().execute("https://servicios.ine.es/wstempus/js/es/DATOS_TABLA/43491?tip=AM");

    }
    public void annadirProgramaticamente(){

        for(int x=0; x<1; x++){//Añado tres botones
            Button boton = new Button(this);

            boton.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            boton.setText("Añadir");
            boton.setId(View.generateViewId());
            linearVertical.addView(boton, x);
            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(v.getContext(), Annadir.class));
                }
            });
        }
        for(int x=0; x<1; x++){
            Button boton = new Button(this);

            boton.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            boton.setText("Eliminar");
            boton.setId(View.generateViewId());
            linearVertical.addView(boton, x);

            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(v.getContext(), Eliminar.class));
                }
            });
        }

        for(int x=0; x<1; x++){
            Button boton = new Button(this);

            boton.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            boton.setText("Consultar");
            boton.setId(View.generateViewId());
            linearVertical.addView(boton, x);
            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(v.getContext(), Consultar.class));
                }
            });
        }
    }

    private class MiTarea extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            String urlString = params[0];
            StringBuilder result = new StringBuilder();
            try {
                URL url = new URL(urlString);
                try {
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    int codigoRespuesta = urlConnection.getResponseCode();

                    urlConnection.setConnectTimeout(20000);
                    urlConnection.setReadTimeout(5000);

                    urlConnection.setRequestMethod("GET");
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException error) {
                error.printStackTrace();
            }
            return result.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // do something with result
            try {
                JSONArray arrayJson = new JSONArray(result);
                File archivoDb = new File("/data/user/0/com.example.repasoexamenmoviles/databases/Becas");

                Log.d("base", "Base datos" + archivoDb.length());
              //  db.execSQL("DELETE FROM BecaTabla");

                Cursor cursor = MainActivity.db.rawQuery("SELECT * FROM BecaTabla", null);

                if (cursor.getCount() == 0 )
                {
                    for (int x = 0; x < arrayJson.length(); x++) {
                        // Log.d("buscame", String.valueOf(arrayJason.get(x)));
                        JSONObject jsonObjeto = null;

                        jsonObjeto = arrayJson.getJSONObject(x);
                        Log.d("buscame", "buscame " + String.valueOf(jsonObjeto.getString("Nombre")));
                        JSONArray arrayJasonGeneral = jsonObjeto.getJSONArray("Data");

                        JSONObject jsonValor = arrayJasonGeneral.getJSONObject(0);

                        db.execSQL("INSERT INTO BecaTabla VALUES ('" + jsonObjeto.getString("Nombre") + "','" + String.valueOf(jsonValor.getDouble("Valor")) + "')");
                        Log.d("busca", "busca valor=> " + jsonValor.getDouble("Valor"));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}