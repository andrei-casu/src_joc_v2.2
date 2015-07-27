package com.example.casuneanuandrei.joc_v22;

import android.content.Context;
import android.graphics.Bitmap;

public class Animatie {
    private Bitmap framebuffer;
    private Context context;
    private Graphics graphics;
    private Image image;
    private int indWMax, indHMax;
    private int ind;
    private int currPos;
    private int h, w;//unui dreptunghi din poza
    private int speedAnim;//viteza animatiei este de cate ori se schimba poza intr-o secunda
    private int indMax;//indicele maxim la care ajunge ind
    private boolean running, looping;
    private boolean visible;
    private int x = 0, y = 0;
    private int indWCurr = 0, indHcurr = 0;
    private boolean visibleAfter;


    Animatie(Bitmap framebuffer, Context context, String nume, int indW, int indH, boolean fixed){
        this.framebuffer = framebuffer;
        this.context = context;

        graphics = new Graphics(framebuffer, context, fixed);

        this.indWMax = indW;
        this.indHMax = indH;

        image = graphics.openImage(nume);

        w = image.getW() / indW;
        h = image.getH() / indH;
    }

    public void setVisibleAfter(boolean visibleAfter) {
        this.visibleAfter = visibleAfter;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setLooping(boolean looping) {
        this.looping = looping;
    }

    public void setSpeedAnim(int speedAnim) {
        this.speedAnim = speedAnim;
        indMax = 60 / speedAnim;
    }

    public void start(){
        running = true;
        visible = true;
        ind = 0;
        indWCurr = 0;
        indHcurr = 0;
    }

    public void pause(){
        running = false;
    }

    public void resume(){
        running = true;
    }

    public void stop(){
        running = false;
        ind = 0;
        indWCurr = 0;
        indHcurr = 0;
    }

    public boolean isRunning(){
        return running;
    }
    public boolean isVisible() {return visible;}

    protected void onUpdate(){
        if (running == false)
            return;

        ++ind;
        if (ind == indMax){
            ind = 0;
            //schimb poza
            ++indWCurr;
            if (indWCurr >= indWMax){
                ++indHcurr;
                indWCurr = 0;
                if (indHcurr >= indHMax){
                    //ori o iau de la capat, ori ma hopresc
                    if (looping == true){
                        indWCurr = indHcurr = 0;
                    }
                    else {
                        running = false;
                        visible = visibleAfter;
                        indWCurr = indWMax - 1;
                        indHcurr = indHMax - 1;
                    }
                }
            }
        }
    }

    protected void onDraw(){//este apelata de 60 de ori pe secunda
        if (visible == false)
            return;

        //desenez la indWcurr*w si indHcurr*h
        graphics.drawImage(image, x, y, indWCurr * w, indHcurr * h, w, h);
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

    public void paint(){onDraw();}

    public void update(){onUpdate();}
}
