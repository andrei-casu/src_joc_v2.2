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
        graphics = new Graphics(framebuffer, context, true);
        this.framebuffer = framebuffer;
        background = graphics.openImage("meniu.png");
        this.context = context;

        butonLevels = new Buton(framebuffer, "buton_levels.png", Ecran.w/2 - Scaler.scale(445)/2, Scaler.scale(50), context, true);
        butonShop = new Buton(framebuffer, "buton_shop.png", Ecran.w/2 - Scaler.scale(445)/2, Scaler.scale(250), context, true);
        butonOptions = new Buton(framebuffer, "buton_options.png", Ecran.w/2 - Scaler.scale(445)/2, Scaler.scale(450), context, true);
        butonHelp = new Buton(framebuffer, "buton_help.png", Ecran.w/2 - Scaler.scale(445)/2, Scaler.scale(650), context, true);
    }

    @Override
    protected void onDraw() {
        graphics.resetCanvas();
        graphics.drawImage(background, -(Scaler.scale(1920) - Ecran.w)/2, 0, 0, 0, background.getW(), background.getH());
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
