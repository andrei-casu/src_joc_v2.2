package com.example.casuneanuandrei.joc_v22;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;

public class SuportTurn {

    private Turn turn;
    private TurnArcasi turn_arcasi;
    //private TurnMagicieni turn_magicieni;
    //private TurnLuptatori turn_luptatori;

    private Context context;
    private Bitmap framebuffer;

    private boolean free;
    private int x, y;
    private int w, h;

    private Animatie animatie;

    public SuportTurn(Context context, Bitmap framebuffer, int x, int y, int width, int height){
        this.free=true;

        this.context = context;
        this.framebuffer = framebuffer;

        this.x=x;
        this.y=y;
        this.w=width;
        this.h=height;

        animatie = new Animatie(framebuffer, context, "level1/animatie_turn.jpg", 10, 10);
        //animatie.SetLooping(false);
    }

    public boolean push(int x, int y) {
        if (this.x <= x && x <= this.x+w && this.y <= y && y <= this.y+h)
            return true;
        return false;
    }

    public void animatie(){

    }

    public void paint(){
        onDraw();
    }
    public void update() {
        onUpdate();
    }
    public int click(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN){
            if (this.push((int)event.getX(), (int)event.getY()))
                animatie.start();
        }
        return onTouch(event);
    }

    protected void onDraw(){

    }

    protected int onTouch(MotionEvent event){
        return EventTypes.NONE_OF_YOUR_B;
    }

    protected void onUpdate(){

    }

}
