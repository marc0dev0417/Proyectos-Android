package com.example.animaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView miImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miImageView = (ImageView) findViewById(R.id.miImageView);

      /*
        //Lo que viene en la documentacion
        ObjectAnimator animator = ObjectAnimator.ofFloat(miImageView, "translationY", 500f); // TransalationY hacia abajo
        animator.setDuration(3000);
        animator.setStartDelay(2000);
        animator.start();
       */

      /*
        //Ahora voy a probar cambiar los parametros
        ObjectAnimator animatorCambiarParametros = ObjectAnimator.ofFloat(miImageView, "translationX", 250f); //TranslationX hacia la derecha
        animatorCambiarParametros.setDuration(2000);
        animatorCambiarParametros.setStartDelay(1000);
        animatorCambiarParametros.start();
     */



        /*
        //Transparencia del componente
        ObjectAnimator animatorTransparencia = ObjectAnimator.ofFloat(miImageView, "alpha", 1f, 0f);
        animatorTransparencia.setDuration(3000);
        animatorTransparencia.setStartDelay(2000);
        animatorTransparencia.start();
        */

        /*
        ObjectAnimator animatorRedimension = ObjectAnimator.ofFloat(miImageView, "scaleX", 2f);
        animatorRedimension.setDuration(3000);
        animatorRedimension.setStartDelay(2000);
        animatorRedimension.start();
         */

        //https://developer.android.com/guide/topics/graphics/prop-animation#java

        ObjectAnimator animatorHaciaArriba = ObjectAnimator.ofFloat(miImageView, "translationY", -500f); // TransalationY hacia abajo
        animatorHaciaArriba.setDuration(3000);
        animatorHaciaArriba.setStartDelay(2000);
        animatorHaciaArriba.start();

        ObjectAnimator animatorHaciaAbajo = ObjectAnimator.ofFloat(miImageView, "translationX", 500f); // TransalationY hacia abajo
        animatorHaciaAbajo.setDuration(3000);
        animatorHaciaAbajo.setStartDelay(2000);
        animatorHaciaAbajo.start();
    }
}