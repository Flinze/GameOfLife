package ca.bcit.comp2526.a2c;

/**
 * Edible.
 *
 * Classes that implement this interface are edible.
 * 
 * @author Felix Lin
 * @version 2.0
 *
 */
public interface Edible {

    /**
     * Checks whether the entity passed is edible.
     * 
     * @param edible
     *          lifeform this object is trying to eat.
     * @return
     *          true, if the entity is edible.
     */
    boolean isEdible(Lifeform edible);
    
}
