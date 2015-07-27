package com.example.casuneanuandrei.joc_v22;

public final class Player {
    public static boolean pause = false;
    public static int gold = 500, viata = 10;
    public static boolean isChanged = true;

    public static boolean isGoldChanged(){
        boolean copie = isChanged;
        isChanged = false;
        return copie;
    }

    public static void adaugaGold(int suma){
        gold += suma;
        isChanged = true;
    }

    public static void scadeOViata(){
        --viata;
        isChanged = true;
    }

    private Player(){
        ////////////
    }
}
