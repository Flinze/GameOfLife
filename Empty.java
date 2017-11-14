package ca.bcit.comp2526.a2a;

import java.awt.*;

public class Empty extends Entity {
    private Color cellColor;

    private Cell cell;

    public Empty(Cell location) {
        super(location);
        cell = location;
        init();
    }

    public void init() {
        cellColor = Color.WHITE;
    }

    public Color getColor() {
        return cellColor;
    }
}
