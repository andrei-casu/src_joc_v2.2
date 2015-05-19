package com.example.casuneanuandrei.joc_v22;

import android.view.MotionEvent;

public abstract class Turn {
    private Graphics graphics;

    public Turn(){

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
