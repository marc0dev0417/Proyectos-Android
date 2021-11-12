package com.example.practica8;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DiscoReciclador extends AppCompatActivity {

    RecyclerView reciclador;

    private RecyclerView.LayoutManager gestor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disco_reciclador);


        Button consultar = (Button)findViewById(R.id.botonCambiarConsultar);
        EditText grupo = (EditText)findViewById(R.id.txt_actualizarGrupo);

        reciclador = (RecyclerView)findViewById(R.id.miReciclador);


        //Creamos la lista especial (Encapsulador para meter los registros en el recycler

                List<Encapsulador> datos = new ArrayList<>();

                //El cursor que me permitirá moverme por las filas
                Cursor cursor = MainActivity.db.rawQuery("SELECT * FROM MisDiscos", null);


                //Si esta vacia la lista, es porque no hay nada :)
                if (cursor.getCount() == 0 )
                {
                    //datos.add(new Encapsulador("No hay registros", ""));
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Lista de Discos");
                    builder.setMessage("No hay discos disponibles");
                    builder.setPositiveButton("Menú Principal", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent actividadPrincipal = new Intent(getApplicationContext(), MainActivity.class);
                            Toast.makeText(getApplicationContext(), "Actividad Principal", Toast.LENGTH_LONG).show();

                            startActivity(actividadPrincipal);
                        }
                    });
                    builder.setNeutralButton("Añadir Discos", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent pantallaActividad = new Intent(getApplicationContext(), ActividadAnnadir.class);
                            Toast.makeText(getApplicationContext(), "Actividad Añadir", Toast.LENGTH_LONG).show();

                            startActivity(pantallaActividad);
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else
                {
                    while(cursor.moveToNext())
                    {
                        datos.add(new Encapsulador(cursor.getString(0),cursor.getString(1)));
                    }
                }
                //Tamaño fijo
                reciclador.setHasFixedSize(true);

                //Se adapta al layout de la aplicacion
                gestor = new LinearLayoutManager(getApplicationContext());
                reciclador.setLayoutManager(gestor);

                //Adapta las entradas del Encapsulador
                Adaptador adaptador = new Adaptador(datos);
                reciclador.setAdapter(adaptador);

                cursor.close();

                reciclador.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

                   GestureDetector gestureDetector = new GestureDetector(getApplicationContext(),
                           new GestureDetector.SimpleOnGestureListener(){
                                @Override
                                public boolean onSingleTapUp(MotionEvent event){
                                    return true;
                                }
                           });

                    @Override

                    //Al pulsar indentificamos los nodos hijos y podemos acceder a ellos
                    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                        View hijo = rv.findChildViewUnder(e.getX(), e.getY());

                        if(hijo != null && gestureDetector.onTouchEvent(e)){
                            //Accedo a la actividad actualizar discos
                            Intent pantallaConsulta = new Intent(rv.getContext(), ActividadActualizar.class);

                            //Formo un index para indentificar cada elemento del reciclerView
                            int index = rv.getChildAdapterPosition(hijo);


                            //Preparo los valores que voy a pasar en la otra actividad
                            pantallaConsulta.putExtra("valorDisco",datos.get(index).dameDisco());
                            pantallaConsulta.putExtra("valorGrupo", datos.get(index).dameGrupo());

                            Toast.makeText(getApplicationContext(), "Actividad Actualizar", Toast.LENGTH_LONG).show();
                            //Inicio la actividad consulta
                            startActivity(pantallaConsulta);
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
