//package ca.bcit.comp2526.a2a;
//
//import java.awt.*;
//import java.util.ArrayList;
//
//import static ca.bcit.comp2526.a2a.RandomGenerator.nextNumber;
//
///**
// * Carnivore.
// *
// * @author Felix Lin
// * @version 1.0
// */
//public class Carnivore extends Entity {
//    private Color cellColour;
//
//    private Cell cell;
//
//    private final int totalLives = 7;
//
//    private int lifeCount = totalLives;
//
//    private boolean isMoved;
//    /**
//     * Constructs a herbivore.
//     *
//     * @param location the location of the cell.
//     */
//    public Carnivore(Cell location) {
//        super(location);
//        cell = location;
//        init();
//    }
//
//    /**
//     * Sets the background to be magenta.
//     */
//    public void init() {
//        cellColour = Color.MAGENTA;
//    }
//
//    /**
//     * Puts the carnivore on the specified cell.
//     * @param location the location of the cell to be
//     *                 placed on.
//     */
//    private void setCell(Cell location) {
//        this.cell = location;
//    }
//
//    /**
//     * Swaps the cell that is passed in with
//     * the current cell.
//     *
//     * @param newCell the new Cell to be swapped with.
//     * @param randIndex A index chosen at random used to swap.
//     * @param cellList An ArrayList holding values that are used to swap.
//     */
//    private void swapCell(Cell newCell, int randIndex,
//                          ArrayList<Cell> cellList) {
//        newCell.setEntity(this);
//        cell.setEntity(new Entity(cell));
//        setCell(cellList.get(randIndex));
//    }
//
//    /**
//     * Checks if carnivore has moved yet.
//     *
//     * @return true if herbivore has moved.
//     */
//    public boolean hasMoved() {
//        if (isMoved) {
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * Resets the move boolean.
//     */
//    public void resetMoved() {
//        isMoved = false;
//    }
//
//    /**
//     * Moves the herbivore one cell.
//     */
//    public void move() {
////        ArrayList<Cell> adjList = cell.getAdjacentCells();
////        ArrayList<Cell> edibleList = getPlants(adjList);
////        ArrayList<Cell> emptyList = getEmpty(adjList);
////
////        int numEmptyCell = emptyList.size();
////        int numAdjCell = adjList.size();
////        int numEdibleCell = edibleList.size();
////        int randIndex = nextNumber(numAdjCell);
////
////        if (numEdibleCell > 0) {
////            randIndex = nextNumber(numEdibleCell);
////            Cell tmp = edibleList.get(randIndex);
////            tmp.setEntity(this);
////            cell.setEntity(new Entity(cell));
////            setCell(edibleList.get(randIndex));
////            resetLifeCount();
////        } else {
////            randIndex = nextNumber(numEmptyCell);
////            Cell tmp = emptyList.get(randIndex);
////            swapCell(tmp, randIndex, emptyList);
////            decreaseLifeCount();
////            checkLifeCount();
////        }
//        isMoved = true;
//    }
//
//    /**
//     * Checks the life count of the herbivore.
//     * If the life count reaches 0, the herbivore dies.
//     */
//    private void checkLifeCount() {
//        if (lifeCount == 0) {
//            cell.removeCell(this.cell);
//        }
//    }
//
//    /**
//     * Resets the life count to 7.
//     */
//    private void resetLifeCount() {
//        lifeCount = totalLives;
//    }
//
//    /**
//     * Decrements the life count.
//     */
//    private void decreaseLifeCount() {
//        lifeCount--;
//    }
//
//    /**
//     * Gets the colour of the cell.
//     *
//     * @return the cell colour.
//     */
//    public Color getColor() {
//        return cellColour;
//    }
//}
