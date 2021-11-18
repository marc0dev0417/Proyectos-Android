package com.example.proyectominas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

 int contador = 0;
 int filas = 8;
 int columnas = 8;
 String[] opcionesRadioButton = {"Nivel Principiante", "Nivel Amateur", "Nivel Avanzado"};
 int valorTabla = 0;
 boolean hasPerdido = false;

 TableLayout tabla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       tabla = (TableLayout) findViewById(R.id.tablaMinas);

        Mina[][] minas = new Mina[8][8];

        hacemeUnHijo(filas, columnas, 10, minas);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Instrucciones");
        menu.add("Nuevo juego").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                    switch (valorTabla) {
                        case 0:
                            Toast.makeText(getApplicationContext(), "Has seleccionado normal", Toast.LENGTH_LONG).show();

                            tabla.removeAllViews();

                            Mina[][] minasPrincipiante = new Mina[8][8];
                            hacemeUnHijo(8, 8, 10, minasPrincipiante);
                            break;

                        case 1:
                            Toast.makeText(getApplicationContext(), "Has seleccionado medio", Toast.LENGTH_LONG).show();

                            tabla.removeAllViews();

                            Mina[][] minasAmateur = new Mina[12][12];
                            hacemeUnHijo(12, 12, 30, minasAmateur);
                            break;

                        case 2:
                            Toast.makeText(getApplicationContext(), "Has seleccionado dificil", Toast.LENGTH_LONG).show();

                            tabla.removeAllViews();

                            Mina[][] minasAvanzadas = new Mina[16][16];
                            hacemeUnHijo(16, 16, 60, minasAvanzadas);
                            break;
                    }
                return true;
                }
        });

        menu.add("Dificultad").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                mostrarMenu();
                return true;
            }
        });

        return true;
    }
    //Le hago un nene
    public void hacemeUnHijo(int fila, int columna, int cantidadMinas, Mina[][] minas) {

        //tablero
         minas = new Mina[fila][columna];

        TableLayout contenedorMinas = (TableLayout) findViewById(R.id.tablaMinas);


        for (int index = 0; index < minas.length; index++) {
            TableRow filaTabla = new TableRow(getApplicationContext());
            filaTabla.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT, 1
            ));
                filaTabla.setId(View.generateViewId());
            contenedorMinas.addView(filaTabla, index);

            for (int y = 0; y < minas[index].length; y++) {

                Mina mina = new Mina(this);

                mina.setId(View.generateViewId());

                mina.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT, 1
                ));
                minas[index][y] = mina;

               // mina.setLayoutParams(valorWeigthButton);

                filaTabla.addView(minas[index][y]);

                mina.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mina.setEnabled(false);
                        if (mina.estaMinado() == true) {
                            Toast.makeText(getApplicationContext(), "Has Perdido", Toast.LENGTH_LONG).show();
                            mina.setBackgroundResource(R.drawable.equispeque);
                                hasPerdido = true;
                        } else {
                            Toast.makeText(getApplicationContext(), "No es una mina", Toast.LENGTH_LONG).show();
                            hasPerdido = false;
                        }
                    }
                });

                if(hasPerdido){
                    tabla.setEnabled(false);
                }
            }
        }
        //Numero de minas que quiero dependiendo de las filas y columnas
        minas = ponerMina(cantidadMinas, minas);

    }

    public Mina[][] ponerMina(int numeroMinas, Mina[][] minas){
        ArrayList<Integer> listaAleatorios = new ArrayList<Integer>();

        for(int x=0; x<minas.length * minas.length; x++){
            listaAleatorios.add(x);
        }
        Collections.shuffle(listaAleatorios);

        for (int i = 0; i < numeroMinas; i++) {
            //El primer corchete es i / minas.length
            //El segundo corchete es i % mina.length
            int minasAleatorias = listaAleatorios.get(i);
            minas[minasAleatorias / minas.length][minasAleatorias % minas.length].ponEstadoMinado(true);
        }
        return minas;
    }
    public void mostrarMenu() {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("hola", "cancelar funciona");
            }
        });

        alert.setTitle("Dificultad");



        alert.setSingleChoiceItems(opcionesRadioButton, 0, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String item = opcionesRadioButton[i];

               switch (item){
                   case "Nivel Principiante" :
                       Toast.makeText(getApplicationContext(), "Has seleccionado normal", Toast.LENGTH_LONG).show();

                        tabla.removeAllViews();

                       Mina[][] minasPrincipiante = new Mina[8][8];
                       hacemeUnHijo(8,8, 10, minasPrincipiante);
                       valorTabla = 0;
                       break;

                   case "Nivel Amateur" :
                       Toast.makeText(getApplicationContext(), "Has seleccionado medio", Toast.LENGTH_LONG).show();

                       tabla.removeAllViews();

                       Mina[][] minasAmateur = new Mina[12][12];
                       hacemeUnHijo(12,12, 30, minasAmateur);
                       valorTabla = 1;
                       break;

                   case "Nivel Avanzado" :
                       Toast.makeText(getApplicationContext(), "Has seleccionado dificil", Toast.LENGTH_LONG).show();

                       tabla.removeAllViews();

                       Mina[][] minasAvanzadas = new Mina[16][16];
                       hacemeUnHijo(16,16, 60, minasAvanzadas);
                       valorTabla = 2;
                       break;
               }
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
}