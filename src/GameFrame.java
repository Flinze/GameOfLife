package ca.bcit.comp2526.a2c;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;

/**
 * GameFrame.
 *
 * Frame of program.
 * 
 * @author Felix Lin
 * @version 2.0
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame {

    private final World world;

    /**
     * Constructs an object of type GameFrame.
     * 
     * @param world a World.
     *
     */
    public GameFrame(final World world) {
        this.world = world;
    }

    /**
     * Initializes the GameFrame.
     */
    public void init() {
        setTitle("Assignment 2a");
        setLayout(new GridLayout(world.getRowCount(), world.getColumnCount()));

        for (int row = 0; row < world.getRowCount(); row++) {
            for (int col = 0; col < world.getColumnCount(); col++) {
                add(world.getCellAt(row, col));
                world.getCellAt(row, col).setBorder(
                        BorderFactory.createMatteBorder(
                                1, 1, 0, 0, Color.BLACK));
            }
        }

        addMouseListener(new TurnListener(this));
    }

    /**
     * Generates and repaints a new turn in world.
     */
    public void takeTurn() {
        world.update();
        repaint();
    }
}
