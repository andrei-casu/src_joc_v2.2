package com.example.casuneanuandrei.joc_v22;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Joc extends Activity{
    private Screen screen;
    private Meniu meniu;
    private Levels levels;
    private Start start;
    private Renderer renderer;
    private Bitmap framebuffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Ecran.w = size.x;
        Ecran.h = size.y;

        framebuffer = Bitmap.createBitmap(Ecran.w, Ecran.h, Bitmap.Config.ARGB_8888);

        meniu = new Meniu(framebuffer, this);
        levels = new Levels(framebuffer, this);

        screen = meniu;
        renderer = new Renderer(this, framebuffer);
        setContentView(renderer);
        //renderer.setFocusable(true);
        //start();
    }

    public void start(){
        renderer.setRunning(true);
    }


    @Override
    public void onResume() {
        super.onResume();
        renderer.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        renderer.pause();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if (screen == levels)
            setScreen(meniu);
        else if (screen == meniu)
            super.onBackPressed();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int tip = screen.click(event);

        if (tip == EventTypes.SCHIMBA_MENIU){
            setScreen(meniu);
        }
        else if (tip == EventTypes.SCHIMBA_START){
            //setScreen(start);
        }
        else if (tip == EventTypes.SCHIMBA_CREDITS){
            //setScreen(credits);
        }
        else if (tip == EventTypes.SCHIMBA_LEVELS){
            setScreen(levels);
        }
        else if (tip == EventTypes.SCHIMBA_SHOP){
            //setScreen(shop);
        }
        else if (tip == EventTypes.SCHIMBA_OPTIONS){
            //setScreen(options);
        }
        else if (tip == EventTypes.SCHIMBA_HELP){
            //setScreen(help);
        }

        return super.onTouchEvent(event);
    }

    public void setScreen(Screen screen) {
        //renderer.pause();
        this.screen = screen;
        //renderer.resume();
    }

    public Screen getScreen() {
        return screen;
    }

    public void paint(){
        screen.paint();
    }

}
