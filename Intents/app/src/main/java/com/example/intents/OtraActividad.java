package com.example.intents;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OtraActividad extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otra_actividad);

        Intent ventanaSecundaria = null;

        TextView texto = (TextView) findViewById(R.id.text_secundaria);
        texto.setText(dameValor(ventanaSecundaria));
    }

public String dameValor(Intent actividad){
        actividad = getIntent();
    return actividad.getStringExtra("saluda");
}
}

