package Map;

/**
 * An abstract class representing a position in the world map. Either a tile or a wall
 */
public abstract class Cell {

    /**
     * @return The textual representation of the cell
     */
    public abstract char getRepresentation();
}
