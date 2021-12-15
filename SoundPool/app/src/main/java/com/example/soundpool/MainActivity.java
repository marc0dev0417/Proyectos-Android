package com.example.soundpool;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private SoundPool conjuntoSonido;
    private int sonido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        conjuntoSonido = new SoundPool.Builder()
                .setMaxStreams(6)
                .setAudioAttributes(audioAttributes)
                .build();

        sonido = conjuntoSonido.load(this,R.raw.sound1, 1);
        conjuntoSonido.play(R.raw.sound1, 1, 1, 0, 0, 1);
        conjuntoSonido.autoPause();

        conjuntoSonido.release();
        conjuntoSonido = null;


    }
}