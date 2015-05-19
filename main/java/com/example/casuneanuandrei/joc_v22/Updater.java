package com.example.casuneanuandrei.joc_v22;

public class Updater {
    private boolean running;
    private Joc joc;
    private Updt thread;

    private class Updt extends Thread{
        double wait = 1000.0/60;
        @Override
        public void run() {
            int ti, tf;
            while (running){
                ti = (int) System.currentTimeMillis();
                joc.update();
                tf = (int) System.currentTimeMillis();


                if ((wait - (tf - ti)) > 0)
                try {
                    this.sleep((long) (wait - (tf - ti)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void Updt(){
        }
    }

    public Updater(Joc joc){
        this.joc = joc;
        running = false;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void resume(){
        running = true;
        thread = new Updt();
        thread.start();
    }

    public void pause() {
        running = false;
        while(true) {
            try {
                thread.join();
                break;
            } catch (InterruptedException e) {
                // retry
            }

        }
    }
}
