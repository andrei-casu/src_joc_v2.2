package com.example.casuneanuandrei.joc_v22;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;

public class Shop extends Screen{
    private Graphics graphics;
    private Image background;
    private Bitmap framebuffer;
    private Context context;

    public Shop(Bitmap framebuffer, Context context){
        super();
        this.framebuffer = framebuffer;
        this.context = context;

        graphics = new Graphics(framebuffer, context, true);
        background = graphics.openImage("poze/background.jpg");
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

    @Override
    protected void onUpdate() {

    }
}
