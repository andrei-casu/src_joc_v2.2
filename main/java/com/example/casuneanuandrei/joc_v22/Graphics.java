package com.example.casuneanuandrei.joc_v22;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

import java.io.IOException;
import java.io.InputStream;

public class Graphics {
    private Canvas canvas;
    private Paint paint;
    private Rect srcRect, dstRect;
    private Context context;
    private Bitmap framebuffer;
    private boolean fixed = false;

    public Graphics(Bitmap framebuffer, Context context, boolean fixed){
        this.canvas = new Canvas(framebuffer);
        paint = new Paint();
        srcRect = new Rect();
        dstRect = new Rect();
        this.context = context;
        this.framebuffer = framebuffer;
        canvas.setBitmap(this.framebuffer);

        this.fixed = fixed;
    }

    public void resetCanvas(){
        canvas.drawColor(Color.BLACK);
    }

    public void drawImage(Image image, int x, int y, int xs, int ys, int ws, int hs){
        srcRect.set(xs, ys, xs+ws, ys+hs);

        int xaux, yaux;

        if (!fixed) {//atunci e scalabil
            xaux = (int) (x * Offset.scale + Offset.valuex);
            yaux = (int) (y * Offset.scale + Offset.valuey);
            dstRect.set((int)xaux, (int)yaux, (int)(xaux+ws*Offset.scale), (int)(yaux+hs*Offset.scale));
        }
        else {
            dstRect.set(x, y, x + ws, y + hs);
        }

        canvas.drawBitmap(image.getBitmap(), srcRect, dstRect, null);
    }

    public Image rotateImage(Image image, int angle){//trebuie sa fie patrata
        Bitmap fin = Bitmap.createBitmap(image.getW(), image.getH(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(fin);
        c.rotate(angle, image.getW() / 2, image.getH() / 2);
        c.drawBitmap(image.getBitmap(), 0, 0, null);
        return new Image(fin);
    }

    public Image openImage(String nume){
        Image imagine = null;
        try {
            Bitmap aux = BitmapFactory.decodeStream(context.getAssets().open(nume));
            int w = Scaler.scale(aux.getWidth());
            int h = Scaler.scale(aux.getHeight());
            Bitmap fin = Bitmap.createScaledBitmap(aux, w, h, true);
            imagine = new Image(fin);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagine;
    }


    public Bitmap drawText(String text, int textWidth, int textSize) {
        // Get text dimensions
        TextPaint textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(textSize);
        StaticLayout mTextLayout = new StaticLayout(text, textPaint,
                textWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);

        // Create bitmap and canvas to draw to
        Bitmap b = Bitmap.createBitmap(textWidth, mTextLayout.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);



        c.save();
        mTextLayout.draw(c);
        c.restore();

        return b;
    }

    /*public Bitmap drawText(Context gContext, int gResId, String gText) {
        Resources resources = gContext.getResources();
        float scale = resources.getDisplayMetrics().density;
        Bitmap bitmap =
                BitmapFactory.decodeResource(resources, gResId);

        android.graphics.Bitmap.Config bitmapConfig =
                bitmap.getConfig();
        // set default bitmap config if none
        if(bitmapConfig == null) {
            bitmapConfig = android.graphics.Bitmap.Config.ARGB_8888;
        }
        // resource bitmaps are imutable,
        // so we need to convert it to mutable one
        bitmap = bitmap.copy(bitmapConfig, true);

        Canvas canvas = new Canvas(bitmap);
        // new antialised Paint
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // text color - #3D3D3D
        paint.setColor(Color.rgb(61, 61, 61));
        // text size in pixels
        paint.setTextSize((int) (14 * scale));
        // text shadow
        paint.setShadowLayer(1f, 0f, 1f, Color.WHITE);

        // draw text to the Canvas center
        Rect bounds = new Rect();
        paint.getTextBounds(gText, 0, gText.length(), bounds);

        canvas.drawText(gText, x, y, paint);

        return bitmap;
    }*/

    public Canvas getCanvas() {
        return canvas;
    }
}
