package ca.bcit.comp2526.a2a;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.BorderFactory;

/**
 * Cell.
 *
 * One square in World.
 * Can hold Plant, Herbivore, nothing.
 *
 * @author  Felix Lin
 * @version 1.0
 */
public class Cell extends JPanel {

    private int xCoordinate;

    private int yCoordinate;

    private World world;

    private Color cellColour;

    private Entity entity;

    private Point location;

    private ArrayList<Cell> cellList;

    /**
     * Constructor.
     *
     * @param world as World
     * @param row as int
     * @param column as int
     */
    public Cell(World world, int row, int column) {
        this.world = world;
        xCoordinate = row;
        yCoordinate = column;
        getCell().setCell();
    }

    /**
     * Sets up layout.
     */
    protected void init() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        if (entity != null) {
            setBackground(entity.getColor());
        }
    }

    /**
     * Sets the entity into the cell.
     * @param entity A entity such as a plant or herbivore.
     */
    public void setEntity(Entity entity) {
        this.entity = entity;
        init();
    }

    /**
     * Gets the entity of the Cell.
     *
     * @return the entity of the cell.
     */
    public Entity getEntity() {
        return entity;
    }

    /**
     * Sets the location of the Cell on the World.
     */
    public void setCell() {
        location = new Point(xCoordinate, yCoordinate);
    }

    /**
     * Returns location of Cell on the World.
     *
     * @return point as Point
     */
    public Point getLocation() {
        return location;
    }

    /**
     * Returns the adjacent Cells.
     * Corners return 3 Cells.
     * Sides return 5 Cells.
     * All others return 8 Cells.
     *
     * @return cell[] positions of adjacent Cells
     */
    public ArrayList<Cell> getAdjacentCells() {
        cellList = new ArrayList<>();
        for (int i = location.x - 1; i <= location.x + 1; i++) {
            for (int j = location.y - 1; j <= location.y + 1; j++) {
                if ((i >= 0 && i < world.getRowCount()) && (j >= 0
                        && j < world.getColumnCount())) {
                    if (!(i == location.x && j == location.y)) {
                        cellList.add(world.getCellAt(i, j));
                    }
                }
            }
        }
        return cellList;
    }

    /**
     * Gets Cell type. (incomplete, not sure if
     * should have)
     *
     * @return Cell
     */
    public EntityType getCellType() {
            if (entity.getColor() == Color.YELLOW) {
                return EntityType.HERBIVORE;
            } else if (entity.getColor() == Color.GREEN) {
                return EntityType.PLANT;
            } else if (entity.getColor() == Color.WHITE) {
                return EntityType.EMPTY;
            }
        return EntityType.EMPTY;
    }

    /**
     * Gets the cell.
     *
     * @return the cell.
     */
    public Cell getCell() {
        return this;
    }

    /**
     * Removes Cell inhabitant.
     * Replaces with empty cell.
     *
     * @param cell The cell to be replaced with.
     * @return replaces the with an empty cell.
     */
    protected Cell removeCell(Cell cell) {
        cell.setEntity(new Empty(cell));
        return cell;
    }
}
