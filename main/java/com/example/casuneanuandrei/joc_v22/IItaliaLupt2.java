package com.example.casuneanuandrei.joc_v22;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.view.MotionEvent;

public class IItaliaLupt2  extends Inamic implements Collidable{
    private Animatie animatie;
    private Animatie aStanga, aDreapta, aSus, aJos;
    private Miscare miscare;
    private int ind = 0;
    private Drum drumOcol;
    private String numeFolder;
    private int directie;
    private HealthBar healthBar;

    static final int SUS = 0, STANGA = 1, JOS = 2, DREAPTA = 3;

    public IItaliaLupt2(Bitmap framebuffer, Context context, Drum drum, int delay){
        this.framebuffer = framebuffer;
        this.context = context;
        this.drum=drum;
        this.delay = delay;

        viteza = Scaler.scale(50);

        viata = 500;
        sumaCastig = 70;
        healthBar = new HealthBar(framebuffer, context, viata, this);
        atac = 50;
        armour = 10;
        magic_armour = 10;

        x = drum.getPoint(0).x;
        y = drum.getPoint(0).y;

        numeFolder = "level1/";

        /*animatie = new Animatie(framebuffer, context, nume, 3, 2);
        animatie.setSpeedAnim(5);
        animatie.setLooping(true);
        animatie.setX(x);
        animatie.setY(y);
        animatie.start();
        animatie.setVisible(true);*/

        aJos = new Animatie(framebuffer, context, numeFolder+"luptator.png", 3, 2, false);
        aJos.setSpeedAnim(5);
        aJos.setX(x);
        aJos.setY(y);
        aJos.setVisible(true);
        aJos.setLooping(true);

        aSus = new Animatie(framebuffer, context, numeFolder+"luptator.png", 3, 2, false);
        aSus.setSpeedAnim(5);
        aSus.setX(x);
        aSus.setY(y);
        aSus.setVisible(true);
        aSus.setLooping(true);

        aStanga = new Animatie(framebuffer, context, "inamic_stanga.png", 3, 2, false);
        aStanga.setSpeedAnim(5);
        aStanga.setX(x);
        aStanga.setY(y);
        aStanga.setVisible(true);
        aStanga.setLooping(true);

        aDreapta = new Animatie(framebuffer, context, "inamic_dreapta.png", 3, 2, false);
        aDreapta.setSpeedAnim(5);
        aDreapta.setX(x);
        aDreapta.setY(y);
        aDreapta.setVisible(true);
        aDreapta.setLooping(true);

        w = aSus.getW();
        h = aSus.getH();

        xbaza = x + w/2;
        ybaza = y + h;

        ind = 1;
        schimbaDirectie(drum.getPoint(ind));

        CollisionDetector.addObject(this);
    }


    public void ocoleste(Drum drum){
        drumOcol = drum;
    }


    protected void schimbaDirectie(Point point){
        int dx, dy;
        dx = Math.abs(point.x - x);
        dy = Math.abs(point.y - y);

        if (dx > dy){
            //se misca pe parti, stanga sau dreapta
            if ((point.x - x) < 0) {
                directie = STANGA;
                animatie = aStanga;
                animatie.start();
            }
            else {
                directie = DREAPTA;
                animatie = aDreapta;
                animatie.start();
            }
        }
        else{
            if ((point.y - y) < 0) {
                directie = JOS;
                animatie = aJos;
                animatie.start();
            }
            else {
                directie = SUS;
                animatie = aSus;
                animatie.start();
            }
        }

        miscare = new Miscare(xbaza, ybaza, point.x, point.y, viteza);
    }


    @Override
    protected void onDraw(){
        if (dead)
            return;
        animatie.paint();
        healthBar.paint();
    }

    @Override
    protected int onTouch(MotionEvent event){
        return EventTypes.NONE_OF_YOUR_B;
    }

    @Override
    protected void onUpdate(){
        if (dead)
            return;
        miscare.update();
        if (!miscare.isRunning()) {
            ++ind;
            if (ind < drum.getLg())
                schimbaDirectie(drum.getPoint(ind));
            else{
                //scad o viata
                Player.scadeOViata();
                dead = true;
            }
        }

        setXbaza((int) miscare.getx());
        setYbaza((int) miscare.gety());

        animatie.update();
        animatie.setX(x);
        animatie.setY(y);

        healthBar.update();
    }

    protected int distPatr(int x1, int y1, int x2, int y2){
        return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
    }

    @Override
    public void colidedWith(Collidable object) {
        int dCurr, dOther;
        if (IItaliaLuptator.class == object.getClass()){
            //vad daca e el inainte sau celalalt

            //distanta intre el insusi si punctul in care trebuie sa ajunga
            dCurr = distPatr(x, y, drum.getPoint(ind).x, drum.getPoint(ind).y);
            dOther = distPatr(object.getX(), object.getY(), drum.getPoint(ind).x, drum.getPoint(ind).y);

            if (dCurr > dOther && object.isMoving() == false){
                //trebuie sa il ocolesc
                //la drum trebuie sa adaug 2 puncte
            }
        }
    }

    @Override
    public int getRadius() {
        return 0;
    }

    @Override
    public boolean isMoving() {
        return miscare.isRunning();
    }
}
