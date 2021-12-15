package com.example.reproducirmediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    Button btnReproducir;
    Button btnPausar;
    Button btnReanudar;
    Button btnParar;
    int posicion = 0;

    FuncionMedia iniciarAudio = () -> {
       mediaPlayer = MediaPlayer.create(this,R.raw.audio);
        mediaPlayer.start();
    };
    FuncionMedia pausarAudio = () -> {
        mediaPlayer.pause();
        posicion = mediaPlayer.getCurrentPosition();
    };
    FuncionMedia reanudarAudio = () -> {
      mediaPlayer.seekTo(posicion);
        mediaPlayer.start();
    };
    FuncionMedia pararAudio = () -> {
      mediaPlayer.stop();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReproducir = (Button)findViewById(R.id.btnReproducir);
        btnPausar = (Button)findViewById(R.id.btnPausar);
        btnReanudar = (Button)findViewById(R.id.btnReanudar);
        btnParar = (Button)findViewById(R.id.btnParar);

        btnReproducir.setOnClickListener(v ->{
            iniciarAudio.ejecutar();
        });
        btnPausar.setOnClickListener(v ->{
            pausarAudio.ejecutar();
        });
        btnReanudar.setOnClickListener(v -> {
            reanudarAudio.ejecutar();
        });
        btnParar.setOnClickListener(v -> {
            pararAudio.ejecutar();
        });
    }
}