package ca.bcit.comp2526.a2c;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * TurnListener.
 *
 * An object that listens for mouse clicks, and takes a
 * turn in the world when the mouse is clicked.
 * 
 * @author Felix Lin
 * @version 2.0
 */
public class TurnListener extends MouseAdapter {

    private GameFrame gameFrame;

    /**
     * Constructs a turn listener.
     * 
     * @param game the gameFrame.
     */
    public TurnListener(GameFrame game) {
        this.gameFrame = game;
    }

    /** 
     * Takes a turn in the game frame whenever
     * the mouse is clicked on panel.
     *
     * @param e mouse click event.
     */
    public void mouseClicked(MouseEvent e) {
        gameFrame.takeTurn();
    }
}
