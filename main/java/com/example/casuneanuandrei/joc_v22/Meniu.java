package com.example.casuneanuandrei.joc_v22;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public class Meniu extends Screen{
    private Graphics graphics;
    private Image background;
    private Bitmap framebuffer;
    private Context context;

    private Buton butonStart;
    private Buton butonCredits;

    public Meniu(Bitmap framebuffer, Context context){
        super();
        graphics = new Graphics(framebuffer, context, true);
        this.framebuffer = framebuffer;
        background = graphics.openImage("meniu.png");
        this.context = context;

        butonStart = new Buton(framebuffer, "start.png", Ecran.w/2 - Scaler.scale(595)/2, Scaler.scale(120), context, true);
        butonCredits = new Buton(framebuffer, "credits.png", Ecran.w/2 - Scaler.scale(445)/2, Scaler.scale(420), context, true);
    }

    @Override
    protected void onDraw() {
        graphics.resetCanvas();
        graphics.drawImage(background, -(Scaler.scale(1920) - Ecran.w)/2, 0, 0, 0, background.getW(), background.getH());
        butonStart.paint();
        butonCredits.paint();
    }

    @Override
    protected int onTouch(MotionEvent event) {
        int x, y;
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN){
            x = (int) event.getX();
            y = (int) event.getY();
            if (butonStart.push(x, y))
                return EventTypes.SCHIMBA_START;
            if (butonCredits.push(x, y))
                return EventTypes.SCHIMBA_CREDITS;
        }
        return EventTypes.NONE_OF_YOUR_B;
    }

    @Override
    protected void onUpdate() {

    }
}
