package com.example.casuneanuandrei.joc_v22;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.io.IOException;
import java.io.InputStream;

public class Graphics {
    Canvas canvas;
    Paint paint;
    Rect srcRect, dstRect;
    Context context;
    Bitmap framebuffer;
    boolean scalable = true;
    boolean fixed = false;

    public Graphics(Bitmap framebuffer, Context context){
        this.canvas = new Canvas(framebuffer);
        paint = new Paint();
        srcRect = new Rect();
        dstRect = new Rect();
        this.context = context;
        this.framebuffer = framebuffer;
        canvas.setBitmap(this.framebuffer);
    }

    public void setScalable(boolean scalable) {
        this.scalable = scalable;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public void resetCanvas(){
        canvas.drawColor(Color.BLACK);
    }

    public void drawImage(Image image, int x, int y, int xs, int ys, int ws, int hs){
        srcRect.set(xs, ys, xs+ws, ys+hs);

        int xaux, yaux;

        if (fixed){
            xaux = x;
            yaux = y;
        }
        else {
            xaux = (int) (x + Offset.valuex);
            yaux = (int) (y + Offset.valuey);
        }

        if (scalable) {
            xaux *= Offset.scale;
            yaux *= Offset.scale;
            dstRect.set((int)xaux, (int)yaux, (int)(xaux+ws*Offset.scale), (int)(yaux+hs*Offset.scale));
        }
        else {
            dstRect.set((int)xaux, (int)yaux, (int)(xaux), (int)(yaux));
        }

        canvas.drawBitmap(image.getBitmap(), srcRect, dstRect, null);
    }

    public Image openImage(String nume){
        Image imagine = null;
        try {
            Bitmap aux = BitmapFactory.decodeStream(context.getAssets().open(nume));
            int w = Scaler.scale(aux.getWidth());
            int h = Scaler.scale(aux.getHeight());
            Bitmap fin = Bitmap.createScaledBitmap(aux, w, h, false);
            imagine = new Image(fin);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagine;
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
