package ca.bcit.comp2526.a2c;

import java.awt.Color;

/**
 * Plant.
 *
 * A plant is-a life form. Can be eaten
 * by Herbivores, and omnivores.
 * 
 * @author Felix Lin
 * @version 2.0
 */
public class Plant extends Lifeform {

    private static final int PLANT_REQ_MATES = 2;

    private static final int PLANT_REQ_EMPTY = 3;
    
    /**
     * Constructs a Plant object.
     * 
     * @param location the plant's starting location.
     */
    public Plant(Cell location) {
        super(location);
        setColor(Color.GREEN);
        setEmptyCellsRequired(PLANT_REQ_EMPTY);
        setMateCellsRequired(PLANT_REQ_MATES);
        setFoodCellsRequired(0);
        setLifeCount(1);
        setType(LifeformType.PLANT);
    }

    /**
     * Gives birth to new lifeform.
     *
     * @param birthLocation
     *              starting location of the new lifeform.
     *
     * @return a lifeform, specifically a plant.
     */
    Lifeform giveBirth(Cell birthLocation) {
        
        return new Plant(birthLocation);
    
    }

    /**
     * Resets the life count.
     */
    void resetLifeCount() {
        setLifeCount(1);
    }

    /**
     * Checks if there are any types that are edible.
     *
     * @param lifeform
     *          lifeform this object is trying to eat.
     *
     * @return if the food is edible.
     */
    public boolean isEdible(Lifeform lifeform) {
        return false;
    }


    /**
     * Empty override method so that the plant does not eat.
     */
    public void move() {
    }

    /**
     * Override method so that the plant does not
     * update its life count since it does not have a
     * life count.
     */
    public void updateLifeCount() {
        resetLifeCount();
    }

}
