package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView reciclador;
    private RecyclerView.Adapter adaptador;
    private RecyclerView.LayoutManager gestor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reciclador = (RecyclerView)findViewById(R.id.myRecycler);

        List<Encapsulador> datos = new ArrayList<Encapsulador>();

        datos.add(new Encapsulador(R.drawable.moon, getString(R.string.titulo), getString(R.string.contenido)));
        datos.add(new Encapsulador(R.drawable.picture, getString(R.string.titulo), getString(R.string.contenido)));


        reciclador.setHasFixedSize(true);
        gestor = new LinearLayoutManager(this);
        reciclador.setLayoutManager(gestor);

        Adaptador adaptador = new Adaptador(datos);
        reciclador.setAdapter(adaptador);


    }
}