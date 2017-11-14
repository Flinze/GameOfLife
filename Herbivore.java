package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.util.ArrayList;

import static ca.bcit.comp2526.a2a.RandomGenerator.nextNumber;

/**
 * Herbivore.
 *
 * Is an entity, part of the world
 * and can be represented as a cell.
 *
 * @author  Felix Lin
 * @version 1.0
 */
public class Herbivore extends Entity {

    private Color cellColour;

    private Cell cell;

    private final int totalLives = 10;

    private int lifeCount = totalLives;

    /**
     * Constructs a herbivore.
     *
     * @param location the location of the cell.
     */
    public Herbivore(Cell location) {
        super(location);
        cell = location;
        init();
    }

    /**
     * Sets the background to be yellow.
     */
    public void init() {
        cellColour = Color.YELLOW;
        setLifeCount(totalLives);
    }

    /**
     * Puts the herbivore on the specified cell.
     * @param location the location of the cell to be
     *                 placed on.
     */
    public void setCell(Cell location) {
        this.cell = location;
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
     * Swaps the cell that is passed in with
     * the current cell.
     *
     * @param newCell the new Cell to be swapped with.
     * @param randIndex A index chosen at random used to swap.
     * @param cellList An ArrayList holding values that are used to swap.
     */
    private void swapCell(Cell newCell, int randIndex,
                          ArrayList<Cell> cellList) {
        newCell.setEntity(this);
        cell.setEntity(new Empty(cell));
        setCell(cellList.get(randIndex));
    }

    /**
     * Moves the herbivore one cell.
     */
    public void move() {
        ArrayList<Cell> adjList = cell.getAdjacentCells();
        ArrayList<Cell> edibleList = getPlants(adjList);
        ArrayList<Cell> emptyList = getEmpty(adjList);

        int numEmptyCell = emptyList.size();
        int numAdjCell = adjList.size();
        int numEdibleCell = edibleList.size();
        int randIndex = nextNumber(numAdjCell);

        if (numEdibleCell > 0) {
            randIndex = nextNumber(numEdibleCell);
            Cell tmp = edibleList.get(randIndex);
            tmp.setEntity(this);
            cell.setEntity(new Empty(cell));
            setCell(edibleList.get(randIndex));
            resetLifeCount(totalLives);
        } else {
            randIndex = nextNumber(numEmptyCell);
            Cell tmp = emptyList.get(randIndex);
            swapCell(tmp, randIndex, emptyList);
            decreaseLifeCount();
            checkLifeCount(getLifeCount(), cell);
        }
        setMoved();
    }

}
