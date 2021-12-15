package com.example.mediarecoder;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class ActividadReproducir extends AppCompatActivity implements SurfaceHolder.Callback{

    private MediaPlayer mediaPlayer;
    private String rutaVideo;
    private Button btnRegresar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_reproducir);

        btnRegresar = (Button)findViewById(R.id.btnRegresar);


        Intent actividadPrincipal = null;
        actividadPrincipal = getIntent();

        Log.d("buscar", "dame la ruta -> "+actividadPrincipal.getStringExtra("rutaVideo"));
        rutaVideo = actividadPrincipal.getStringExtra("rutaVideo");

        mediaPlayer = new MediaPlayer();

        SurfaceView superficie = (SurfaceView)findViewById(R.id.superficie);
        // Obteniendo el objeto SurfaceHolder a partir del SurfaceView
        SurfaceHolder holder = superficie.getHolder();
        holder.addCallback(this);

        btnRegresar.setOnClickListener(v -> {
            mediaPlayer.stop();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));

        });

    }
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        try {
            mediaPlayer.setDisplay(surfaceHolder);
            mediaPlayer.setDataSource(getApplicationContext(),Uri.parse(rutaVideo));
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IllegalArgumentException e) {
            Log.d("MEDIA_PLAYER", e.getMessage());
        } catch (IllegalStateException e) {
            Log.d("MEDIA_PLAYER", e.getMessage());
        } catch (IOException e) {
            Log.d("MEDIA_PLAYER", e.getMessage());
        }
    }
    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }
    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        mediaPlayer.release();
    }
}
