package com.example.redej2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url = "https://servicios.ine.es/wstempus/js/es/DATOS_TABLA/43491?tip=AM";

        ll = findViewById(R.id.linearLayout);
        Ejdetras ej = new Ejdetras();
        ej.execute(url.toString());

    }
    private class Ejdetras extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder res = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlconn = (HttpURLConnection) url.openConnection();
                url.openConnection();

                Log.d("a","Esta ok: "+ urlconn.getResponseCode());
                urlconn.setRequestMethod("GET");
                InputStream in = new BufferedInputStream(urlconn.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while((line=reader.readLine())!=null){
                    res.append(line);
                }
                Log.d("TAG", "doInBackground: "+res);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return res.toString();
        }
        @Override
        protected void onPostExecute(String res){
            super.onPostExecute(res);
            try {
                JSONArray jsonA = new JSONArray(res);
                for (int i = 0; i < jsonA.length() ; i++) {
                    JSONObject jsonO =   jsonA.getJSONObject(i);
                    JSONArray aux = new JSONArray(jsonO.get("Data").toString());
                    JSONObject OAux = aux.getJSONObject(0);
                    Log.d("a","Mirame Nombre -> "+ jsonO.get("Nombre"));
                    Log.d("a","Mirame Valor  -> "+ OAux.get("Valor"));

                    JSONArray Aaux2= jsonO.getJSONArray("MetaData");
                    JSONObject Oaux2 = Aaux2.getJSONObject(0);
                    Log.d("a", "Mirame Codigo -> "+ Oaux2.getString("Codigo"));
                    TextView tv = new TextView(MainActivity.this);

                    tv.setText("NOMBRE\n\t\t\t\t"+jsonO.get("Nombre")+"\nVALOR\n\t\t\t\t"+ OAux.get("Valor")+"\n");
                    ll.addView(tv);

                }
            } catch (JSONException e) {
                Log.d("a","Mirame no");
                e.printStackTrace();
            }


        }
    }
}
