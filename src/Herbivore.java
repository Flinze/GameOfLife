package ca.bcit.comp2526.a2c;

import java.awt.Color;

/**
 * A Herbivore type Lifeform that eats Plants and dies.
 * 
 * @author Felix Lin
 * @version 2.0
 */
public class Herbivore extends Lifeform {
    
    private static final int HERB_LIVES = 10;

    private static final int HERB_REQ_MATES = 2;

    private static final int HERB_REQ_SPACE = 1;

    private static final int HERB_REQ_FOOD = 2;

    /**
     * Constructs a herbivore.
     * 
     * @param location
     *          the location of the cell.
     */
    public Herbivore(Cell location) {
        super(location);
        setColor(Color.yellow);
        setEmptyCellsRequired(HERB_REQ_SPACE);
        setMateCellsRequired(HERB_REQ_MATES);
        setFoodCellsRequired(HERB_REQ_FOOD);
        setLifeCount(HERB_LIVES);
        setType(LifeformType.HERBIVORE);
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
        
        return new Herbivore(birthLocation);
    }


    /**
     * Resets the hunger.
     */
    void resetLifeCount() {
        setLifeCount(HERB_LIVES);
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
        return (t == LifeformType.PLANT);
    }

}
