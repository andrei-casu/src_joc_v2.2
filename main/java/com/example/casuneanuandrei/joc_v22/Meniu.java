package com.example.casuneanuandrei.joc_v22;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by casuneanuandrei on 5/9/15.
 */
public class Meniu extends Screen{
    private Graphics graphics;
    private Image background;
    private Bitmap framebuffer;
    private Buton butonStart;
    private Buton butonCredits;
    private Context context;

    public Meniu(Bitmap framebuffer, Context context){
        super();
        graphics = new Graphics(framebuffer, context);
        this.framebuffer = framebuffer;
        background = graphics.openImage("poze/background.jpg");
        this.context = context;

        butonStart = new Buton(framebuffer, "poze/meniu_buton.png", 0, 0, context);
        butonCredits = new Buton(framebuffer, "poze/meniu_buton.png", 0, 30, context);
    }

    public void update(){
        //graphics.drawImage(background, 0, 0, 0, 0, background.getW(), background.getH());
    }

    @Override
    protected void onDraw() {
        graphics.resetCanvas();
        graphics.drawImage(background, 0, 0, 0, 0, background.getW(), background.getH());
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
}
