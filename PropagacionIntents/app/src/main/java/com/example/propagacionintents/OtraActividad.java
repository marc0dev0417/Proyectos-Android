package com.example.propagacionintents;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class OtraActividad extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otra_actividad);

        Intent i = new Intent();

        setResult(RESULT_OK, i);
        finish();
    }
}
