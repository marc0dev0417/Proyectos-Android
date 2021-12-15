package com.example.dialogos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String[] toDoList = {"comer","jugar","estudiar","beber"};
    ArrayList<String> listaToDoList = new ArrayList<String>();
    TextView textoLista;
    String guardarItemsMultiples = "";
    String guardarItemsUnicos = "";

    Button botonSaludo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoLista = (TextView) findViewById(R.id.listaItems);
        botonSaludo = (Button)findViewById(R.id.botonSaluda);

        //listaMultiple();
        //listaSimpleSeleccion();
        dialogoPersonalizado();
    }
    public void listaMultiple(){


        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);

        dialogo.setTitle("Lista para hacer");
        //dialogo.setMessage("Mi cuadro de dialogo");
        dialogo.setMultiChoiceItems(toDoList, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                String item = toDoList[which];
                if(isChecked) {
                    Log.d("buscame", " Item => "+item);
                    listaToDoList.add(item);
                }else if(listaToDoList.contains(item)){
                    int quitaPosicion = listaToDoList.indexOf(item);
                    listaToDoList.remove(quitaPosicion);
                }
            }
        });
        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Funciona el aceptar", Toast.LENGTH_SHORT).show();

                for(String items : listaToDoList){
                    guardarItemsMultiples += items+"\n";
                }
                textoLista.setText(guardarItemsMultiples);
            }
        });
        dialogo.show();
    }
    public void listaSimpleSeleccion(){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("Lista con selección simple");
        dialogo.setSingleChoiceItems(toDoList, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String item = toDoList[which];

                switch (which){
                    case 0 : Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
                    listaToDoList.add(item);
                    break;

                    case 1 : Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
                        listaToDoList.add(item);
                    break;

                    case 2 : Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
                        listaToDoList.add(item);
                    break;

                    case 3 : Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
                        listaToDoList.add(item);
                    break;
                }
            }
        });
        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                for(String item : listaToDoList){
                    textoLista.setText(item);
                }
            }
        });
        dialogo.show();
    }
    public void dialogoPersonalizado(){
        LayoutInflater inflador = getLayoutInflater();

        AlertDialog.Builder dialogoPersonalizado = new AlertDialog.Builder(this);

        dialogoPersonalizado.setView(inflador.inflate(R.layout.dialogo_personalizado, null));

        dialogoPersonalizado.show();
    }
}