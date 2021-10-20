package com.example.recorridodeelementos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public void invisible(){
        TextView texto = (TextView)findViewById(R.id.textPrincipal);
        CheckBox checkPrincipal = (CheckBox) findViewById(R.id.checkVisibilidad);
        boolean isChecked = checkPrincipal.isChecked();
        Log.d("myapp","entrando");
        checkPrincipal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(isChecked){
                    texto.setText("hola");
                }
            }
        });
    }

    public void recorrerElementosContenedor(){

        /*
        Ejercicio: Implementa este código en Android Studio con un LinearLayout que
        contenga un TextView y un Button. Utiliza el Logcat para mostrar la información.
         */

        View vista;
        LinearLayout diseno = (LinearLayout)findViewById(R.id.disenoGeneral);
        Button miBoton;
        TextView miTexto;

        int totalElementos = diseno.getChildCount();

        for(int index=0; index<totalElementos; index++){
            vista = diseno.getChildAt(index);
            Log.d("buscame",vista.toString());

            //Ahora quiero saber el nombre de las clases de los widguets contenidos del contenedor

            Log.d("buscame",vista.getClass().getSimpleName());

            if(vista.getClass().getSimpleName().equals("MaterialButton")){
                miBoton = (Button) vista;
                miBoton.setText(R.string.cambioBoton);
            }
            else if(vista.getClass().getSimpleName().equals("MaterialTextView")){
                miTexto = (TextView) vista;
                miTexto.setText(R.string.cambioTexto);
            }
        }
    }

    public void anadimeHijos(int cantidad) {
        LinearLayout diseno = (LinearLayout) findViewById(R.id.disenoGeneral);
        TextView textoPrincipal = (TextView) findViewById(R.id.textPrincipal);

        for (int index = 0; index < cantidad; index++) {
            //Por cada vuelta del bucle voy creando un nuevo boton...
            Button nuevoBoton = new Button(this);

            //Le doy sus propiedades de diseño y posicion en el contenedor general
            nuevoBoton.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
        ));
            nuevoBoton.setText("btn "+(index + 1));
            nuevoBoton.setId(View.generateViewId());
            diseno.addView(nuevoBoton, index);

            nuevoBoton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    textoPrincipal.setText("Buenas Tardes");
                }
            });
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recorro los elementos =>
        recorrerElementosContenedor();
        anadimeHijos(2);
        invisible();
    }

}