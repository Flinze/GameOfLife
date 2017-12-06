package ca.bcit.comp2526.a2c;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static ca.bcit.comp2526.a2c.RandomGenerator.nextNumber;

/**
 * Lifeform.
 *
 * Lifeforms are any items in the cell.
 * They can range from any life organism.
 * 
 * @author Felix Lin
 * @version 2.0
 */
public abstract class Lifeform implements Edible, Serializable {

    private Cell location;

    private Color color;

    private int lifeCount;

    private boolean hasEaten;

    private boolean turnTaken;

    private int emptyCellsRequired;

    private int mateCellsRequired;

    private int foodCellsRequired;

    private LifeformType type;  
    
    /**
     * Constructs a Lifeform.
     *  
     * @param location The location on where the entity will be made.
     */
    public Lifeform(Cell location) {
        this.location = location;
    }

    /**
     * Resets the lifeCount of the lifeform, used when a lifeform
     * has eaten.
     */
    abstract void resetLifeCount();
    
    /**
     * Gives birth to a new lifeform on the specified
     * location.
     * 
     * @param birthLocation location of the new lifeform.
     * @return the new lifeform.
     */
    abstract Lifeform giveBirth(Cell birthLocation);
    
    /**
     * If it meets the requirements, the cell will
     * move, eat, reproduce, and/or die this turn.
     */
    public void takeTurn() {
        if (!isDead()) {
            move();
        }
        if (!isDead()) {
            reproduce();
        }
        setTurnTaken(true);
        updateLifeCount();
    }
    
    /**
     * Decides a location of the cell and moves into it,
     * if possible it will eat the cell.
     */
    public void move() {
        Cell newLocation = chooseNewLocation();
        
        if (location == newLocation) {
            return;
        }

        location.setEntity(null);
        location.init();
        setLocation(newLocation);
        if (newLocation.getEntity() == null) {
            hasEaten = false;
        } else if (isEdible(newLocation.getEntity())) {
            eat(newLocation.getEntity());
        }
        newLocation.setEntity(this);
        newLocation.init();
    }
    
    /**
     * Chooses a cell that's nearby, preferably one that
     * has a cell that is edible.
     * 
     * @return the selected cell to move to.
     */
    protected Cell chooseNewLocation() {
        ArrayList<Cell> adjacentCells = location.getAdjacentCells();

        ArrayList<Cell> foodList = new ArrayList<>();
        ArrayList<Cell> emptyList = new ArrayList<>();

        for (Cell cell: adjacentCells) {
            Lifeform l = cell.getEntity();
            if (l == null) {
                emptyList.add(cell);
            } else if (isEdible(l)) {
                foodList.add(cell);
            }
        }

        if (emptyList.isEmpty() && foodList.isEmpty()) {
            return location;
        }
        if (foodList.isEmpty()) {
            int randNum = nextNumber(emptyList.size());
            return emptyList.get(randNum);
        } else {
            int randNum = nextNumber(foodList.size());
            return foodList.get(randNum);
        }
    }
    
    /**
     * Kills and Eats the lifeform sets the eaten flag.
     * 
     * @param food lifeform to be eaten.
     */
    protected void eat(Lifeform food) {
        food.die();
        hasEaten = true;
    }
    
    /**
     * Checks whether this Lifeform has ran out of lives.
     * 
     * @return true if lifeform hasn't eaten within the turns.
     */
    protected boolean isDead() {
        return this.lifeCount <= 0;
    }

    /**
     * Kills the current Lifeform.
     */
    protected void die() {
        location.setEntity(null);
        setTurnTaken(true);
        location.init();
        this.location = null;
    }
    
    /**
     * Checks if the Lifeform has eaten this turn and either
     * resets lifeCount or decrease the lifeCount counter and kills the
     * creature if it runs out of lives.
     */
    public void updateLifeCount() {
        if (hasEaten) {
            resetLifeCount();
        } else {
            lifeCount--;
        }
        this.hasEaten = false;
        if (isDead()) {
            die();
        }
    }
    
    /**
     * Checks conditions for reproduction and if passed creates
     * a new Lifeform object at random potential location.
     */
    protected void reproduce() {
        if (checkReproduceable(getMateCellsRequired(), getEmptyCellsRequired(),
                getFoodCellsRequired())) {
            Cell birthDestination = chooseBirthCell();

            Lifeform l = giveBirth(birthDestination);
            birthDestination.setEntity(l);
            birthDestination.init();
            
        }
    }
    
    /**
     * Checks if the conditions are met for lifeform to reproduce.
     * 
     * @param numReqMates
     *          number of nearby mating partners required to reproduce.
     * @param numReqEmpty
     *          number of neighbouring empty cells required to reproduce.
     * @param numReqFood
     *          number of neighbouring food cells required to reproduce.
     * @return
     *          true if conditions met for reproduction.
     */
    protected boolean checkReproduceable(int numReqMates, int numReqEmpty,
                                         int numReqFood) {
        ArrayList<Cell> adjacentCells = location.getAdjacentCells();
        int mateCount = 0;
        int emptyCellCount = 0;
        int foodCount = 0;

        for (int i = 0; i < adjacentCells.size(); i++) {
            Lifeform l = adjacentCells.get(i).getEntity();
            if (adjacentCells.get(i).isEmpty()) {
                emptyCellCount++;
            } else if (this.type == l.getType()) {
                mateCount++;
            } else if (isEdible(l)) {
                foodCount++;
            }
        }

        return (mateCount >= numReqMates
                && emptyCellCount >= numReqEmpty
                && foodCount >= numReqFood);
    }
    
    /**
     * Select a random neighbouring empty cell to give
     * birth to a new Lifeform.
     * 
     * @return the selected cell for the new Lifeform object
     */
    protected Cell chooseBirthCell() {
        ArrayList<Cell> searchArea = location.getAdjacentCells();
        List<Cell> birthLocations = new ArrayList<>();

        for (int i = 0; i < searchArea.size(); i++) {
            if (searchArea.get(i).isEmpty()) {
                birthLocations.add(searchArea.get(i));
            }
        }

        return birthLocations.get(nextNumber(birthLocations.size()));
    } 
    
    /**
     * Sets the background color of the cell.
     */
    public void init() {
        location.setColor(this.color);
    }

    /**
     * Sets the color of the lifeform.
     * 
     * @param color the color.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Checks whether this object has taken an action this turn.
     * 
     * @return the turnTaken
     */
    public boolean isTurnTaken() {
        return turnTaken;
    }

    /**
     * Sets the turnTaken boolean.
     * 
     * @param turnTaken the turnTaken to set.
     */
    public void setTurnTaken(boolean turnTaken) {
        this.turnTaken = turnTaken;
    }
    
    /**
     * Gets the number of neighbouring empty cells required
     * for this Lifeform to reproduce.
     *
     * @return the emptyCellsRequired.
     */
    protected int getEmptyCellsRequired() {
        return emptyCellsRequired;
    }

    /**
     * Sets the number of neighbouring empty cells required
     * for this Lifeform to reproduce.
     * 
     * @param emptyCellsRequired the emptyCellsRequired to set.
     */
    protected void setEmptyCellsRequired(int emptyCellsRequired) {
        this.emptyCellsRequired = emptyCellsRequired;
    }

    /**
     * Gets the number of neighbouring food cells required
     * for the Lifeform to reproduce.
     * 
     * @return the foodCellsRequired.
     */
    protected int getFoodCellsRequired() {
        return foodCellsRequired;
    }

    /**
     * Sets the number of neighbouring cells with food required
     * for this Lifeform to reproduce.
     * 
     * @param foodCellsRequired the foodCellsRequired to set
     */
    protected void setFoodCellsRequired(int foodCellsRequired) {
        this.foodCellsRequired = foodCellsRequired;
    }

    /**
     * Sets the current Cell that this object exists in.
     *
     * @param location the location.
     */
    public void setLocation(Cell location) {
        this.location = location;
    }

    /**
     * Sets the current lifeCount level of this Lifeform.
     *
     * @param lifeCount the lifeCount.
     */
    public void setLifeCount(int lifeCount) {
        this.lifeCount = lifeCount;
    }

    /**
     * The enumerated type identifier for this type of Lifeform.
     * 
     * @return the type
     */
    protected LifeformType getType() {
        return type;
    }

    /**
     * Set the enumerated type identifier for this type of Lifeform.
     * 
     * @param type the type to set
     */
    protected void setType(LifeformType type) {
        this.type = type;
    }

    /**
     * Gets the number of neighbouring mates required
     * for this Lifeform to reproduce.
     *
     * @return the mateCellsRequired
     */
    protected int getMateCellsRequired() {
        return mateCellsRequired;
    }

    /**
     * Sets the number of neighbouring mates required
     * for this Lifeform to reproduce.
     *
     * @param mateCellsRequired the mateCellsRequired to set
     */
    protected void setMateCellsRequired(int mateCellsRequired) {
        this.mateCellsRequired = mateCellsRequired;
    }

}
