package com.example.mapainteractivomultimedia;

import android.content.ContextWrapper;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;

/**
 * The type Actividad grabar.
 */
public class ActividadGrabar extends AppCompatActivity {

    private File audioFile = null;
    private MediaRecorder mediaRecorder;
    private Button btnGrabar;
    private Button btnParar;
    private Button btnReproducir;

    /**
     * The Grabar audio.
     */
    funcionLambda grabarAudio = () -> {
        ContextWrapper cw = new ContextWrapper(this);
        audioFile = new File(cw.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),"audio.3gp");

        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setOutputFile(audioFile.getAbsolutePath());

        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    };
    /**
     * The Parar audio.
     */
    funcionLambda pararAudio = () -> {
        try{
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
        }catch (Exception error){
            Toast.makeText(getApplicationContext(), "Debes grabar primero un audio :)", Toast.LENGTH_SHORT).show();
        }
    };
    /**
     * The Reproducir audio.
     */
    funcionLambda reproducirAudio = () -> {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, Uri.parse(String.valueOf(audioFile)));
        mediaPlayer.start();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grabar_audio);

        btnGrabar = (Button)findViewById(R.id.btnGrabarAudio);
        btnParar = (Button)findViewById(R.id.btnPararGrabacion);
        btnReproducir = (Button)findViewById(R.id.btnReproducirGrabacion);

        btnGrabar.setOnClickListener(v -> {
            grabarAudio.ejecutar();
        });
        btnParar.setOnClickListener(v -> {
            pararAudio.ejecutar();
        });
        btnReproducir.setOnClickListener(v -> {
            reproducirAudio.ejecutar();
        });
    }
}