package com.example.palexamen;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class VistaHttp extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        HttpURLConnection urlConnection = null;

        final String[] res = new String[1];
        try {
            URL url = new URL("https://servicios.ine.es/wstempus/js/es/DATOS_TABLA/43491?tip=AM");
            urlConnection= (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert urlConnection != null;
        urlConnection.setConnectTimeout(20000);
            urlConnection.setReadTimeout(5000);
            HttpURLConnection finalUrlConnection = urlConnection;
            Log.d("mira", "Mira");
        new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        int n = finalUrlConnection.getResponseCode();
                        Log.d("mira", "Mira"+String.valueOf(n));
                        if(n == 200){
                            finalUrlConnection.setRequestMethod("GET");
                            InputStream in = new BufferedInputStream(finalUrlConnection.getInputStream());
                            BufferedReader br = new BufferedReader(new InputStreamReader(in));
                            String line;
                            StringBuilder result = new StringBuilder();
                            while ((line = br.readLine()) != null) {
                                result.append(line);
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    TextView tv = VistaHttp.this.findViewById(R.id.http_tv);
                                    tv.setText(leerJson(String.valueOf(result)));
                                }
                            });
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                    assert finalUrlConnection != null;
                        finalUrlConnection.disconnect();
                }
                }
            }).start();
    }
    public String leerJson(String jsonP){
        String resultado="";
        try {
            JSONArray jsonA = new JSONArray(jsonP);
            for (int i = 0; i < jsonA.length(); i++) {
                JSONObject jsonO = jsonA.getJSONObject(i);
                resultado += "\tNombre: "+jsonO.getString("Nombre")+"\n";
                JSONArray jsonA2 = jsonO.getJSONArray("Data");
                jsonO = jsonA2.getJSONObject(0);
                resultado += "\t\t\t\tValor: "+jsonO.getString("Valor")+"\n\n";

            }
            Log.d("mira",resultado);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    return resultado;
    }
}
