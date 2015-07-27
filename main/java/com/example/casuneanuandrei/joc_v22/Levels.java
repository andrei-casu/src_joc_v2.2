package com.example.casuneanuandrei.joc_v22;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;

public class Levels extends Screen {
    private Graphics graphics;
    private Image background;
    private Bitmap framebuffer;
    private Context context;

    private Buton butonLevel1;

    public Levels(Bitmap framebuffer, Context context){
        super();

        this.framebuffer = framebuffer;
        this.context = context;

        graphics = new Graphics(framebuffer, context, true);
        background = graphics.openImage("meniu.png");

        butonLevel1 = new Buton(framebuffer, "buton_italia.png", Ecran.w/2 - Scaler.scale(700)/2, Scaler.scale(150), context, true);
    }

    @Override
    protected void onDraw() {
        graphics.resetCanvas();
        graphics.drawImage(background, (Scaler.scale(1920) - Ecran.w)/2, 0, 0, 0, background.getW(), background.getH());
        butonLevel1.paint();
    }

    @Override
    protected int onTouch(MotionEvent event) {
        int x, y;
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN){
            x = (int) event.getX();
            y = (int) event.getY();

            if (butonLevel1.push(x, y))
                return EventTypes.SCHIMBA_LEVEL1;
        }
        return EventTypes.NONE_OF_YOUR_B;
    }

    @Override
    protected void onUpdate() {

    }
}
