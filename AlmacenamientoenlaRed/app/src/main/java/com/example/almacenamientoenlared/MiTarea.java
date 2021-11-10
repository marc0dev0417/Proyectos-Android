package com.example.almacenamientoenlared;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MiTarea extends AsyncTask<String,Void,String>{

    @Override
    protected String doInBackground(String... params) {
        String urlString = params[0];
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL("https://www.google.com/humans.txt");
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
        //TextView texto = MainActivity.this.findViewById(R.id.textoPeticion);
    }
}
