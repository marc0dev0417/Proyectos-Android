package com.example.mapainteractivomultimedia;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * The type Actividad canva paint.
 */
public class ActividadCanvaPaint extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MiPlantilla plantilla = new MiPlantilla(this);

        setContentView(plantilla);

    }
}
