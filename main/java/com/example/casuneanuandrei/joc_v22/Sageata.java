package com.example.casuneanuandrei.joc_v22;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;

public abstract class Sageata {

    protected Bitmap framebuffer;
    protected Context context;

    protected int xstart, ystart;
    protected Inamic inamic;

    public Sageata(){

    }

    public void paint(){
        onDraw();
    }
    public int click(MotionEvent event){
        return onTouch(event);
    }
    public void update(){onUpdate();}

    protected abstract void onDraw();
    protected abstract int onTouch(MotionEvent event);
    protected abstract void onUpdate();
}
