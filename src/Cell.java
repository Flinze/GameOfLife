package ca.bcit.comp2526.a2c;


import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Cell.
 *
 * A Cell represents an area where the lifeform
 * is placed in.
 * 
 * @author Felix Lin
 * @version 2.0
 */
@SuppressWarnings("serial")
public class Cell extends JPanel {

    private World world;

    private int row;

    private int column;

    private Color color;

    private Lifeform entity;


    /**
     * Constructs a Cell.
     * 
     * @param world
     *          The world where the cell exists.
     * @param row
     *          The row of where the cell exists.
     * @param column
     *          The column of where the cell exists.
     */
    public Cell(World world, int row, int column) {
        this.row = row;
        this.column = column;
        this.world = world;
    }
    
    /**
     * Initializes the cell by setting a background
     * color.
     */
    public void init() {
        if (entity == null) {
            this.setBackground(Color.WHITE);
        } else {
            this.setBackground(color);
        }
    }
    
    /**
     * Returns the Content object inhabiting the Cell.
     * @return
     *      This Cell's Content entity.
     */
    public Lifeform getEntity() {
        return entity;
    }
    
    /**
     * Sets content object as entity of this Cell.
     * @param l
     *          new entity.
     */
    public void setEntity(Lifeform l) {
        this.entity = l;
        if (entity != null) {
            entity.init();
        }
    }

    /**
     * Returns the adjacent Cells.
     * Corners return 3 Cells.
     * Sides return 5 Cells.
     * All others return 8 Cells.
     *
     * @return ArrayList of adjacent cells.
     */
    public ArrayList<Cell> getAdjacentCells() {
        ArrayList<Cell> adjacentCells = new ArrayList<>();

        for (int i = this.row - 1; i <= this.row + 1; i++) {
            for (int j = this.column - 1; j <= this.column + 1; j++) {
                if (j == this.column && i == this.row) {
                    continue;
                }
                if (i >= 0 && j >= 0
                        && j < world.getRowCount()
                        && i < world.getColumnCount()) {
                    adjacentCells.add(world.getCellAt(i, j));
                }
            }
        }
        return adjacentCells;
    }

    /**
     * Sets the background colour of the cell.
     * @param color
     *          New background colour to set.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Checks if the cell is empty.
     * 
     * @return
     *      True, if cell is considered empty.
     */
    public boolean isEmpty() {
        return entity == null;
    }

}
