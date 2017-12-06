package ca.bcit.comp2526.a2c;

import java.awt.Color;

/**
 * A Carnivore type Lifeform that eats Herbivores and Omnivores.
 * 
 * @author Felix Lin
 * @version 2.0
 */
public class Carnivore extends Lifeform {

    private static final int CARN_LIVES = 7;

    private static final int CARN_REQ_MATES = 1;

    private static final int CARN_REQ_FOOD = 2;

    private static final int CARN_REQ_EMPTY = 2;

    /**
     * Creates a Carnivore object.
     * 
     * @param location
     *          the location of the cell.
     */
    public Carnivore(Cell location) {
        super(location);
        setColor(Color.MAGENTA);
        setEmptyCellsRequired(CARN_REQ_EMPTY);
        setMateCellsRequired(CARN_REQ_MATES);
        setFoodCellsRequired(CARN_REQ_FOOD);
        setLifeCount(CARN_LIVES);
        setType(LifeformType.CARNIVORE);
    }


    /**
     * Gives birth to new lifeform.
     *
     * @param birthLocation
     *              starting location of the new lifeform.
     *
     * @return a lifeform, specifically a carnivore.
     */
    Lifeform giveBirth(Cell birthLocation) {
        return new Carnivore(birthLocation);
    }

    /**
     * Resets the lifeCount.
     */
    void resetLifeCount() {
        setLifeCount(CARN_LIVES);
    }

    /**
     * Checks if the food is edible.
     *
     * @param food
     *          lifeform this object is trying to eat
     *
     * @return if the food is edible.
     */
    public boolean isEdible(Lifeform food) {
        LifeformType t = food.getType();
        return (t == LifeformType.OMNIVORE || t == LifeformType.HERBIVORE);
    }
    
}
