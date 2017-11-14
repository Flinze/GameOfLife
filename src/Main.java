package ca.bcit.comp2526.a2a;

// Import packages
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * Main.
 * Drives the program.
 * When user clicks on game window, a turn passes.
 *
 * @author  Felix Lin
 * @version 1.0
 */
public final class Main {

    private static final Toolkit TOOLKIT;

    static {
        TOOLKIT = Toolkit.getDefaultToolkit();
    }

    /**
     * Disallows the creation of any Main objects.
     */
    private Main() {
    }

    /**
     * Drives the program.
     *
     * @param argv as String[]
     */
    public static void main(final String[] argv) {
        final int columns = 25;
        final int rows = 25;

        final GameFrame frame;
        final World world;

        RandomGenerator.reset();
        world = new World(rows, columns);
        world.init();
        frame = new GameFrame(world);
        position(frame);
        frame.init();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Sets size of frame.
     *
     * @param frame as GameFrame
     */
    private static void position(final GameFrame frame) {
        final Dimension size;
        final float widthPercent = 0.80f;
        final float heightPercent = 0.80f;

        size = calculateScreenArea(widthPercent, heightPercent);
        frame.setSize(size);
        frame.setLocation(centreOnScreen(size));
    }

    /**
     * Creates Point object as a function of screensize.
     *
     * @param size as Dimension
     * @return new Point
     */
    private static Point centreOnScreen(final Dimension size) {
        final Dimension screenSize;

        if (size == null) {
            throw new IllegalArgumentException("Size cannot be null");
        }

        screenSize = TOOLKIT.getScreenSize();

        return (new Point((screenSize.width - size.width)
                / 2, (screenSize.height - size.height) / 2));
    }

    /**
     * Calculates screen area.
     *
     * @param widthPercent as float
     * @param heightPercent as float
     * @return area as Dimension
     */
    private static Dimension calculateScreenArea(final float widthPercent,
                                                 final float heightPercent) {
        final Dimension screenSize;
        final Dimension area;
        final int width;
        final int height;
        final int size;
        final float maxWidthPercent = 100.0f;
        final float minWidthPercent = 0.0f;

        if ((widthPercent <= minWidthPercent)
                || (widthPercent > maxWidthPercent)) {
            throw new IllegalArgumentException("widthPercent cannot be "
                    + "<= 0 or > 100 - got: " + widthPercent);
        }

        if ((heightPercent <= minWidthPercent)
                || (heightPercent > maxWidthPercent)) {
            throw new IllegalArgumentException("heightPercent cannot be "
                    + "<= 0 or > 100 - got: " + heightPercent);
        }

        screenSize = TOOLKIT.getScreenSize();
        width = (int) (screenSize.width * widthPercent);
        height = (int) (screenSize.height * heightPercent);
        size = Math.min(width, height);
        area = new Dimension(size, size);

        return (area);
    }
}
