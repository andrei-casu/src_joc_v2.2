package com.example.casuneanuandrei.joc_v22;

import android.content.Context;
import android.graphics.Bitmap;

public class Buton {
    private Graphics graphics;
    private Image image;

    private int w, h;
    private int x, y;
    private int xc, yc;
    private boolean fixed;
    private boolean scalable;
    private boolean visible;
    private boolean active;

    public Buton(Bitmap framebuffer, String nume, int x, int y, Context context, boolean fixed)
    {
        graphics = new Graphics(framebuffer, context, fixed);
        visible = true;
        active = true;
        image = graphics.openImage(nume);

        w = image.getW();
        h = image.getH();

        this.x = x;
        this.y = y;

        xc = this.x + w/2;
        yc = this.y + h/2;

        this.fixed = fixed;
        this.scalable = scalable;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public boolean isActive() {
        return active;
    }

    public Buton(Bitmap framebuffer, int x, int y, int w, int h, Context context, boolean fixed)
    {
        graphics = new Graphics(framebuffer, context, fixed);
        visible = true;
        active = true;

        this.w = w;
        this.h = h;

        this.x = x;
        this.y = y;

        xc = this.x + w/2;
        yc = this.y + h/2;

        this.fixed = fixed;
        this.scalable = scalable;
    }


    public void setActive(boolean active) {
        this.active = active;
    }

    protected void onDraw() {
        if (visible == false)
            return;

        graphics.drawImage(image, x, y, 0, 0, w, h);
    }

    public void paint(){
        onDraw();
    }

    public boolean push(int x, int y) {
        if (active == false)
            return false;


        if (!fixed) {
            int xbnou, ybnou, wbnou, hbnou;

            xbnou = (int) ((int) (this.x * Offset.scale) + Offset.valuex);
            ybnou = (int) ((int) (this.y * Offset.scale) + Offset.valuey);

            wbnou = (int) (w * Offset.scale);
            hbnou = (int) (h * Offset.scale);

            if (x >= xbnou && x <= xbnou + wbnou && y >= ybnou && y <= ybnou + hbnou)
                return true;
        }
        else{
            if (x >= this.x && x <= this.x + w && y >= this.y && y <= this.y + h)
                return true;
        }

        return false;
    }
}
