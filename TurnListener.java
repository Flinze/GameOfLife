package ca.bcit.comp2526.a2a;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * TurnListener.
 * Implements mouseClicked().
 *
 * @author  Felix Lin
 * @version 1.0
 */
public class TurnListener extends MouseAdapter {

    private GameFrame gameFrame;

    /**
     * Constructor for turn TurnListener.
     * @param game the GameFrame.
     */
    public TurnListener(GameFrame game) {
        gameFrame = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        gameFrame.takeTurn();
    }
}
