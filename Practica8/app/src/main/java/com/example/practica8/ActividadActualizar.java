package com.example.practica8;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActividadActualizar extends AppCompatActivity {
    //Esto me permite importar los color que necesito del Directorio Resource.
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_actualizar);

        Button botonActualizar = (Button) findViewById(R.id.botonActualizar);
        Button botonVolverActualizar = (Button)findViewById(R.id.botonVolverActualizar);

        EditText grupo = (EditText) findViewById(R.id.txt_actualizarGrupo);
        EditText disco = (EditText) findViewById(R.id.txt_actualizarDisco);

        //Traeme los valores declarados en la actividad DiscoReciclador.
        Intent pantallaConsulta = getIntent();

        grupo.setText(pantallaConsulta.getStringExtra("valorGrupo"));
        disco.setText(pantallaConsulta.getStringExtra("valorDisco"));



        //Desabilitar el texto para no modificar => Será una constante para visualizar que grupo pertenece el disco.
        grupo.setEnabled(false);
        grupo.setTextColor(R.color.black);

        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {

                //Control para evitar los espacios en blanco

                //Añado el metodo trim para hacer el control de los espacios => elimina los espacios en blanco
            if(!disco.getText().toString().trim().equals("") && !disco.getText().toString().equals(pantallaConsulta.getStringExtra("valorDisco"))) {
                MainActivity.db.execSQL("UPDATE MisDiscos SET Disco='" + disco.getText().toString() + "' WHERE Grupo='" +
                        grupo.getText().toString() + "';");


                Toast.makeText(vista.getContext(), "Se ha actualizado el disco" + " "
                        + disco.getText().toString(), Toast.LENGTH_LONG).show();

                Intent pantallaConsulta = new Intent(getApplicationContext(), DiscoReciclador.class);
                startActivity(pantallaConsulta);
                }
            else{
                Toast.makeText(vista.getContext(), "No se ha actualizado el disco", Toast.LENGTH_LONG).show();
            }
            }
        });
        botonVolverActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallaPrincipal = new Intent(getApplicationContext(), MainActivity.class);
                Toast.makeText(getApplicationContext(), "Actividad Principal",Toast.LENGTH_LONG).show();

                startActivity(pantallaPrincipal);
            }
        });

    }
}
