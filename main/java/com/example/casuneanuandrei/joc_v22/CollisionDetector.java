package com.example.casuneanuandrei.joc_v22;

import java.util.Vector;

public final class CollisionDetector {
    private static Vector vectorInamici, vectorRadius, vectorLuptatori;
    private static int vI = 0, vR = 0, vL = 0;

    private CollisionDetector(){
    }

    public static void init(){
        vectorInamici = new Vector();
        vectorRadius = new Vector();
        vectorLuptatori = new Vector();
    }

    public static void addObject(Collidable object){
        if (object.getClass() == IItaliaLuptator.class) {
            vectorInamici.add(object);
            ++vI;
        }
        else if (object.getClass() == Arcas.class || object.getClass() == Sulitar.class || object.getClass() == Tunar.class){
            vectorRadius.add(object);
            ++vR;
        }
        //else if(object.getClass() == )
    }

    public static void checkCollisions(){
        //aici verific eu tot
        //fiecare element cu fiecare element, practic in n^2

        int i, j;
        Collidable a, b;
        for (i = 0; i < vI; ++i){
            a = (Collidable) vectorInamici.get(i);
            if (((Inamic)a).isDead())
                continue;
            for (j = 0; j < vR; ++j){
                b = (Collidable) vectorRadius.get(j);
                if (distPatr(a.getX(), a.getY(), b.getX(), b.getY()) <= b.getRadius()*b.getRadius()){
                    a.colidedWith(b);
                    b.colidedWith(a);
                }
            }
        }


        /*for (i = 0; i < n; ++i){
            a = (Collidable) vector.get(i);
            for (j = i + 1; j < n; ++j){
                b = (Collidable) vector.get(j);
                if (    a.getX() < b.getX() + b.getW() &&
                        a.getX() + a.getW() > b.getX() &&
                        a.getY() < b.getY() + b.getH() &&
                        a.getH() + a.getY() > b.getY()) {
                    a.colidedWith(b);
                    b.colidedWith(a);
                }
            }
        }*/

    }

    private static double distPatr(int x1, int y1, int x2, int y2){
        return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
    }
}
