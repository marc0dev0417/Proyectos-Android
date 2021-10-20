package com.example.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    String saludo = "Buenos dias Marco";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cambiar();
    }
    public void cambiar(){
        Button pulsar = (Button)findViewById(R.id.btn_principal);
        pulsar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent actividadSecundaria = new Intent(view.getContext(), OtraActividad.class);
                actividadSecundaria.putExtra("saluda", saludo);
                startActivity(actividadSecundaria);


            }
        });
    }
}