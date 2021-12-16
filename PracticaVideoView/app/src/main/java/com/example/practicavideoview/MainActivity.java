package com.example.practicavideoview;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    Button btnReproducir;
    Button btnPausar;

    VideoView videoView;

        FuncionLambda iniciarVideo = () ->{
          videoView.start();
        };
        FuncionLambda pausarVideo = () -> {
            videoView.pause();
        };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReproducir = (Button)findViewById(R.id.btnReproducir);
        btnPausar = (Button) findViewById(R.id.btnPausar);

        videoView = (VideoView) findViewById(R.id.idVideoView);
        //Si el vídeo está en la carpeta res/raw
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video;

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                iniciarVideo.ejecutar();
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.d("videoView", "completed");
            }
        });


        Uri videoUri = Uri.parse(videoPath);
        videoView.setVideoURI(videoUri);

        btnReproducir.setOnClickListener(v -> {
            iniciarVideo.ejecutar();
        });
        btnPausar.setOnClickListener( v -> {
            pausarVideo.ejecutar();
        });
    }
}