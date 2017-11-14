package ca.bcit.comp2526.a2a;

//Import packages
import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 * GameFrame.
 * Frame of program.
 *
 * @author  Felix Lin
 * @version 1.0
 */
public class GameFrame extends JFrame {

    private final World world;

    /**
     * Constructs GameFrame object.
     *
     * @param w as World
     */
    public GameFrame(final World w) {
        world = w;
    }

    /**
     * Initiates frame.
     */
    public void init() {
        setTitle("Assignment 2a");
        setLayout(new GridLayout(world.getRowCount(), world.getColumnCount()));

        for (int row = 0; row < world.getRowCount(); row++) {
            for (int col = 0; col < world.getColumnCount(); col++) {
                add(world.getCellAt(row, col));
            }
        }

        addMouseListener(new TurnListener(this));
    }

    /**
     * Generates and repaints a new turn in world.
     */
    public void takeTurn() {
        world.takeTurn();
        repaint();
    }
}
