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

/**
 * Created by casuneanuandrei on 5/9/15.
 */
public class Graphics {
    Canvas canvas;
    Paint paint;
    Rect srcRect, dstRect;
    Context context;
    Bitmap framebuffer;

    public Graphics(Bitmap framebuffer, Context context){
        this.canvas = new Canvas(framebuffer);
        paint = new Paint();
        srcRect = new Rect();
        dstRect = new Rect();
        this.context = context;
        this.framebuffer = framebuffer;
        canvas.setBitmap(this.framebuffer);
    }

    public void resetCanvas(){
        canvas.drawColor(Color.BLACK);
    }

    public void drawImage(Image image, int x, int y, int xs, int ys, int ws, int hs){
        srcRect.set(xs, ys, xs+ws, ys+hs);

        dstRect.set(x, y, x+ws, y+hs);

        canvas.drawBitmap(image.getBitmap(), srcRect, dstRect, null);
    }

    public Image openImage(String nume){
        Image imagine = null;
        try {
            imagine = new Image(BitmapFactory.decodeStream(context.getAssets().open(nume)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagine;
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
