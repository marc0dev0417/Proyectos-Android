package com.example.palexamen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = findViewById(R.id.butSMS);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, VistaSMS.class);
                startActivity(it);
            }
        });
         b = findViewById(R.id.butConexion);
         b.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent it = new Intent(MainActivity.this, VistaHttp.class);
                 startActivity(it);
             }
         });
         b = findViewById(R.id.butSocket);
         b.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent it = new Intent(MainActivity.this, VistaSocket.class);
                 startActivity(it);
             }
         });
         b = findViewById(R.id.butRecycler);
         b.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Log.d("TAG", "onClick: ???????");
                 Intent it = new Intent(MainActivity.this, VistaRecycler.class);
                 startActivity(it);
             }
         });
    }
}