package com.example.casuneanuandrei.joc_v22;

import android.content.Context;
import android.graphics.Point;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Drum {

    private int lg; //0 pana la lg-1
    private Point drum[];


    public Drum(Context context, String nume) {

        int i;
        InputStream is=null;
        BufferedReader reader;

        String aux;
        String [] puncte;

        try {
            is = context.getAssets().open(nume);
            reader= new BufferedReader(new InputStreamReader(is));
            puncte = new String [3];

            aux = reader.readLine();
            lg = Integer.parseInt(aux);
            drum = new Point [lg];

            for (i=0; i<lg; ++i) {
                aux = reader.readLine();
                puncte = aux.split(" ");

                drum[i] = new Point (Scaler.scale(Integer.parseInt(puncte[0])), Scaler.scale(Integer.parseInt(puncte[1])));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getLg() {
        return lg;
    }

    public Point getPoint (int ind){
        return drum[ind];
    }
}