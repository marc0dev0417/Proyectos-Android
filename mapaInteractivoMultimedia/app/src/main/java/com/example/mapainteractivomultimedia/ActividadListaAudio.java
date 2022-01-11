package com.example.mapainteractivomultimedia;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Actividad lista audio.
 */
public class ActividadListaAudio extends AppCompatActivity {
    private RecyclerView reciclador;
    private RecyclerView.LayoutManager gestor;
    private Intent actividadImagenImportada;
    private AdaptadorImagenSonido adaptadorImagenSonido;
    private String[] opcionesRadioButton = {"Grabar Audio", "Grabar Video"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_imagen_sonido);

        reciclador = (RecyclerView) findViewById(R.id.recicladorImagenSonido);

        actividadImagenImportada = getIntent();

        List<SonidoImagen> listaSonidoImagen = new ArrayList<SonidoImagen>();

        ArrayList<String> listaUrl = actividadImagenImportada.getStringArrayListExtra("listaImagenesUrl");

        for(String url : listaUrl){
            listaSonidoImagen.add(new SonidoImagen(url));
        }
        adaptadorImagenSonido = new AdaptadorImagenSonido(this, listaSonidoImagen);

        reciclador.setAdapter(adaptadorImagenSonido);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        reciclador.setLayoutManager(gridLayoutManager);

        reciclador.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector  = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent event){

                    return true;
                }
            });
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                View hijo = rv.findChildViewUnder(e.getX(), e.getY());
                if(hijo != null && gestureDetector.onTouchEvent(e)){
                    int position = rv.getChildAdapterPosition(hijo);
                    Toast.makeText(getApplicationContext(), "Esto funciona", Toast.LENGTH_SHORT).show();
                    mostrarMenu();
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

    /**
     * Mostrar menu.
     */
    public void mostrarMenu(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Acción cancelada", Toast.LENGTH_SHORT).show();
            }
        });
        alert.setTitle("Multimedia");

        alert.setSingleChoiceItems(opcionesRadioButton, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int posicion) {
                String item = opcionesRadioButton[posicion];

                switch (item){
                    case "Grabar Audio" :
                        Toast.makeText(getApplicationContext(), "Has seleccionado la acción grabar audio", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), ActividadGrabar.class));
                        break;
                    case "Grabar Video" :
                        Toast.makeText(getApplicationContext(), "Has seleccionado la acción grabar video", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), ActividadGrabarVideo.class));
                }
            }
        });
        AlertDialog dialogo = alert.show();
        dialogo.show();
    }
}
