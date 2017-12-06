package ca.bcit.comp2526.a2c;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * GameFrame.
 *
 * Frame of program.
 * @author Felix Lin
 * @version 2.0
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame {

    private World world;
    private JButton saveBtn;
    private JButton loadBtn;
    private JPanel gridPanel = new JPanel();

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
        setTitle("Assignment 2c");
        gridPanel.setLayout(
                new GridLayout(world.getRowCount(), world.getColumnCount()));

        for (int row = 0; row < world.getRowCount(); row++) {
            for (int col = 0; col < world.getColumnCount(); col++) {
                gridPanel.add(world.getCellAt(row, col));
                world.getCellAt(row, col).setBorder(
                        BorderFactory.createMatteBorder(
                                1, 1, 0, 0, Color.BLACK));
            }
        }

        this.add(gridPanel);
        JPanel b = new JPanel();
        saveBtn = new JButton("Save");
        loadBtn = new JButton("Load");
        b.add(saveBtn);
        b.add(loadBtn);
        this.add(b, BorderLayout.SOUTH);
        addMouseListener(new TurnListener(this));
        saveBtn.addActionListener(new ButtonListener());
        loadBtn.addActionListener(new ButtonListener());
        
        repaint();
    }

    /**
     * Generates and repaints a new turn in world.
     */
    public void takeTurn() {
        world.updateLinkedList();
        repaint();
    }

    /**
     * Fills the game panel with the cells.
     */
    public void fillGamePanel() {
        for (int row = 0; row < world.getRowCount(); row++) {
            for (int col = 0; col < world.getColumnCount(); col++) {
                add(world.getCellAt(row, col));
                world.getCellAt(row, col).setBorder(
                        BorderFactory.createMatteBorder(
                                1, 1, 0, 0, Color.BLACK));
            }
        }
        for (int row = 0; row < world.getRowCount(); row++) {
            for (int col = 0; col < world.getColumnCount(); col++) {
                gridPanel.add(world.getCellAt(row, col));
                world.getCellAt(row, col).setBorder(
                        BorderFactory.createMatteBorder(
                                1, 1, 0, 0, Color.BLACK));
            }
        }
    }

    /**
     * Action Listener for control panel.
     *
     * @author Felix Lin
     * @version 1.0
     */
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            if (source == loadBtn) {
                load();
            }

            if (source == saveBtn) {
                save();
            }

        }
    }

    /**
     * Saves the current state of the game, and then
     * prompts to save the file location.
     */
    public void save() {
        final JFileChooser fileChooser =
                new JFileChooser(System.getProperty("user.dir"));
        try {
            fileChooser.setSelectedFile(new File("save.ser"));
            int saveFile = fileChooser.showSaveDialog(GameFrame.this);

            if (saveFile == JFileChooser.APPROVE_OPTION) {
                File saveGame = fileChooser.getSelectedFile();
                FileOutputStream fileOut = new FileOutputStream(saveGame);
                ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
                objOut.writeObject(world);
                objOut.close();
                fileOut.close();
                System.out.println("Game saved.");
            } else {
                return;
            }
        } catch (IOException i) {
            i.printStackTrace();
        }

    }

    /**
     * Loads a local file from a previous game, prompts user to
     * select a file.
     */
    public void load() {
        World loadGame;
        final JFileChooser fileChooser =
                new JFileChooser(System.getProperty("user.dir"));
        try {
            int fileOpened = fileChooser.showOpenDialog(GameFrame.this);

            if (fileOpened == JFileChooser.APPROVE_OPTION) {
                FileInputStream fileInput =
                        new FileInputStream(fileChooser.getSelectedFile());
                ObjectInputStream objInput = new ObjectInputStream(fileInput);
                loadGame = (World) objInput.readObject();
                objInput.close();
                fileInput.close();
            } else {
                return;
            }
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException x) {
            x.printStackTrace();
            return;
        }

        world = loadGame;
        gridPanel.removeAll();
        fillGamePanel();
        world.reinit();
        setWorld(world);
        GameFrame.this.repaint();
        System.out.println("Game loaded.");
    }

    /**
     * Sets the world.
     * @param world sets the world.
     */
    public void setWorld(World world) {
        this.world = world;
    }

}
