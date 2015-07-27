package com.example.casuneanuandrei.joc_v22;

public class Miscare {
    private double xCurr = 0, yCurr = 0;
    private int xDest, yDest;
    private boolean running;
    private double vitezaSec;
    private double viteza;
    private double mx, my;
    private double rap;
    private int semnx, semny;

    Miscare(int xCurr, int yCurr, int xDest, int yDest, int viteza){
        this.xCurr = xCurr;
        this.yCurr = yCurr;

        this.xDest = xDest;
        this.yDest = yDest;

        vitezaSec = viteza;

        this.viteza = vitezaSec / 60;
        running = true;

        if (yDest == yCurr)
            rap = 0;
        else
            rap = (double) (xDest - xCurr) / (yDest - yCurr);

        rap = Math.abs(rap);

        if (xDest < xCurr)
            semnx = -1;
        else
            semnx = 1;

        if (yDest < yCurr)
            semny = -1;
        else
            semny = 1;
    }

    public boolean isRunning() {
        return running;
    }

    public double getVitezaSec() {
        return vitezaSec;
    }

    void resume(){
        running = true;
    }

    void pause(){
        running = false;
    }

    public double getx() {
        return xCurr;
    }

    public double gety() {
        return yCurr;
    }

    protected void onUpdate(){
        if (running == false)
            return;

        if (xCurr == xDest){
            mx = 0;
            my = viteza;
        }
        else if (yCurr == yDest){
            mx = viteza;
            my = 0;
        }
        else{
            mx = Math.sqrt(Math.abs(viteza * viteza * rap * rap) / (rap * rap + 1));
            my = Math.sqrt(Math.abs(viteza * viteza - mx * mx));
        }

        if (mx > viteza)
            mx = 0;
        if (my > viteza)
            my = 0;

        xCurr += semnx * mx;
        yCurr += semny * my;

        if (Math.abs(xCurr - xDest) <= 6 && Math.abs(yCurr - yDest) <= 6)
            running = false;

    }

    public void update(){
        onUpdate();
    }
}
