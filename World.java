package ca.bcit.comp2526.a2a;

import java.util.ArrayList;

import static ca.bcit.comp2526.a2a.RandomGenerator.nextNumber;

/**
 * World.
 *
 * Cells interact within World.
 *
 * @author  Felix Lin
 * @version 1.0
 */
public class World {

    private int turnCount;

    private int rows;

    private int columns;

    private Cell[][] cellGrid;

    private Cell cell;

    private final int herbiRange = 80;

    private final int plantRange = 50;

    private final int carniRange = 40;

    private final int omniRange = 32;

    private final int randGenRange = 100;

    /**
     * Constructs the world.
     *
     * @param rows as int
     * @param columns as int
     */
    public World(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    /**
     * Puts Cells on the world.
     * Adds appropriate number of Herbivores and Plants.
     */
    public void init() {
        cellGrid = new Cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cell = new Cell(this, i, j);
                cell.setEntity(makeEntity(cell));
                cellGrid[i][j] = cell;
            }
        }
    }

    /**
     * Retrieves the requested Cell from the specified location
     * in the World.
     *
     * @param row as int
     * @param col as int
     * @return cell as Cell
     */
    public Cell getCellAt(int row, int col) {
        return cellGrid[row][col];
    }

    /**
     * Creates an entity such as a plant or
     * herbivore.
     *
     * @param location the location of the cell.
     * @return the entity type.
     */
    public Entity makeEntity(Cell location) {
        int num = nextNumber(randGenRange);

        if (num >= herbiRange) {
            return new Herbivore(location);
        } else if (num >= plantRange) {
            return new Plant(location);
        }
//        else if (num >= carniRange) {
//            return new Carnivore(location);
//        } else if (num >= omniRange) {
//            return new Omnivore(location);
//        }
        return new Empty(location);
    }

    /**
     * Gets all entities in the world.
     *
     * @return An arraylist of entities in the world.
     */
    public ArrayList<Cell> getAllEntities() {
        ArrayList<Cell> allEntities = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cell = cellGrid[i][j];
                if (cell.getCellType() != EntityType.EMPTY) {
                    allEntities.add(cell);
                }
            }
        }
        return allEntities;
    }

    /**
     * Removes dead Herbivores.
     * Checks each plant to see if it seeds.
     * Moves living Herbivores one Cell.
     * Herbivores eat, if possible.
     */
    public void takeTurn() {
        turnCount++;
//        for (int i = 0; i < cellGrid.length; i++) {
//            for (int j = 0; j < cellGrid[i].length; j++) {
//                cell = cellGrid[i][j];
//                if (!(cell.getEntity().hasMoved())) {
//                    cell.getEntity().move();
//                }
//            }
//        }
        update();

        for (int i = 0; i < cellGrid.length; i++) {
            for (int j = 0; j < cellGrid[i].length; j++) {
                cell = cellGrid[i][j];
                cell.getEntity().resetMoved();
            }
        }

        System.out.println("Turn number: " + turnCount);
    }

    /**
     * Acquires all entities. It then checks if they're
     * alive, and then moves them, reproduce, and remove.
     */
    public void update() {
        ArrayList<Cell> entityList = getAllEntities();
        for (Cell entities: entityList) {
            if (!(entities.getEntity().hasMoved())) {
                entities.getEntity().move();
            }

        }
    }

    /**
     * Gets number of rows in World.
     *
     * @return rows as int
     */
    public int getRowCount() {
        return rows;
    }

    /**
     * Gets number of columns in World.
     *
     * @return cols as int
     */
    public int getColumnCount() {
        return columns;
    }
}
