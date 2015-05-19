package com.example.casuneanuandrei.joc_v22;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;

public class SageataDreapta extends Sageata{

    public SageataDreapta(Bitmap framebuffer, Context context, int x, int y, Inamic inamic){
        this.framebuffer = framebuffer;
        this.context = context;
        this.xstart = x;
        this.ystart = y;
        this.inamic = inamic;
    }

    @Override
    protected void onDraw(){

    }

    @Override
    protected int onTouch(MotionEvent event){
        return EventTypes.NONE_OF_YOUR_B;
    }

    @Override
    protected void onUpdate(){

    }
}
