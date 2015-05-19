package com.example.casuneanuandrei.joc_v22;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public class Start extends Screen{
    private Graphics graphics;
    private Image background;
    private Bitmap framebuffer;
    private Context context;

    private Buton butonLevels;
    private Buton butonShop;
    private Buton butonOptions;
    private Buton butonHelp;

    public Start(Bitmap framebuffer, Context context){
        super();
        graphics = new Graphics(framebuffer, context);
        this.framebuffer = framebuffer;
        background = graphics.openImage("poze/background.jpg");
        this.context = context;

        butonLevels = new Buton(framebuffer, "poze/meniu_buton.png", 0, 0, context);
        butonShop = new Buton(framebuffer, "poze/meniu_buton.png", 0, 30, context);
        butonOptions = new Buton(framebuffer, "poze/meniu_buton.png", 0, 60, context);
        butonHelp = new Buton(framebuffer, "poze/meniu_buton.png", 0, 90, context);
    }

    @Override
    protected void onDraw() {
        graphics.resetCanvas();
        graphics.drawImage(background, 0, 0, 0, 0, background.getW(), background.getH());
        butonLevels.paint();
        butonShop.paint();
        butonOptions.paint();
        butonHelp.paint();
    }

    @Override
    protected int onTouch(MotionEvent event) {
        int x, y;
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN){
            x = (int) event.getX();
            y = (int) event.getY();

            if (butonLevels.push(x, y))
                return EventTypes.SCHIMBA_LEVELS;
            if (butonShop.push(x, y))
                return EventTypes.SCHIMBA_SHOP;
            if (butonOptions.push(x, y))
                return EventTypes.SCHIMBA_OPTIONS;
            if (butonHelp.push(x, y))
                return EventTypes.SCHIMBA_HELP;
        }
        return EventTypes.NONE_OF_YOUR_B;
    }

    @Override
    protected void onUpdate() {

    }
}
