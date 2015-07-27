package com.example.casuneanuandrei.joc_v22;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;

public class HealthBar {
    private Bitmap framebuffer;
    private Context context;
    private Graphics graphics;
    private Image image;
    private int viataTotal, viata;
    private Rect src;
    private int x, y, w, h;
    private Inamic inamic;

    public HealthBar(Bitmap framebuffer, Context context, int viata, Inamic inamic){
        viataTotal = this.viata = viata;

        graphics = new Graphics(framebuffer, context, false);

        image = graphics.openImage("healthbar.png");
        w = image.getW();
        h = image.getH();

        this.inamic = inamic;

        src = new Rect(0, 0, w, h);
    }

    public void paint(){onDraw();}

    protected void onDraw(){
        double rap = ((double)viata)/viataTotal;
        graphics.drawImage(image, x, y, 0, 0, (int) (w * rap), h);
    }

    protected void onUpdate(){
        viata = inamic.getViata();
        x = inamic.getX();
        y = inamic.getY();
    }

    public void update(){onUpdate();}

    public void setViata(int viata) {
        this.viata = viata;
    }

    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }
}
