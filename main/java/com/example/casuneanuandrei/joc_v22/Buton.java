package com.example.casuneanuandrei.joc_v22;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by casuneanuandrei on 5/10/15.
 */
public class Buton {
    Graphics graphics;
    Image image;

    private int w, h;
    private int x, y;
    private int xc, yc;

    public Buton(Bitmap framebuffer, String nume, int x, int y, Context context)
    {
        graphics = new Graphics(framebuffer, context);
        image = graphics.openImage(nume);

        w = image.getW();
        h = image.getH();

        this.x = Scaler.scale(x);
        this.y = Scaler.scale(y);

        xc = this.x + w/2;
        yc = this.y + h/2;
    }

    public void paint(){
        graphics.drawImage(image, x, y, 0, 0, w, h);
    }

    public boolean push(int x, int y) {
        if (xc-w/2 <= x && x <= xc+w/2 && yc-h/2 <= y && y <= yc+h/2)
            return true;
        return false;
    }

}
