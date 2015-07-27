package com.example.casuneanuandrei.joc_v22;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConstructorPluton {

    private Bitmap framebuffer;
    private Context context;
    private String nume;

    private int nr_plutoane;
    private Pluton[] plutoane;

    public ConstructorPluton(Bitmap framebuffer, Context context, String nume, Drum[] drumuri){
        this.framebuffer = framebuffer;
        this.context = context;
        this.nume = nume;

        InputStream is=null;
        BufferedReader reader;
        String aux;

        int i, j, nr;
        String[] date;
        int tip, delay;

        //String date[];
        //int tip, delay;
        //int i, j, nr;

        int delayAux;

        try {
            is = context.getAssets().open(nume);
            reader= new BufferedReader(new InputStreamReader(is));

            aux = reader.readLine();
            nr_plutoane = Integer.parseInt(aux);
            plutoane = new Pluton[nr_plutoane];
            date = new String[5];

            for (i=0; i<nr_plutoane; ++i){

                //numar inamici
                aux = reader.readLine();
                plutoane[i] = new Pluton(Integer.parseInt(aux));
                //delay
                aux = reader.readLine();
                delayAux = Integer.parseInt(aux);
                plutoane[i].setDelay(delayAux);

                //drum
                aux = reader.readLine();
                plutoane[i].setDrum(drumuri[Integer.parseInt(aux)]);

                // constructia inamicilor

                nr = plutoane[i].getNrInamici();
                for (j=0; j<nr; ++j){
                    aux = reader.readLine();
                    date = aux.split(" ");

                    tip = Integer.parseInt(date[0]);
                    delay = Integer.parseInt(date[1]);


                    switch (tip) {
                        case 1:
                            plutoane[i].setInamicPoz(new IItaliaLuptator(framebuffer, context, plutoane[i].getDrum(), delay), j);
                            break;
                        case 2:
                            break;
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getNrPlutoane(){
        return nr_plutoane;
    }

    public Pluton getPlutonPoz(int poz){
        return plutoane[poz];
    }
}
