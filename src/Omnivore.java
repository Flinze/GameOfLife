package ca.bcit.comp2526.a2c;

import java.awt.Color;

/**
 * An omnivore is a lifeform that can
 * eat carnivores, herbivores, and plants.
 * It is displayed as a blue cell.
 * 
 * @author Felix Lin
 * @version 2.0
 */
public class Omnivore extends Lifeform {

    private static final int OMNI_LIVES = 2;

    private static final int ONI_REQ_MATES = 1;

    private static final int OMNI_REQ_FOOD = 3;

    private static final int OMNI_REQ_EMPTY = 3;

    /**
     * Constructs an Omnivore type.
     * 
     * @param location the Omnivore's starting location
     */
    public Omnivore(Cell location) {
        super(location);
        setColor(Color.BLUE);
        setEmptyCellsRequired(OMNI_REQ_EMPTY);
        setMateCellsRequired(ONI_REQ_MATES);
        setFoodCellsRequired(OMNI_REQ_FOOD);
        setLifeCount(OMNI_LIVES);
        setType(LifeformType.OMNIVORE);
    }

    /**
     * Gives birth to new lifeform.
     *
     * @param birthLocation
     *              starting location of the new lifeform.
     *
     * @return a lifeform, specifically a omnivore.
     */
    Lifeform giveBirth(Cell birthLocation) {
        return new Omnivore(birthLocation);
    }

    /**
     * Resets the lifeCount.
     */
    void resetLifeCount() {
        setLifeCount(OMNI_LIVES);
        
    }

    /**
     * Checks if the food is edible.
     *
     * @param food
     *          lifeform this object is trying to eat.
     *
     * @return if the food is edible.
     */
    public boolean isEdible(Lifeform food) {
        LifeformType t = food.getType();

        return (t == LifeformType.PLANT || t == LifeformType.HERBIVORE
                || t == LifeformType.CARNIVORE);
    }

}
