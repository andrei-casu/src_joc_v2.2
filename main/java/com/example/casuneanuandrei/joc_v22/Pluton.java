package com.example.casuneanuandrei.joc_v22;

public class Pluton {

    private int nr_inamici;
    private int delay;
    private Drum drum;
    private int nrCurr;
    private int lastTime;
    private Inamic[] inamici;

    public Pluton(int nr_inamici){
        this.nr_inamici = nr_inamici;
        inamici = new Inamic[nr_inamici];
        nrCurr = 1;
    }

    public void start(){
        lastTime = (int) System.currentTimeMillis();
    }

    public void setNrInamici(int nr_inamici){
        this.nr_inamici = nr_inamici;
    }

    public int getNrInamici(){
        return nr_inamici;
    }

    public void setDelay(int delay){
        this.delay = delay;
    }

    public int getDelay(){
        return delay;
    }

    public void setDrum(Drum drum) {
        this.drum = drum;
    }

    public Drum getDrum() {
        return drum;
    }

    protected void onDraw(){
        if (delay != 0)
            return;
        int i;
        for (i = 0; i < nrCurr; ++i)
            if (inamici[i] != null)
                inamici[i].paint();
    }

    protected void onUpdate(){

        int currentTime = (int) System.currentTimeMillis();
        if (currentTime - lastTime <= delay && delay!=0)
            return;
        else if (delay != 0) {
            delay = 0;
            lastTime = currentTime;
        }
        if (nrCurr < nr_inamici)
            if (currentTime - lastTime >= inamici[nrCurr].getDelay()) {
                ++nrCurr;
                lastTime = currentTime;
            }

        int i;
        for (i = 0; i < nrCurr; ++i) {
            if (inamici[i] != null) {
                inamici[i].update();
            }
        }
    }

    public void paint(){
        onDraw();
    }

    public void update(){
        onUpdate();
    }


    public void setInamicPoz(Inamic inamic, int poz){
        inamici[poz] = inamic;
    }

    public Inamic getInamicPoz(int poz){
        return inamici[poz];
    }
}
