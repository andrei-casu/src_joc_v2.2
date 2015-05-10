package com.example.casuneanuandrei.joc_v22;

import android.graphics.Bitmap;

public class Image {
    private Bitmap bitmap;
    private int w, h;

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
