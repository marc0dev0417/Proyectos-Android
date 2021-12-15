package com.example.grabaraudio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    File audioFile = null;
    MediaRecorder mediaRecorder;

    Button btnGrabar;
    Button btnParar;
    Button btnReproducir;

    FuncionMedia grabarAudio = () -> {
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

    FuncionMedia pararAudio = () -> {
      try {
          mediaRecorder.stop();
          mediaRecorder.release();
          mediaRecorder = null;
      }catch (Exception error){
          Toast.makeText(getApplicationContext(), "Debes grabar primero un audio", Toast.LENGTH_SHORT).show();
      }
    };

    FuncionMedia reproducirAudio = () -> {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, Uri.parse(String.valueOf(audioFile)));
        mediaPlayer.start();
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGrabar = (Button)findViewById(R.id.btnGrabarAudio);
        btnParar = (Button)findViewById(R.id.btnPararGrabacion);
        btnReproducir = (Button)findViewById(R.id.btnReproducirGrabacion);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 0);
        }
        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grabarAudio.ejecutar();
            }
        });

        btnParar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pararAudio.ejecutar();
            }
        });

        btnReproducir.setOnClickListener(v -> {
            reproducirAudio.ejecutar();
        });

    }
}