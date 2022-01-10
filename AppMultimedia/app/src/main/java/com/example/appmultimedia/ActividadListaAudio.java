package com.example.appmultimedia;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ActividadListaAudio extends AppCompatActivity {

    private Button btnMostrar;
    private Intent actividadImagenUrl;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar_imagenes_audio);

        btnMostrar = (Button)findViewById(R.id.btnListaImagenes);
        actividadImagenUrl = getIntent();

            btnMostrar.setOnClickListener(v -> {
              ArrayList<String> lista = actividadImagenUrl.getStringArrayListExtra("listaImagenes");

              for(int x=0; x<lista.size(); x++){
                   Log.d("lista", " lista enlaces => "+lista.get(x)+" numero enlace "+x+" y el tamaño de la lista es "+lista.size());
              }
            });
    }
}
