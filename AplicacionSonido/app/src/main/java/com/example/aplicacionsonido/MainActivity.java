package com.example.aplicacionsonido;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button botonSonidoUno;
    Button botonSonidoDos;
    Button botonPausarSonido;

    private SoundPool conjuntoSonido;
    private int sonido1;
    private int sonido2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonSonidoUno = (Button)findViewById(R.id.botonSonidoUno);
        botonSonidoDos = (Button)findViewById(R.id.botonSonidoDos);
        botonPausarSonido = (Button)findViewById(R.id.botonSonidoPausar);

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        conjuntoSonido = new SoundPool.Builder()
                .setMaxStreams(1)//Veces simultaneamente
                .setAudioAttributes(audioAttributes)
                .build();

        sonido1 = conjuntoSonido.load(this,R.raw.sound1, 1);
        sonido2 = conjuntoSonido.load(this,R.raw.sound2, 1);

        botonSonidoUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conjuntoSonido.play(sonido1, 1, 1, 0, 1, 1);
            }
        });
        botonSonidoDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conjuntoSonido.play(sonido2, 1, 1, 0, 0, 1);
            }
        });
        botonPausarSonido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conjuntoSonido.autoPause();
            }
        });
    }
}