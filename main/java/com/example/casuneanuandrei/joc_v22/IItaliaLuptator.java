package com.example.casuneanuandrei.joc_v22;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;

public class IItaliaLuptator extends Inamic{

    private Animatie animatie;
    private Miscare miscare;
    private int ind = 0;

    public IItaliaLuptator(Bitmap framebuffer, Context context, Drum drum){
        this.framebuffer = framebuffer;
        this.context = context;
        this.drum=drum;

        viteza = 10;

        viata = 1000;
        atac = 500;
        armour = 10;
        magic_armour = 10;

        x = drum.getPoint(0).x;
        y = drum.getPoint(0).y;

        nume = "level1/luptator.png";

        animatie = new Animatie(framebuffer, context, nume, 3, 2);
        animatie.setSpeedAnim(5);
        animatie.setLooping(true);
        animatie.setX(x);
        animatie.setY(y);
        animatie.start();
        animatie.setVisible(true);

        miscare = new Miscare(x, y, drum.getPoint(1).x, drum.getPoint(1).y, 70);
        ind = 1;
    }



    @Override
    protected void onDraw(){
        animatie.paint();
    }

    @Override
    protected int onTouch(MotionEvent event){
        return EventTypes.NONE_OF_YOUR_B;
    }

    @Override
    protected void onUpdate(){
        setX((int) miscare.getx());
        setY((int) miscare.gety());

        animatie.setX(x);
        animatie.setY(y);

        animatie.update();
        miscare.update();

        if (!miscare.isRunning()){
            ++ind;
            if (ind < drum.getLg())
                miscare = new Miscare(x, y, drum.getPoint(ind).x, drum.getPoint(ind).y, (int) miscare.getVitezaSec());
        }
    }
}
