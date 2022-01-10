package com.example.appmultimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button btnElegirImagen;
    private Button btnLista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnElegirImagen = (Button)findViewById(R.id.btnElegirImagen);
        btnLista = (Button)findViewById(R.id.btnLista);

        btnElegirImagen.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ActividadElegirImagen.class));
        });
        btnLista.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ActividadListaAudio.class));
        });
    }
}