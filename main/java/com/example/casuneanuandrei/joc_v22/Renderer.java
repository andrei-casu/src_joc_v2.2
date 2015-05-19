package com.example.casuneanuandrei.joc_v22;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Renderer extends SurfaceView{
    private boolean running;
    private Joc joc;
    private SurfaceHolder surfaceHolder;
    private Canvas frame;
    private Bitmap framebuffer;
    private ren renderThread;
    private Context context;

    private class ren extends Thread{
        @Override
        public void run(){
            Rect dstRect = new Rect();
            while (running){
                if(!surfaceHolder.getSurface().isValid())
                    continue;

                frame = surfaceHolder.lockCanvas();
                joc.paint();
                dstRect.set(0, 0, framebuffer.getWidth(), framebuffer.getHeight());
                frame.drawBitmap(framebuffer, null, dstRect, null);
                surfaceHolder.unlockCanvasAndPost(frame);
            }
        }
    }

    public Renderer(Joc joc, Bitmap framebuffer){
        super(joc);
        this.joc = joc;
        this.surfaceHolder = getHolder();
        this.framebuffer = framebuffer;
        running = false;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void resume() {
        running = true;
        renderThread = new ren();
        renderThread.start();
    }

    public void pause() {
        running = false;
        while(true) {
            try {
                renderThread.join();
                break;
            } catch (InterruptedException e) {
                // retry
            }

        }
    }

    public Canvas getFrame() {
        return frame;
    }

}
