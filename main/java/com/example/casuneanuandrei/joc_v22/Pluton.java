package com.example.casuneanuandrei.joc_v22;

public class Pluton {

    private int nr_inamici;
    private int delay;
    private Drum drum;

    private Inamic[] inamici;

    public Pluton(int nr_inamici){
        this.nr_inamici = nr_inamici;
        inamici = new Inamic[nr_inamici];
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
        int i;
        for (i = 0; i < nr_inamici; ++i)
            inamici[i].paint();
    }

    protected void onUpdate(){
        int i;
        for (i = 0; i < nr_inamici; ++i)
            inamici[i].update();
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
