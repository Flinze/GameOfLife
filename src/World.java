package ca.bcit.comp2526.a2c;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * World.
 *
 * The world where the game of life starts.
 * 
 * @author Felix Lin
 * @version 2.0
 *
 */
public class World implements Serializable {

    private static final int RANDOM_GEN_LIMIT = 100;

    private static final int HERB_PROB = 80;

    private static final int PLANT_PROB = 50;

    private static final int CARN_PROB = 40;

    private static final int OMNI_PROB = 32;

    private int rows;

    private int columns;

    private Cell[][] grid;
  
    /**
     * Constructs a World object.
     * 
     * @param rows Number of rows the world grid will have.
     * @param columns Number of columns that the world will have.
     */
    public World(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new Cell[rows][columns];
    }
    
    /**
     * Initializes the World by populating it with different life forms.
     */
    public void init() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                grid[i][j] = new Cell(this, i, j);
                int randNum = RandomGenerator.nextNumber(RANDOM_GEN_LIMIT);

                if (randNum >= HERB_PROB) {
                    grid[i][j].setEntity(
                            new Herbivore(grid[i][j]));                    
                } else if (randNum >= PLANT_PROB) {
                    grid[i][j].setEntity(new Plant(grid[i][j]));
                } else if (randNum >= CARN_PROB) {
                    grid[i][j].setEntity(new Carnivore(grid[i][j]));
                } else if (randNum >= OMNI_PROB) {
                    grid[i][j].setEntity(new Omnivore(grid[i][j]));
                }
                grid[i][j].init();
            }
        }
        getAllAdjCells();
    }

    /**
     * Returns the cell at the specified row and column.
     *
     * @param row
     *          The row index of the cell.
     * @param column
     *          The column of the cell.
     * @return The cell at the specified indices.
     */
    Cell getCellAt(int row, int column) {
        return grid[row][column];
    }

    /**
     * Returns the number of rows in the world grid.
     *
     * @return the number of rows.
     */
    public int getRowCount() {
        return rows;
    }

    /**
     * Returns the number of columns in the world grid.
     *
     * @return the number of columns.
     */
    public int getColumnCount() {
        return columns;
    }

    /**
     * Updates the world by taking turns, and flagging
     * the life form if it has taken a turn.
     */    
    public void update() {
        List<Lifeform> lifeforms = new ArrayList<>();

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                if (grid[i][j].getEntity() != null) {
                    lifeforms.add(grid[i][j].getEntity());
                }
            }
        }
        for (Lifeform l : lifeforms) {
            if (!l.isTurnTaken()) {
                l.takeTurn();
            }
        }
        for (Lifeform l : lifeforms) {
            l.setTurnTaken(false);
        }
    }

    /**
     * Has each cell collect an array of their neighbouring cells
     *      and store it as a data member.
     */
    private void getAllAdjCells() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                grid[i][j].getAdjacentCells();
            }
        }
    }

    /**
     * Updates the world by taking turns
     * using a doubly linked list.
     */
    public void updateLinkedList() {
        DoubleLinkedList<Lifeform> lifeforms = new DoubleLinkedList<>();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                if (!grid[i][j].isEmpty()) {
                    try {
                        lifeforms.addToEnd(grid[i][j].getEntity());
                    } catch (CouldNotAddException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        Iterator<Lifeform> iterator = lifeforms.iterator();
        while (iterator.hasNext()) {
            Lifeform l = iterator.next();
            if (!l.isTurnTaken()) {
                l.takeTurn();
            }
        }
        iterator = lifeforms.iterator();

        while (iterator.hasNext()) {
            Lifeform l = iterator.next();
            l.setTurnTaken(false);
        }
    }

    /**
     * Reinitializes the world following being loaded from a save state.
     */
    public void reinit() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                if (!grid[i][j].isEmpty()) {
                    grid[i][j].getEntity().setLocation(grid[i][j]);
                }
                grid[i][j].init();
            }
        }
    }

}
