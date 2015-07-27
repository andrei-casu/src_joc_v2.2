package com.example.casuneanuandrei.joc_v22;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;

public class Ghiulea extends Sageata {
    private Image image;
    private Miscare miscare;
    private Graphics graphics;
    private Inamic inamic;

    private int ind=10;

    private int x, y;
    private int w, h;
    private int xstop, ystop;
    private int xurm, yurm;
    private int currAngle = 0;
    private boolean visible;
    private int damage;

    private int lgx, lgy;

    public Ghiulea(Bitmap framebuffer, Context context, int x, int y, Inamic inamic){
        this.framebuffer = framebuffer;
        this.context = context;
        this.nume = nume;

        this.x = x;
        this.y = y;

        this.inamic = inamic;

        xstop = inamic.getX() + inamic.getW()/2 - w/2;
        ystop = inamic.getY() + inamic.getH()/2 - h/2;

        graphics = new Graphics(framebuffer, context, false);
        image = graphics.openImage("ghiulea.png");
        visible = true;
        w = image.getW();
        h = image.getH();

        lgx = (xstop-x)/ind;
        lgy = (ystop-y)/ind;

        xurm = x+lgx;
        yurm = y+lgy;

        miscare = new Miscare(x, y, xurm, yurm, Scaler.scale(400));

        int aux = (int) (Math.atan2(yurm - y, xurm - x) * (180/Math.PI));
        image = graphics.rotateImage(image, aux - currAngle);
        currAngle = aux;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public int getAngle() {
        return currAngle;
    }

    @Override
    protected void onDraw(){
        if (visible)
            graphics.drawImage(image, x, y, 0, 0, w, h);
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }


    @Override
    protected int onTouch(MotionEvent event){
        return EventTypes.NONE_OF_YOUR_B;
    }

    @Override
    protected void onUpdate(){


        x = (int) miscare.getx();
        y = (int) miscare.gety();

        miscare.update();

        if (!miscare.isRunning()){
            ind--;
            if (ind > 0){
                xstop = inamic.getX() + inamic.getW()/2 - w/2;
                ystop = inamic.getY() + inamic.getH()/2 - h/2;

                lgx = (xstop-x)/ind;
                lgy = (ystop-y)/ind;

                xurm = x+lgx;
                yurm = y+lgy;

                miscare = new Miscare(x, y, xurm, yurm, Scaler.scale(400));
                //unghiul
                int aux = (int) (Math.atan2(yurm - y, xurm - x) * (180/Math.PI));
                image = graphics.rotateImage(image, aux - currAngle);
                currAngle = aux;
                //
            }
            else {
                visible = false;
                //AM AJUNS
                inamic.hitYa(damage);
            }
        }
    }
}
