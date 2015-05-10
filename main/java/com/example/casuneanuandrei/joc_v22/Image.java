package com.example.casuneanuandrei.joc_v22;

import android.graphics.Bitmap;

/**
 * Created by casuneanuandrei on 5/9/15.
 */
public class Image {
    private Bitmap bitmap;
    int w, h;

    public Image(Bitmap bitmap){
        this.bitmap = bitmap;
        w = bitmap.getWidth();
        h = bitmap.getHeight();
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getH() {
        return h;
    }

    public int getW() {
        return w;
    }
}
