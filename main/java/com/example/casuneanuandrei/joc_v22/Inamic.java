package com.example.casuneanuandrei.joc_v22;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;

public abstract class Inamic{

    protected Bitmap framebuffer;
    protected Context context;

    protected int x, y;
    protected int xbaza, ybaza;
    protected int w, h;
    protected int viata;
    protected int atac;
    protected int armour, magic_armour;
    protected int sumaCastig;
    protected boolean dead = false;

    protected Drum drum;
    protected int viteza;

    protected String nume;

    protected int delay;

    public Inamic(){
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void paint(){
        onDraw();
    }
    public int click(MotionEvent event){
        return onTouch(event);
    }
    public void update(){onUpdate();}

    protected abstract void onDraw();
    protected abstract int onTouch(MotionEvent event);
    protected abstract void onUpdate();

    public void setX(int x) {
        this.x = x;
        xbaza = x + w/2;
    }

    public void setXbaza(int x){
        xbaza = x;
        this.x = xbaza - w/2;
    }

    public void setYbaza(int y){
        ybaza = y;
        this.y = ybaza - h;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
        ybaza = y + h;
    }

    public void hitYa(int damage) {
        viata -= (damage - armour);
        if (viata <= 0 && !dead) {
            dead = true;
            Player.adaugaGold(sumaCastig);
        }
    }

    public int getY() {
        return y;
    }

    public void setViata(int viata) {
        this.viata = viata;
    }

    public int getViata() {
        return viata;
    }

    public void setAtac(int atac) {
        this.atac = atac;
    }

    public int getAtac() {
        return atac;
    }

    public void setArmour(int armour){
        this.armour = armour;
    }

    public int getArmour() {
        return armour;
    }

    public void setMagic_armour(int magic_armour) {
        this.magic_armour = magic_armour;
    }

    public int getMagic_armour() {
        return magic_armour;
    }

    public void setDrum(Drum drum) {
        this.drum = drum;
    }

    public Drum getDrum() {
        return drum;
    }

    public void setViteza(int viteza) {
        this.viteza = viteza;
    }

    public int getViteza() {
        return viteza;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getDelay() {
        return delay;
    }

    public int getW(){
        return w;
    }

    public int getH(){
        return h;
    }

    public boolean isDead() {
        return dead;
    }
}