package com.example.casuneanuandrei.joc_v22;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;

public class Sulitar implements Collidable {
    private Bitmap framebuffer;
    private Context context;
    private int x, y;
    private int w, h;
    private int radius;
    private int xbaza, ybaza;
    private double lastAttack;
    private double attackSpeed;//cate atacuri pe secunda
    private int damage;

    private Sulita[] sulite; //?? nu stiu de cate ai nevoie

    private Animatie staDreapta, staStanga;
    private Animatie atacDreapta;
    private Animatie atacStanga;
    private Animatie animatie;
    private int lastDir = 0;//0 stanga, 1 dreapta

    public Sulitar(Bitmap framebuffer, Context context, int xbaza, int ybaza){
        this.framebuffer = framebuffer;
        this.context = context;
        this.xbaza = xbaza;
        this.ybaza = ybaza;

        damage = 70;

        staDreapta = new Animatie(framebuffer, context, "sulitar_dreapta_sta.png", 1, 1, false);
        staDreapta.setSpeedAnim(2);
        staDreapta.setLooping(true);
        //staDreapta.setX(xbaza - staDreapta.getW() / 2); //x
        //staDreapta.setY(ybaza - staDreapta.getH()); //y

        staStanga = new Animatie(framebuffer, context, "sulitar_stanga_sta.png", 1, 1, false);
        staStanga.setSpeedAnim(2);
        staStanga.setLooping(true);

        atacDreapta = new Animatie(framebuffer, context, "sulitar_dreapta_atac.png", 4, 3, false);
        atacDreapta.setSpeedAnim(7);
        atacDreapta.setLooping(false);


        atacStanga = new Animatie(framebuffer, context, "sulitar_stanga_atac.png", 4, 3, false);
        atacStanga.setSpeedAnim(7);
        atacStanga.setLooping(false);


        animatie = staDreapta;
        animatie.setX(xbaza - staDreapta.getW() / 2);
        animatie.setY(ybaza - staDreapta.getH()); //y
        animatie.start();

        x = xbaza - staDreapta.getW() / 2;
        y = ybaza - staDreapta.getH();

        w = staDreapta.getW();
        h = staDreapta.getH();

        sulite = new Sulita[30];
        attackSpeed = 1;


        CollisionDetector.addObject(this);
    }

    protected boolean canAttack(){

        if (animatie != staStanga && animatie != staDreapta){
            if (animatie.isVisible())
                return false;
        }

        double now = System.currentTimeMillis();
        if (now - lastAttack >= 1000/attackSpeed)
            return true;
        return false;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void paint(){
        onDraw();
    }
    public void update() {
        onUpdate();
    }
    public int click(MotionEvent event) {
        return onTouch(event);
    }


    protected void onDraw() {
        if (!animatie.isVisible()){
            if (lastDir == 0){
                animatie = staStanga;
                animatie.setX(xbaza - staDreapta.getW() / 2);
                animatie.setY(ybaza - staDreapta.getH()); //y
                animatie.start();
            }
            else {
                animatie = staDreapta;
                animatie.setX(xbaza - staDreapta.getW() / 2);
                animatie.setY(ybaza - staDreapta.getH()); //y
                animatie.start();
            }
        }
        animatie.paint();
        for (int i = 0; i < 30; ++i){
            if (sulite[i] != null)
                sulite[i].paint();
        }
    }

    protected int onTouch(MotionEvent event) {
        return 0;
    }

    protected void onUpdate() {
        animatie.update();
        for (int i = 0; i < 30; ++i){
            if (sulite[i] != null)
                sulite[i].update();
        }

        for (int i = 0; i < 30; ++i){
            if (sulite[i] != null)
                if (!sulite[i].isVisible())
                    sulite[i] = null;
        }
    }

    protected synchronized void schimbaAnim(Sulita sulita){
        int angle = sulita.getAngle();
        if (angle < 0)
            angle = 360 + angle;
        if (angle >= 0 && angle <= 30) {//0 stanga
            animatie = atacDreapta;
            lastDir = 1;
        }
        else if (angle > 30 && angle <= 90){
            animatie = atacDreapta;
            lastDir = 1;
        }
        else if (angle > 90 && angle <= 150){
            animatie = atacStanga;
            lastDir = 0;
        }
        else if (angle > 150 && angle <=180){
            animatie = atacStanga;
            lastDir = 0;
        }
        else if (angle > 180 && angle <=210){
            animatie = atacStanga;
            lastDir = 0;
        }
        else if (angle >210 && angle <=270){
            animatie = atacStanga;
            lastDir = 0;
        }
        else if (angle > 270 && angle <=330){
            animatie = atacDreapta;
            lastDir = 1;
        }
        else if (angle > 330 && angle <=360){
            animatie = atacDreapta;
            lastDir = 1;
        }


        animatie.setX(xbaza - staDreapta.getW() / 2);
        animatie.setY(ybaza - staDreapta.getH()); //y
        animatie.setVisibleAfter(false);
        animatie.setSpeedAnim(7);
        animatie.start();
    }

    @Override
    public void colidedWith(Collidable object) {
        if (object.getClass() == IItaliaLuptator.class){
            if (canAttack()) {
                for (int i = 0; i < 30; ++i) {
                    if (sulite[i] == null) {
                        //a terminat si poate fi refolosita
                        sulite[i] = new Sulita(framebuffer, context, x, y, (IItaliaLuptator) object);
                        sulite[i].setDamage(damage);
                        schimbaAnim(sulite[i]);

                        lastAttack = System.currentTimeMillis();
                        break;
                    }
                }
            }
        }
    }


    public int getDamage() {
        return damage;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getW() {
        return w;
    }

    @Override
    public int getH() {
        return h;
    }

    @Override
    public int getRadius() {
        return radius;
    }

    @Override
    public boolean isMoving() {
        return false;
    }
}
