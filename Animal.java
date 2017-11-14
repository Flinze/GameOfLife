package ca.bcit.comp2526.a2a;

import java.awt.*;

public abstract class Animal extends Entity {

    private int lifeCount = 0;

    public void setLifeCount(int lifeCount) {
        this.lifeCount = lifeCount;
    }

    public int getLifeCount() {
        return lifeCount;
    }

    public Animal(Cell location) {
        super(location);
    }

    abstract public Color getColor();

//    abstract void decreaseLifeCount();

//    abstract void resetLifeCount();
}
