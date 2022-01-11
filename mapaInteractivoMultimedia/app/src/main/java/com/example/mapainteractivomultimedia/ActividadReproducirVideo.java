package com.example.mapainteractivomultimedia;

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

/**
 * The type Actividad reproducir video.
 */
public class ActividadReproducirVideo extends AppCompatActivity implements SurfaceHolder.Callback {

    private MediaPlayer mediaPlayer;
    private String rutaVideo;
    private Button btnRegresar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_reproducir_video);

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
            startActivity(new Intent(getApplicationContext(), ActividadElegirImagen.class));
        });
    }
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        try {
            mediaPlayer.setDisplay(holder);
            mediaPlayer.setDataSource(getApplicationContext(), Uri.parse(rutaVideo));
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
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }
    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        mediaPlayer.release();
    }
}
