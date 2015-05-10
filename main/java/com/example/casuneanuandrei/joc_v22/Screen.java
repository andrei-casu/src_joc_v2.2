package com.example.casuneanuandrei.joc_v22;

import android.view.MotionEvent;

/**
 * Created by casuneanuandrei on 5/9/15.
 */
public abstract class Screen{
    private Graphics graphics;

    public Screen(){
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public abstract void update();

    public void paint(){
        onDraw();
    }
    public int click(MotionEvent event){
        return onTouch(event);
    }

    protected abstract void onDraw();
    protected abstract int onTouch(MotionEvent event);

}
