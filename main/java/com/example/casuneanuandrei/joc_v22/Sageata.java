package com.example.casuneanuandrei.joc_v22;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;

public abstract class Sageata {

    protected Bitmap framebuffer;
    protected Context context;

    protected Inamic inamic;
    protected String nume;

    public Sageata(){

    }

    public void paint(){
        onDraw();
    }

    public abstract int getAngle();

    public int click(MotionEvent event){
        return onTouch(event);
    }
    public void update(){onUpdate();}

    protected abstract void onDraw();
    protected abstract int onTouch(MotionEvent event);
    protected abstract void onUpdate();
}
