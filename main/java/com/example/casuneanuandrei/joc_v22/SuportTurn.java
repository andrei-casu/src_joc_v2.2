package com.example.casuneanuandrei.joc_v22;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;

public class SuportTurn {

    private Turn turn;
    //private TurnArcasi turn_arcasi;

    //private TurnMagicieni turn_magicieni;
    //private TurnLuptatori turn_luptatori;

    private Context context;
    private Bitmap framebuffer;
    private Buton buton;

    private boolean deschis;
    private int x, y;
    private int w, h;

    private Animatie animatie_open;
    private Animatie animatie_close;

    private Buton bArcasi, bSulita, bTun, bLuptatori;
    private Buton bArcasiPret, bSulitaPret, bTunPret, bLuptatoriPret;

    public SuportTurn(Context context, Bitmap framebuffer, int x, int y, int width, int height){
        this.context = context;
        this.framebuffer = framebuffer;

        this.x=x;
        this.y=y;
        this.w=width;
        this.h=height;

        animatie_open = new Animatie(framebuffer, context, "suport_turn_open.png", 3, 3, false);
        animatie_open.setSpeedAnim(22);
        animatie_open.setLooping(false);
        animatie_open.setX(x + w / 2 - animatie_open.getW() / 2);
        animatie_open.setY(y + h / 2 - animatie_open.getH() / 2);

        animatie_close = new Animatie(framebuffer, context, "suport_turn_close.png", 3, 3, false);
        animatie_close.setSpeedAnim(22);
        animatie_close.setLooping(false);
        animatie_close.setX(x + w / 2 - animatie_open.getW() / 2);
        animatie_close.setY(y + h / 2 - animatie_open.getH() / 2);

        deschis=false;

        buton = new Buton(framebuffer, x, y, w, h, context, false);

        bArcasi = new Buton(framebuffer, x + w / 2 - animatie_open.getW() / 2, y + h / 2 - animatie_open.getH() / 2, animatie_open.getW()/2, animatie_open.getH()/2, context, false);
        bSulita = new Buton(framebuffer, x + w / 2, y + h / 2 - animatie_open.getH() / 2, animatie_open.getW()/2, animatie_open.getH()/2, context, false);
        bTun = new Buton(framebuffer, x + w / 2 - animatie_open.getW() / 2, y + h / 2, animatie_open.getW()/2, animatie_open.getH()/2, context, false);
        bLuptatori = new Buton(framebuffer, x + w / 2, y + h / 2, animatie_open.getW()/2, animatie_open.getH()/2, context, false);

        bArcasiPret = new Buton(framebuffer, "cerc_arcas_pret.png", x + w / 2 - animatie_open.getW() / 2, y + h / 2 - animatie_open.getH() / 2, context, false);
        bArcasiPret.setVisible(false);
        bArcasiPret.setActive(false);

        bSulitaPret = new Buton(framebuffer, "cerc_sulitar_pret.png", x + w / 2 - animatie_open.getW() / 2, y + h / 2 - animatie_open.getH() / 2, context, false);
        bSulitaPret.setVisible(false);
        bSulitaPret.setActive(false);

        bTunPret = new Buton(framebuffer, "cerc_tun_pret.png", x + w / 2 - animatie_open.getW() / 2, y + h / 2 - animatie_open.getH() / 2, context, false);
        bTunPret.setVisible(false);
        bTunPret.setActive(false);



        bArcasi.setActive(false);
        bSulita.setActive(false);
        bTun.setActive(false);
        bLuptatori.setActive(false);
    }

    public boolean areCeva(){
        return turn != null;
    }

    public boolean push(int x, int y) {
        return buton.push(x, y);
    }

    public void paint(){
        onDraw();
    }
    public void update() {
        onUpdate();
    }
    public int click(MotionEvent event) {
        return onTouch(event);
    }

    protected void onDraw(){
        animatie_open.paint();
        animatie_close.paint();

        bArcasiPret.paint();
        bSulitaPret.paint();
        bTunPret.paint();

        if (turn != null){
            turn.paint();
        }
    }

    protected int onTouch(MotionEvent event){

        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();

            if (bArcasi.push(x, y) && !animatie_open.isRunning() && turn == null) {
                inchide();
                bArcasiPret.setVisible(true);
                bArcasiPret.setActive(true);
                buton.setActive(false);
            }
            else if (bSulita.push(x, y) && !animatie_open.isRunning() && turn == null) {
                inchide();
                bSulitaPret.setVisible(true);
                bSulitaPret.setActive(true);
                buton.setActive(false);
            }
            else if (bTun.push(x, y) && !animatie_open.isRunning() && turn == null){
                inchide();
                bTunPret.setVisible(true);
                bTunPret.setActive(true);
                buton.setActive(false);
            }
            else if (bArcasiPret.push(x, y) && turn == null && Player.gold >= 100){
                Player.adaugaGold(-100);
                turn = new TurnArcasi(framebuffer, context, this.x + w / 2, this.y + h);
                inchide();
            }
            else if (bSulitaPret.push(x, y) && turn == null && Player.gold >= 110){
                Player.adaugaGold(-110);
                turn = new TurnSulitari(framebuffer, context, this.x + w / 2, this.y + h);
                inchide();
            }
            else if (bTunPret.push(x, y) && turn == null && Player.gold >= 140){
                Player.adaugaGold(-140);
                turn = new TurnTun(framebuffer, context, this.x + w / 2, this.y + h);
                inchide();
            }
            else{
                if (!deschis)
                    inchide();
            }
        }
        if (turn != null) {
            if (turn.click(event) != EventTypes.CLICK_PE_CEVA_TURN)
                turn.inchide();
        }
        return EventTypes.NONE_OF_YOUR_B;
    }

    protected void onUpdate(){
        animatie_open.update();
        animatie_close.update();

        if (animatie_open.isRunning() == false && animatie_open.isVisible() && !bArcasi.isActive()){
            bArcasi.setActive(true);
            bSulita.setActive(true);
            bTun.setActive(true);
            bLuptatori.setActive(true);
        }

        if (turn != null){
            turn.update();
        }
    }

    public boolean isDeschis() {
        return deschis;
    }

    public void deschide(){
        if (!deschis && turn == null){
            deschis = true;
            animatie_open.setVisible(true);
            animatie_open.setVisibleAfter(true);
            animatie_open.start();
        }
    }

    public void inchide(){
        if (deschis){
            deschis = false;
            animatie_open.setVisible(false);
            animatie_close.setVisible(true);
            animatie_close.setVisibleAfter(false);
            animatie_close.start();

        }
        bArcasi.setActive(false);
        bArcasi.setVisible(false);
        bSulita.setActive(false);
        bSulita.setVisible(false);
        bTun.setActive(false);
        bTun.setVisible(false);
        //bLuptatori.setActive(false);
        //bLuptatori.setVisible(false);

        bArcasiPret.setActive(false);
        bArcasiPret.setVisible(false);
        bSulitaPret.setActive(false);
        bSulitaPret.setVisible(false);
        bTunPret.setActive(false);
        bTunPret.setVisible(false);
        //bLuptatoriPret.setActive(false);
        //bLuptatoriPret.setVisible(false);

        buton.setActive(true);
    }
}
