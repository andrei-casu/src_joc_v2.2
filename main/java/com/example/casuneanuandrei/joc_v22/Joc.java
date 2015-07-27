package com.example.casuneanuandrei.joc_v22;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Joc extends Activity{
    private Screen screen;
    private Meniu meniu;
    private Start start;
    private Credits credits;
    private Levels levels;
    private Level level;
    private Shop shop;
    private Options options;
    private Help help;

    private Renderer renderer;
    private Updater updater;
    private Bitmap framebuffer;
    private ScaleGestureDetector scaleListener;

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

        CollisionDetector.init();

        Scaler.rap = (double) Ecran.h / 1080;

        framebuffer = Bitmap.createBitmap(Ecran.w, Ecran.h, Bitmap.Config.ARGB_8888);

        meniu = new Meniu(framebuffer, this);
        start = new Start(framebuffer, this);
        credits = new Credits(framebuffer, this);
        levels = new Levels(framebuffer, this);
        shop = new Shop(framebuffer, this);
        options = new Options(framebuffer, this);
        help = new Help (framebuffer, this);
        level = new Level(framebuffer, this, "level1");

        screen = meniu;
        renderer = new Renderer(this, framebuffer);
        setContentView(renderer);

        updater = new Updater(this);

        scaleListener = new ScaleGestureDetector(this, new ScaleListener());
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
        updater.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        renderer.pause();
        updater.pause();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if (screen == start || screen == credits)
            setScreen(meniu);
        else if (screen == levels || screen == shop || screen == options || screen == help)
            setScreen(start);
        else if (screen == level) {
            Offset.scale = 1.0f;
            setScreen(levels);
        }
        else if (screen == meniu)
            super.onBackPressed();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int tip = screen.click(event);

        scaleListener.onTouchEvent(event);

        if (tip == EventTypes.SCHIMBA_MENIU){
            setScreen(meniu);
        }
        else if (tip == EventTypes.SCHIMBA_START){
            setScreen(start);
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
        else if (tip == EventTypes.SCHIMBA_LEVEL1){
            Offset.scale = 1.0f;
            //CollisionDetector.init();
            //Player.gold = 500;
            //Player.viata = 10;
            //Player.isChanged = true;
            //level1 = new Level1(framebuffer, this);
            setScreen(level);
            level.start();
        }

        return super.onTouchEvent(event);
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            double wInainte, wDupa;
            double hInainte, hDupa;
            if (screen != level)
                return true;
            level.handlePinchFocus(detector.getFocusX(), detector.getFocusY());

            wInainte = Ecran.w * Offset.scaleNext;
            hInainte = Ecran.h * Offset.scaleNext;
            Offset.scaleNext *= detector.getScaleFactor();
            Offset.scaleNext = Math.max(Ecran.h / (1440 * (float) Ecran.h / 1080), Math.min(Offset.scaleNext, 2.0f));
            wDupa = Ecran.w * Offset.scaleNext;
            hDupa = Ecran.h * Offset.scaleNext;
            Offset.valuexNext += -(wDupa - wInainte)/2;
            Offset.valueyNext += -(hDupa - hInainte)/2;
            return true;
        }
    }

    public void setScreen(Screen screen) {
        //renderer.pause();
        this.screen = screen;
        //renderer.resume();
    }

    protected void onDraw(){
        screen.paint();
    }

    protected void onUpdate(){
        screen.update();
    }

    public void paint(){
        onDraw();
    }

    public void update(){
        onUpdate();
    }

}
