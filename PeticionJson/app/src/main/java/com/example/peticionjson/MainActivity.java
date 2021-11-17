package com.example.peticionjson;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectivityManager administradorConectividad =
                (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo conexionActiva = administradorConectividad.getActiveNetworkInfo();

        boolean isConnected = conexionActiva != null &&
                conexionActiva.isConnectedOrConnecting();

        Log.d("msg", String.valueOf(isConnected));

        new MiTarea().execute("https://servicios.ine.es/wstempus/js/es/DATOS_TABLA/43491?tip=AM");
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


                for(int x=0; x<arrayJson.length(); x++){
                   // Log.d("buscame", String.valueOf(arrayJason.get(x)));
                        JSONObject jsonObjeto = arrayJson.getJSONObject(x);
                           Log.d("buscame","buscame "+String.valueOf(jsonObjeto.getString("Nombre")));
                    JSONArray arrayJasonGeneral = jsonObjeto.getJSONArray("Data");

                    JSONObject jsonValor = arrayJasonGeneral.getJSONObject(0);

                    Log.d("busca", "busca data=> "+jsonValor.getDouble("Valor"));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                JSONArray arrayGeneral = new JSONArray(result);

                for(int x=0; x<arrayGeneral.length(); x++){
                    JSONObject objetoNombre = arrayGeneral.getJSONObject(x);
                    Log.d("valores", "nombre => "+objetoNombre.getString("Nombre"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}