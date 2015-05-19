package com.example.casuneanuandrei.joc_v22;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

public class Level1 extends Screen {
    private Graphics graphics;
    private Image background;
    private Bitmap framebuffer;
    private Context context;
    private Drum[] drumuri;
    private Pluton pluton;
    private ConstructorPluton constructorPluton;
    private int nrDrumuri = 1;


    public Level1(Bitmap framebuffer, Context context){
        super();
        this.framebuffer = framebuffer;
        this.context = context;

        graphics = new Graphics(framebuffer, context);
        background = graphics.openImage("level1/harta.png");
        drumuri = new Drum[nrDrumuri];
        drumuri[0] = new Drum(context, "level1/drum1.txt");

        constructorPluton = new ConstructorPluton(framebuffer, context, "level1/plutoane.txt", drumuri);
        pluton = constructorPluton.getPlutonPoz(0);
    }

    @Override
    protected void onDraw() {
        graphics.resetCanvas();
        graphics.drawImage(background, 0, 0, 0, 0, background.getW(), background.getH());
        pluton.paint();
    }

    @Override
    protected int onTouch(MotionEvent event) {

        float xt=0, xc=0, yt = 0, yc = 0;

        if (event.getActionMasked() == MotionEvent.ACTION_MOVE){
            int historySize = event.getHistorySize();
            xc = event.getX();
            yc = event.getY();
            if(historySize > 0) {
                xt = event.getHistoricalX(historySize - 1);
                Offset.valuex += /*3 * */2 * (xc - xt);
                int aux = (int) ((background.getW()*Offset.scale - Ecran.w)/Offset.scale);
                if (Offset.valuex > 0)
                    Offset.valuex = 0;
                else if (Offset.valuex < -aux)
                    Offset.valuex = -aux;

                yt = event.getHistoricalY(historySize - 1);
                Offset.valuey += /*3 **/2 * (yc - yt);
                aux = (int) ((background.getH() * Offset.scale - Ecran.h)/Offset.scale);
                if (Offset.valuey > 0)
                    Offset.valuey = 0;
                else if (Offset.valuey < -aux)
                    Offset.valuey = -aux;
            }
        }
        return EventTypes.NONE_OF_YOUR_B;
    }

    public void handlePinchFocus(float x, float y){
        /*Offset.valuex = - (int) x/2;
        Offset.valuey = - (int) y/2;


        int aux = (int) ((background.getW()*Offset.scale - Ecran.w)/Offset.scale);
        if (Offset.valuex > 0)
            Offset.valuex = 0;
        else if (Offset.valuex < -aux)
            Offset.valuex = -aux;

        aux = (int) ((background.getH() * Offset.scale - Ecran.h)/Offset.scale);
        if (Offset.valuey > 0)
            Offset.valuey = 0;
        else if (Offset.valuey < -aux)
            Offset.valuey = -aux;*/
    }

    @Override
    protected void onUpdate() {
        pluton.update();
    }
}
