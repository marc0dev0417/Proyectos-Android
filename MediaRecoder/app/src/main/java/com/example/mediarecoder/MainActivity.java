package com.example.mediarecoder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SurfaceHolder.Callback
{
    File videoFile;
    MediaRecorder recorder;
    SurfaceHolder holder;
    boolean recording;

    Button btnReproducir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO}, 0);
        }
        // En la siguiente ruta será almacenado el vídeo que vamos a grabar
        ContextWrapper cw = new ContextWrapper(this);
        videoFile = new File(cw.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "video.mp4");

        Log.d("buscar", "ruta -> "+videoFile.getAbsolutePath());

        //Inicializaremos la pantalla para que la ponga en horizontal
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //inicializaremos el MediaRecorder para poder hacer la grabación
        recorder = new MediaRecorder();
        initRecorder();
        setContentView(R.layout.activity_main);

         btnReproducir = (Button)findViewById(R.id.btnReproducir);

        //Usaremos el SurfaceView/SurfaceHolder para poder visualizar lo que se está grabando.
        SurfaceView cameraView = (SurfaceView) findViewById(R.id.miSuperficie);
        holder = cameraView.getHolder();
        holder.addCallback(this);

        //Podremos hacer clic en la superficie para comenzar a grabar o parar de grabar
        cameraView.setClickable(true);
        cameraView.setOnClickListener(this);

        btnReproducir.setOnClickListener(v -> {

            Intent actividadReproducir = new Intent(getApplicationContext(), ActividadReproducir.class);

            File archivoVideo = new File(cw.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "video.mp4");

            String rutaVideo = archivoVideo.getAbsolutePath();

            actividadReproducir.putExtra("rutaVideo", rutaVideo);

           startActivity(actividadReproducir);
        });
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        prepareRecorder();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        if (recording) {
            recorder.stop();
            recording = false;
        }
        recorder.release();
        finish();
    }

    @Override
    public void onClick(View view) {
        if (recording) {
            recorder.stop();
            recording = false;
        } else {
            recording = true;
            recorder.start();
        }
    }
    private void initRecorder() {
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

        CamcorderProfile cpHigh = CamcorderProfile
                .get(CamcorderProfile.QUALITY_HIGH);
        recorder.setProfile(cpHigh);
        recorder.setOutputFile(videoFile.getAbsolutePath());
    }
    private void prepareRecorder() {
        //conseguimos visualizar en directo lo que estamos grabando
        recorder.setPreviewDisplay(holder.getSurface());

        try {
            recorder.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
            finish();
        } catch (IOException e) {
            e.printStackTrace();
            finish();
        }
    }
}