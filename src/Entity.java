package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Entity.
 *
 * Entities are any items in the cell.
 * They can range from herbivore to a plant.
 *
 * @author Felix Lin
 * @version 1.0
 */
public abstract class Entity {

    private Color cellColor;

    private boolean isMoved;

    private Cell cell;

    private int lifeCount;

    /**
     * Constructs an entity.
     * @param location The location on where the entity will be made.
     */
    public Entity(Cell location) {
        cell = location;
        init();
    }

    /**
     * Initializes the entity.
     */
    public abstract void init();

    /**
     * Moves the entity.
     */
    public void move() {
    }

    /**
     * Checks for empty cells in the adjacent cells,
     * and stores it into an ArrayList.
     *
     * @param adjList the list of adjacent cells.
     * @return a list of empty cells.
     */
    protected ArrayList<Cell> getEmpty(ArrayList<Cell> adjList) {
        ArrayList<Cell> emptyList = new ArrayList<>();
        for (Cell emptyCell: adjList) {
            if (EntityType.EMPTY == emptyCell.getCellType()) {
                emptyList.add(emptyCell);
            }
        }
        return emptyList;
    }

    /**
     * Checks for herbivores in the adjacent cells,
     * and stores it into an ArrayList.
     *
     * @param adjCell ArrayList of adjacent cells.
     * @return ArrayList of herbivores.
     */
    protected ArrayList<Cell> getHerbivores(ArrayList<Cell> adjCell) {
        ArrayList<Cell> herb = new ArrayList<>();
        for (Cell herbCell: adjCell) {
            if (EntityType.HERBIVORE == herbCell.getCellType()) {
                herb.add(herbCell);
            }
        }
        return herb;
    }

    /**
     * Checks the ArrayList of adjacent cells for edible cells,
     * then returns an ArrayList of edible cells.
     *
     * @param adjCell ArrayList of adjacent cells.
     * @return ArrayList of edible cells.
     */
    protected ArrayList<Cell> getPlants(ArrayList<Cell> adjCell) {
        ArrayList<Cell> food = new ArrayList<>();
        for (Cell edibleCell: adjCell) {
            if (EntityType.PLANT == edibleCell.getCellType()) {
                food.add(edibleCell);
            }
        }
        return food;
    }

    /**
     * Gets the colour of the entity.
     *
     * @return the colour of the entity.
     */
    public abstract Color getColor();

    /**
     * Gets the current amount of lives left.
     *
     * @return the current life count.
     */
    public int getLifeCount() {
        return lifeCount;
    }

    /**
     * Sets the total amount of lives the entity has.
     *
     * @param totalLives the total lives of the entity.
     */
    public void setLifeCount(int totalLives) {
        lifeCount = totalLives;
    }

    /**
     * Decrements the life count.
     */
    public void decreaseLifeCount() {
        lifeCount--;
    }

    /**
     * Checks the life count of the herbivore.
     * If the life count reaches 0, the herbivore dies.
     *
     * @param count The current number of lives left.
     * @param currCell The current ell entity that is being checked.
     */
    public void checkLifeCount(int count, Cell currCell) {
        if (count == 0) {
            currCell.removeCell(currCell);
        }
    }

    /**
     * Sets the total lives of the entity.
     *
     * @param totalLives the total lives of the entity.
     */
    public void resetLifeCount(int totalLives) {
        lifeCount = totalLives;
    }

    /**
     * Resets the move boolean, allowing movement
     * for the next turn.
     */
    public void resetMoved() {
        isMoved = false;
    }

    /**
     * Checks if the cell has moved.
     *
     * @return if the cell moved.
     */
    public boolean hasMoved() {
        if (isMoved) {
            return true;
        }
        return false;
    }

    /**
     * Sets the cell to have moved.
     */
    public void setMoved() {
        isMoved = true;
    }

    public void reproduce(int numMates, int numEmpty, int numFood, int lifeRemaining) {
        ArrayList<Cell> adjList = cell.getAdjacentCells();
        ArrayList<Cell> emptyList = getEmpty(adjList);
        ArrayList<Cell> plantList = getPlants(adjList);
        ArrayList<Cell> herbiList = getHerbivores(adjList);

        int numEmptyCell = emptyList.size();
    }


}
