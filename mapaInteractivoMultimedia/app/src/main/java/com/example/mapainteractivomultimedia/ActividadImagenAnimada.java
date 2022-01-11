package com.example.mapainteractivomultimedia;

import android.animation.ObjectAnimator;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * The type Actividad imagen animada.
 */
public class ActividadImagenAnimada extends AppCompatActivity {
    private Intent imagenImportada;
    private ImageView imageUrlCargada;
    private Button btnAnimarImagen;
    private Button btnFondoPantalla;
    private Button btnGrabarAudio;
    private int anchura;
    private int altura;
    private ArrayList<String> listaSonidoUrl;
    private ArrayList<String> listaSonidoDescripcion;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagen_animada);

        imageUrlCargada = (ImageView)findViewById(R.id.imagenCargada);
        btnAnimarImagen = (Button)findViewById(R.id.btnAnimarImagen);
        btnFondoPantalla = (Button)findViewById(R.id.btnFondoPantalla);
        btnGrabarAudio = (Button)findViewById(R.id.btnGrabarAudio);

        imagenImportada = getIntent();

        Glide.with(getApplicationContext()).load(imagenImportada.getStringExtra("urlImagen")).into(imageUrlCargada);

        listaSonidoUrl = new ArrayList<>(imagenImportada.getStringArrayListExtra("listaUrlImportada"));
        listaSonidoDescripcion = new ArrayList<>(imagenImportada.getStringArrayListExtra("listaDescripcionImportada"));

        imageUrlCargada.getLayoutParams().width = 350;

        anchura = imageUrlCargada.getLayoutParams().width;
        altura = imageUrlCargada.getLayoutParams().height;

        btnAnimarImagen.setOnClickListener(v -> {

            int animacionAleatorio = dameNumeroAleatorio(1,5);
            Log.d("aleatorio", "numero => "+animacionAleatorio);

            switch (animacionAleatorio){
                case 1 :
                    ObjectAnimator animator = ObjectAnimator.ofFloat(imageUrlCargada, "translationY", 500f); // TransalationY hacia abajo
                    animator.setDuration(3000);
                    animator.setStartDelay(2000);
                    animator.start();
                    break;
                case 2 :
                    //Ahora voy a probar cambiar los parametros
                    ObjectAnimator animatorCambiarParametros = ObjectAnimator.ofFloat(imageUrlCargada, "translationX", 250f); //TranslationX hacia la derecha
                    animatorCambiarParametros.setDuration(2000);
                    animatorCambiarParametros.setStartDelay(1000);
                    animatorCambiarParametros.start();
                    break;
                case 3 :
                    ObjectAnimator animatorRedimension = ObjectAnimator.ofFloat(imageUrlCargada, "scaleX", 2f);
                    animatorRedimension.setDuration(3000);
                    animatorRedimension.setStartDelay(2000);
                    animatorRedimension.start();
                    break;
                case 4 :
                    ObjectAnimator animatorHaciaArriba = ObjectAnimator.ofFloat(imageUrlCargada, "translationY", -500f); // TransalationY hacia abajo
                    animatorHaciaArriba.setDuration(3000);
                    animatorHaciaArriba.setStartDelay(2000);
                    animatorHaciaArriba.start();

                    ObjectAnimator animatorHaciaAbajo = ObjectAnimator.ofFloat(imageUrlCargada, "translationX", 500f); // TransalationY hacia abajo
                    animatorHaciaAbajo.setDuration(3000);
                    animatorHaciaAbajo.setStartDelay(2000);
                    animatorHaciaAbajo.start();
                    break;
            }
        });
        btnFondoPantalla.setOnClickListener(v -> {

            WallpaperManager administradorFondo = WallpaperManager.getInstance(this);
            Bitmap mapaBit = ((BitmapDrawable)imageUrlCargada.getDrawable()).getBitmap();

            try {
                administradorFondo.setBitmap(mapaBit);
                Toast.makeText(getApplicationContext(), "Se ha establecido como fondo de pantalla",Toast.LENGTH_SHORT).show();
            }catch(Exception error){
                Toast.makeText(getApplicationContext(), "Error al cargar la imagen", Toast.LENGTH_SHORT).show();
            }
        });
        btnGrabarAudio.setOnClickListener(v -> {
            Intent actividadListaAudioImagen = new Intent(getApplicationContext(), ActividadListaAudio.class);
            actividadListaAudioImagen.putStringArrayListExtra("listaImagenesUrl", listaSonidoUrl);
            actividadListaAudioImagen.putStringArrayListExtra("listaImagenesDescripcion", listaSonidoDescripcion);
            startActivity(actividadListaAudioImagen);
        });
    }

    /**
     * Dame numero aleatorio int.
     *
     * @param minimo the minimo
     * @param maximo the maximo
     * @return the int
     */
    public int dameNumeroAleatorio(int minimo, int maximo){
        int aleatorio = ((int)(Math.random() * (maximo - minimo))) + minimo;
        return aleatorio;
    }
}
