package com.example.mapainteractivomultimedia;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * The type Mi plantilla.
 */
public class MiPlantilla extends View {

    private int aleatorio;

    /**
     * Instantiates a new Mi plantilla.
     *
     * @param context the context
     */
    public MiPlantilla(Context context) {
        super(context);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        aleatorio = ((int)(Math.random()*(6 - 1))) + 1;

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);//color blanco
        canvas.drawPaint(paint);//pinta fondo

        switch (aleatorio){
            case 1 :  //Texto
                paint.setColor(Color.RED);
                paint.setTextSize(42);//tamaño de texto
                //texto , X, Y, Paint
                canvas.drawText("Buenos días Marco Alonso Benegas!!", 10, 56, paint);//pinta texto
                break;
            case 2 :  //Linea
                paint.setAntiAlias(true);//para evitar efecto sierra
                paint.setStyle(Paint.Style.STROKE);//estilo
                paint.setStrokeWidth(10);//grosor de linea
                paint.setColor(Color.BLUE);
                //x1 , y1, x2, y2
                canvas.drawLine(0, 100, canvas.getWidth(), 100, paint);
                break;
            case 3 : //Rectangulo
                paint.setAntiAlias(true);
                paint.setColor(Color.RED);
                paint.setStyle(Paint.Style.FILL);//relleno
                // x, y, ancho , alto
                canvas.drawRect(40, 140, canvas.getWidth() - 40, 280, paint);
                break;
            case 4 : //Circulo
                paint.setAntiAlias(true);
                paint.setColor(Color.GREEN);
                paint.setStyle(Paint.Style.STROKE);//borde
                paint.setStrokeWidth(3.6f);
                // centro X, centro Y, radio
                canvas.drawCircle(canvas.getWidth() / 2 , 400, 100, paint);
                break;
            case 5 : //poligono
                Path path = new Path();
                paint.setColor(Color.MAGENTA);
                paint.setStyle(Paint.Style.FILL);
                path.moveTo(canvas.getWidth() / 2, 550);
                path.lineTo(canvas.getWidth() / 2 + 100, 700);
                path.lineTo(canvas.getWidth() / 2 - 100, 700);
                path.close();
                canvas.drawPath(path, paint);
                break;
            case 6 : //imagen
                Bitmap bmp = BitmapFactory.decodeResource(getResources(),
                        R.drawable.cat);//Icono de android
                //imagen, X, Y
                canvas.drawBitmap(bmp,canvas.getWidth() / 2,760,paint);
                break;
        }
    }
}
