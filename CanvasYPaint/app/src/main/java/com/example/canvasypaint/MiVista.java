package com.example.canvasypaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MiVista extends View {

    Paint pintar = null;
    public MiVista(Context context) {
        super(context);

        pintar = new Paint();
    }

    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
       /*
        int x = getWidth();
        int y = getHeight();
        int radius;
        radius = 300;
        pintar.setStyle(Paint.Style.FILL);
        pintar.setColor(Color.WHITE);
        canvas.drawPaint(pintar);
        // Use Color.parseColor to define HTML colors
        pintar.setColor(Color.parseColor("#CD5C5C"));
        canvas.drawCircle(x / 2, y / 2, radius, pintar);
        */
        pintar.setColor(Color.RED);
        pintar.setStrokeWidth(3);
        canvas.drawRect(30, 100, 300, 300, pintar);
        pintar.setStrokeWidth(0);
        pintar.setColor(Color.CYAN);
        canvas.drawRect(33, 100, 30, 60, pintar );
        pintar.setColor(Color.BLUE);
        canvas.drawRect(33, 33, 77, 60, pintar );

    }
}
