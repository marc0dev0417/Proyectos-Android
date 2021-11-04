package com.example.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

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

        datos.add(new Encapsulador(R.drawable.moon, getString(R.string.titulo1), getString(R.string.contenido)));
        datos.add(new Encapsulador(R.drawable.picture, getString(R.string.titulo2), getString(R.string.contenido)));


        reciclador.setHasFixedSize(true);
        gestor = new LinearLayoutManager(this);
        reciclador.setLayoutManager(gestor);

        Adaptador adaptador = new Adaptador(datos);
        reciclador.setAdapter(adaptador);

        reciclador.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector  = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent event){
                    return true;
                }
            });
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                View hijo = rv.findChildViewUnder(e.getX(), e.getY());

                if (hijo != null && gestureDetector.onTouchEvent(e)){
                    int position = rv.getChildAdapterPosition(hijo);
                    Toast.makeText(getApplicationContext(), datos.get(position).getTextoTitulo(), Toast.LENGTH_LONG).show();
                }
               return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }
}