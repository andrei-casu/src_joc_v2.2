package com.example.casuneanuandrei.joc_v22;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;

public class GoldText {
    private Bitmap framebuffer;
    private Context context;
    private Graphics graphics;
    private Image image, icon, inima, vieti;
    private int viataTotal, viata;
    private Rect src;
    private int x, y, w, h;

    public GoldText(Bitmap framebuffer, Context context){
        this.framebuffer = framebuffer;
        this.context = context;

        graphics = new Graphics(framebuffer, context, true);
        icon = graphics.openImage("gold.png");
        inima = graphics.openImage("inima.png");
        Bitmap aux = graphics.drawText(""+Player.gold, Scaler.scale(125), Scaler.scale(60));
        image = new Image(aux);

        aux = graphics.drawText(""+Player.viata, Scaler.scale(125), Scaler.scale(60));
        vieti = new Image(aux);
    }

    public void paint(){onDraw();}

    protected void onDraw(){
        graphics.drawImage(icon, Ecran.w - 25 - icon.getW(), Scaler.scale(25), 0, 0, icon.getW(), icon.getH());
        graphics.drawImage(image, Ecran.w - 25 - icon.getW() - image.getW(), Scaler.scale(25), 0, 0, image.getW(), image.getH());

        graphics.drawImage(inima, 25, Scaler.scale(25), 0, 0, inima.getW(), inima.getH());
        graphics.drawImage(vieti, 30 + inima.getW(), Scaler.scale(25), 0, 0, vieti.getW(), vieti.getH());
    }

    public void update(){onUpdate();}

    protected void onUpdate(){
        if (Player.isGoldChanged()){
            Bitmap aux = graphics.drawText(""+Player.gold, Scaler.scale(125), Scaler.scale(60));
            image = new Image(aux);

            aux = graphics.drawText(""+Player.viata, Scaler.scale(125), Scaler.scale(60));
            vieti = new Image(aux);
        }
    }
}
