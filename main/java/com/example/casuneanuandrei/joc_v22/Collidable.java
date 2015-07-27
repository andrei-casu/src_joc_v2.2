package com.example.casuneanuandrei.joc_v22;

interface Collidable {
    void colidedWith(Collidable object);
    int getX();
    int getY();
    int getW();
    int getH();
    int getRadius();
    boolean isMoving();
}
