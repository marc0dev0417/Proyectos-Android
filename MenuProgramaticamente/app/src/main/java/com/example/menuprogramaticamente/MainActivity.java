package com.example.menuprogramaticamente;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        Menu submenu = menu.addSubMenu("Ajustes");
        submenu.add("Cambia el color");
        submenu.add("Cambia la letra");

        menu.add("Información");
        return true;
    }
}