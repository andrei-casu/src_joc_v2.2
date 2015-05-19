package com.example.casuneanuandrei.joc_v22;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;

public class TurnArcasi extends Turn{
    private Graphics graphics;
    private Bitmap framebuffer;
    private Context context;

    TurnArcasi(Bitmap framebuffer, Context context){
        this.framebuffer = framebuffer;
        this.context = context;

        graphics = new Graphics(framebuffer, context);
    }

    @Override
    protected void onDraw() {

    }

    @Override
    protected int onTouch(MotionEvent event) {
        return 0;
    }

    @Override
    protected void onUpdate() {

    }
}
