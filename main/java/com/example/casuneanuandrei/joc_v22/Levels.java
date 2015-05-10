package com.example.casuneanuandrei.joc_v22;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;

/**
 * Created by casuneanuandrei on 5/10/15.
 */
public class Levels extends Screen {
    private Graphics graphics;
    private Image background;
    private Bitmap framebuffer;
    private Context context;
    private Buton level1;

    public Levels(Bitmap framebuffer, Context context){
        super();

        this.framebuffer = framebuffer;
        this.context = context;

        level1 = new Buton(framebuffer, "poze/butonStart.png", 200, 200, context);

        graphics = new Graphics(framebuffer, context);
        background = graphics.openImage("poze/backgroundLevels.png");

    }

    @Override
    public void update() {

    }

    @Override
    protected void onDraw() {
        graphics.resetCanvas();
        graphics.drawImage(background, 0, 0, 0, 0, background.getW(), background.getH());
    }

    @Override
    protected int onTouch(MotionEvent event) {
        return EventTypes.NONE_OF_YOUR_B;
    }
}
