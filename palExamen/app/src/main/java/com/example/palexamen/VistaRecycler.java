package com.example.palexamen;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class VistaRecycler extends AppCompatActivity {
    RecyclerView.Adapter adaptador;
    RecyclerView reciclador;
    RecyclerView.LayoutManager gestor;
    List<RvObjeto> datos;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_recycler);
        super.onCreate(savedInstanceState);
        cargaLista();
    }
    protected void cargaLista(){
        reciclador = (RecyclerView) findViewById(R.id.RV_lista);
        datos= new ArrayList<>();
        for (int i = 0; i < 20; i+=2) {
            datos.add(new RvObjeto("a:"+i,"b:"+(i+2)));
        }
        Log.d("TAG", "cargaLista: "+datos.size());
        reciclador.setHasFixedSize(true);
        gestor = new LinearLayoutManager(this);
        reciclador.setLayoutManager(gestor);
        adaptador = new RvAdaptador(datos);
        adaptador.getItemCount();
        reciclador.setAdapter(adaptador);
    }
}
