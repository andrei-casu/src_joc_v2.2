package com.example.casuneanuandrei.joc_v22;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Level extends Screen {
    private Graphics graphics;
    private Image background;
    private Bitmap framebuffer;
    private Context context;
    private Drum[] drumuri;
    private Pluton pluton;
    private ConstructorPluton constructorPluton;
    private int nrDrumuri = 2;
    private boolean down;
    private int xdown, ydown;

    private GoldText goldText;

    private int nrSuporturi;
    private SuportTurn[] suporturi;
    private Pluton[] plutoane;
    private int nrPlutoane;

    private Buton pause, resume;

    private String numeFolder;


    public Level(Bitmap framebuffer, Context context, String numeFolder){
        super();
        this.framebuffer = framebuffer;
        this.context = context;
        this.numeFolder = numeFolder;

        InputStream is=null;
        try {
            is = context.getAssets().open(numeFolder+"/nrDrumuri.txt");
            BufferedReader reader= new BufferedReader(new InputStreamReader(is));
            String aux;
            aux = reader.readLine();

            nrDrumuri = Integer.parseInt(aux);

        } catch (IOException e) {
            e.printStackTrace();
        }

        graphics = new Graphics(framebuffer, context, false);
        background = graphics.openImage(numeFolder+"/harta.png");
        drumuri = new Drum[nrDrumuri];
        for (int i = 0; i < nrDrumuri; ++i)
            drumuri[i] = new Drum(context, numeFolder+"/drum"+i+".txt");

        constructorPluton = new ConstructorPluton(framebuffer, context, numeFolder + "/plutoane.txt", drumuri);
        nrPlutoane = constructorPluton.getNrPlutoane();
        plutoane = new Pluton[nrPlutoane];
        int i;
        for (i = 0; i < nrPlutoane; ++i)
            plutoane[i] = constructorPluton.getPlutonPoz(i);

        int x, y;
        try {
            is = context.getAssets().open(numeFolder+"/suporturi.txt");
            BufferedReader reader= new BufferedReader(new InputStreamReader(is));
            String aux;
            aux = reader.readLine();

            nrSuporturi = Integer.parseInt(aux);
            suporturi = new SuportTurn[nrSuporturi];

            for (i = 0; i < nrSuporturi; ++i){
                aux = reader.readLine();
                x = Scaler.scale(Integer.parseInt(aux));
                aux = reader.readLine();
                y = Scaler.scale(Integer.parseInt(aux));
                suporturi[i] = new SuportTurn(context, framebuffer, x, y, Scaler.scale(165), Scaler.scale(120));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        goldText = new GoldText(framebuffer, context);
        for (i = 0; i < nrPlutoane; ++i)
            plutoane[i].start();

        resume = new Buton(framebuffer, "play.png", Ecran.w/2 - Scaler.scale(188), Scaler.scale(25), context, true);
        resume.setActive(false);
        resume.setVisible(false);

        pause = new Buton(framebuffer, "pause.png", Ecran.w/2, Scaler.scale(25), context, true);
        pause.setActive(true);
        pause.setVisible(true);
    }

    public void start(){
        int i;
        for (i = 0; i < nrPlutoane; ++i)
            plutoane[i].start();
    }

    @Override
    protected void onDraw() {
        graphics.resetCanvas();
        graphics.drawImage(background, 0, 0, 0, 0, background.getW(), background.getH());

        int i;
        for (i = 0; i < nrSuporturi; ++i)
            suporturi[i].paint();

        for (i = 0; i < nrPlutoane; ++i)
            plutoane[i].paint();
        goldText.paint();

        pause.paint();
        resume.paint();
    }

    @Override
    protected int onTouch(MotionEvent event) {
        float xt=0, xc=0, yt = 0, yc = 0;

        if (event.getActionMasked() == MotionEvent.ACTION_MOVE){
            int historySize = event.getHistorySize();
            xc = event.getX();
            yc = event.getY();
            if(historySize > 0) {
                ////////////////chestiile trb sa fie inchise
                ///////////////
                xt = event.getHistoricalX(historySize - 1);
                Offset.valuexNext += /*3 * */2 * (xc - xt) * Offset.scale;
                int aux = (int) (((double)background.getW() * Offset.scale - Ecran.w));
                if (Offset.valuexNext > 0)
                    Offset.valuexNext = 0;
                else if (Offset.valuexNext < -aux)
                    Offset.valuexNext = -aux;
                yt = event.getHistoricalY(historySize - 1);
                Offset.valueyNext += /*3 **/2 * (yc - yt) * Offset.scale;
                aux = (int) (((double)background.getH() * Offset.scale - Ecran.h));
                if (Offset.valueyNext > 0)
                    Offset.valueyNext = 0;
                else if (Offset.valueyNext < -aux)
                    Offset.valueyNext = -aux;
            }
            return EventTypes.MOVING;
        }
        else if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            down = true;
            xdown = (int) event.getX();
            ydown = (int) event.getY();

            if (pause.push(xdown, ydown)){
                Player.pause = true;
                pause.setActive(false);
                pause.setVisible(false);
                resume.setActive(true);
                resume.setVisible(true);
            }
            else if (resume.push(xdown, ydown)){
                Player.pause = false;
                resume.setActive(false);
                resume.setVisible(false);
                pause.setActive(true);
                pause.setVisible(true);
            }

        }
        else if (event.getActionMasked() == MotionEvent.ACTION_UP) {
            down = false;
            if (Math.abs(event.getX()-xdown)<10 && Math.abs(event.getY()-ydown)<10) {
                int i, j, x, y;
                boolean ok;

                x = (int) event.getX();
                y = (int) event.getY();

                ok = false;
                for (i = 0; i < nrSuporturi; i++) {
                    if (suporturi[i].push(x, y) && !suporturi[i].areCeva()) {
                        ok = true;

                        if (!suporturi[i].isDeschis()) {
                            for (j = 0; j < i; j++) suporturi[j].inchide();
                            for (j = i + 1; j < nrSuporturi; j++) suporturi[j].inchide();
                            suporturi[i].deschide();
                        }
                    }
                }

                //if (!ok)
                    //for (i = 0; i < nrSuporturi; i++)
                    //    suporturi[i].inchide();
            }
        }
        int i;
        for (i = 0; i < nrSuporturi; ++i)
            suporturi[i].click(event);
        return EventTypes.NONE_OF_YOUR_B;
    }

    public void handlePinchFocus(float x, float y){
        /*Offset.valuex = -(Ecran.w - x);
        Offset.valuey = -(Ecran.h - y);

        int aux = (int) ((background.getW()*Offset.scale - Ecran.w)/Offset.scale);
        if (Offset.valuex > 0)
            Offset.valuex = 0;
        else if (Offset.valuex < -aux)
            Offset.valuex = -aux;

        aux = (int) ((background.getH() * Offset.scale - Ecran.h)/Offset.scale);
        if (Offset.valuey > 0)
            Offset.valuey = 0;
        else if (Offset.valuey < -aux)
            Offset.valuey = -aux;

        /*Offset.valuex = - (int) x/2;
        Offset.valuey = - (int) y/2;*/
    }

    @Override
    protected void onUpdate() {
        int i;
        for (i = 0; i < nrPlutoane; ++i)
            plutoane[i].update();
        for (i = 0; i < nrSuporturi; ++i)
            suporturi[i].update();
        goldText.update();
    }
}
