package ca.bcit.comp2526.a2a;
import java.awt.Color;
import java.util.ArrayList;

import static ca.bcit.comp2526.a2a.RandomGenerator.nextNumber;

/**
 * Plant.
 *
 * A plant is-a Entity, part of the world.
 *
 * @author  Felix Lin
 * @version 1.0
 */
public class Plant extends Entity {

    private Color cellColour;

    private Cell cell;

    private final int minEmptyCells = 3;

    private final int minPlantCells = 2;

    /**
     * Constructor.
     *
     * @param location the location of the cell.
     */
    public Plant(Cell location) {
        super(location);
        cell = location;
        init();
    }

    /**
     * Initializes the plant and sets
     * the cell background to be green.
     */
    public void init() {
        cellColour = Color.GREEN;
    }

    /**
     * Gets the colour of the cell.
     *
     * @return the cell colour.
     */
    public Color getColor() {
        return cellColour;
    }

    /**
     * Puts the plant on the specified cell.
     *
     * @param location the location of the cell to be
     *                 placed on.
     */
    public void setCell(Cell location) {
        this.cell = location;
    }

    /**
     * Seeds a new plant when there are at least 3
     * empty neighbouring Cells and 2 plant neighbouring cells.
     *
     */
    public void move() {
        ArrayList<Cell> adjList = cell.getAdjacentCells();
        ArrayList<Cell> plantList = getPlants(adjList);
        ArrayList<Cell> emptyList = getEmpty(adjList);

        int randIndex;
        int numEmptyCell = emptyList.size();
        int numPlantCell = plantList.size();

        if ((numEmptyCell >= minEmptyCells)
                && (numPlantCell >= minPlantCells)) {
            randIndex = nextNumber(numEmptyCell);
            Cell tmp = emptyList.get(randIndex);
            tmp.setEntity(new Plant(tmp));
            tmp.getEntity().setMoved();
        }
        setMoved();
    }
}
