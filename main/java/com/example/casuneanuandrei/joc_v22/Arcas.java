package com.example.casuneanuandrei.joc_v22;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;

public class Arcas implements Collidable{
    private Bitmap framebuffer;
    private Context context;
    private int x, y;
    private int w, h;
    private int radius;
    private int xbaza, ybaza;
    private double lastAttack;
    private double attackSpeed;//cate atacuri pe secunda
    private int damage;

    private SageataDreapta[] sageti; //?? nu stiu de cate ai nevoie

    private Animatie staDreapta, staStanga;
    private Animatie atacDreaptaSus, atacDreaptaMijloc, atacDreaptaJos;
    private Animatie atacStangaSus, atacStangaMijloc, atacStangaJos;
    private Animatie animatie;
    private int lastDir = 0;//0 stanga, 1 dreapta

    Arcas(Bitmap framebuffer, Context context, int xbaza, int ybaza){
        this.framebuffer = framebuffer;
        this.context = context;
        this.xbaza = xbaza;
        this.ybaza = ybaza;

        damage = 25;

        staDreapta = new Animatie(framebuffer, context, "arcas_dreapta_sta.png", 2, 1, false);
        staDreapta.setSpeedAnim(2);
        staDreapta.setLooping(true);
        //staDreapta.setX(xbaza - staDreapta.getW() / 2); //x
        //staDreapta.setY(ybaza - staDreapta.getH()); //y

        staStanga = new Animatie(framebuffer, context, "arcas_stanga_sta.png", 2, 1, false);
        staStanga.setSpeedAnim(2);
        staStanga.setLooping(true);

        atacDreaptaSus = new Animatie(framebuffer, context, "arcas_dreapta_sus.png", 5, 1, false);
        atacDreaptaSus.setSpeedAnim(12);
        atacDreaptaSus.setLooping(false);

        atacDreaptaMijloc = new Animatie(framebuffer, context, "arcas_dreapta_fata.png", 5, 1, false);
        atacDreaptaMijloc.setSpeedAnim(12);
        atacDreaptaMijloc.setLooping(false);

        atacDreaptaJos = new Animatie(framebuffer, context, "arcas_dreapta_jos.png", 5, 1, false);
        atacDreaptaJos.setSpeedAnim(12);
        atacDreaptaJos.setLooping(false);


        atacStangaSus = new Animatie(framebuffer, context, "arcas_stanga_sus.png", 5, 1, false);
        atacStangaSus.setSpeedAnim(12);
        atacStangaSus.setLooping(false);

        atacStangaMijloc = new Animatie(framebuffer, context, "arcas_stanga_fata.png", 5, 1, false);
        atacStangaMijloc.setSpeedAnim(12);
        atacStangaMijloc.setLooping(false);

        atacStangaJos = new Animatie(framebuffer, context, "arcas_stanga_jos.png", 5, 1, false);
        atacStangaJos.setSpeedAnim(12);
        atacStangaJos.setLooping(false);

        animatie = staDreapta;
        animatie.setX(xbaza - staDreapta.getW() / 2);
        animatie.setY(ybaza - staDreapta.getH()); //y
        animatie.start();

        x = xbaza - staDreapta.getW() / 2;
        y = ybaza - staDreapta.getH();

        w = staDreapta.getW();
        h = staDreapta.getH();

        sageti = new SageataDreapta[30];
        attackSpeed = 2;


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
            if (sageti[i] != null)
                sageti[i].paint();
        }
    }

    protected int onTouch(MotionEvent event) {
        return 0;
    }

    protected void onUpdate() {
        animatie.update();
        for (int i = 0; i < 30; ++i){
            if (sageti[i] != null)
                sageti[i].update();
        }

        for (int i = 0; i < 30; ++i){
            if (sageti[i] != null)
                if (!sageti[i].isVisible())
                    sageti[i] = null;
        }
    }

    protected synchronized void schimbaAnim(Sageata sageata){
        int angle = sageata.getAngle();
        if (angle < 0)
            angle = 360 + angle;
        if (angle >= 0 && angle <= 30) {//0 stanga
            animatie = atacDreaptaMijloc;
            lastDir = 1;
        }
        else if (angle > 30 && angle <= 90){
            animatie = atacDreaptaJos;
            lastDir = 1;
        }
        else if (angle > 90 && angle <= 150){
            animatie = atacStangaJos;
            lastDir = 0;
        }
        else if (angle > 150 && angle <=180){
            animatie = atacStangaMijloc;
            lastDir = 0;
        }
        else if (angle > 180 && angle <=210){
            animatie = atacStangaMijloc;
            lastDir = 0;
        }
        else if (angle >210 && angle <=270){
            animatie = atacStangaSus;
            lastDir = 0;
        }
        else if (angle > 270 && angle <=330){
            animatie = atacDreaptaSus;
            lastDir = 1;
        }
        else if (angle > 330 && angle <=360){
            animatie = atacDreaptaMijloc;
            lastDir = 1;
        }


        animatie.setX(xbaza - staDreapta.getW() / 2);
        animatie.setY(ybaza - staDreapta.getH()); //y
        animatie.setVisibleAfter(false);
        animatie.setSpeedAnim(12);
        animatie.start();
    }

    @Override
    public void colidedWith(Collidable object) {
        if (object.getClass() == IItaliaLuptator.class){
            if (canAttack()) {
                for (int i = 0; i < 30; ++i) {
                    if (sageti[i] == null) {
                        //a terminat si poate fi refolosita
                        sageti[i] = new SageataDreapta(framebuffer, context, x, y, (IItaliaLuptator) object);
                        sageti[i].setDamage(damage);
                        schimbaAnim(sageti[i]);

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
