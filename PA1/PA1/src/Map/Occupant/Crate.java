package Map.Occupant;

/**
 * A class representing a crate that can be pushed around on the tiles
 */
public class Crate extends Occupant {
    private char ID;

    /**
     * @param r  The row position of the crate
     * @param c  The column position of the crate
     * @param id The lowercase letter ID
     */
    public Crate(int r, int c, char id) {
        super(r, c);
        ID = id;
    }

    /**
     * @return The textual representation of the crate. Use the lowercase letter ID.
     */
    @Override
    public char getRepresentation() {
        return getID();
    }

    /**
     * @return The lowercase letter of this crate
     */
    public char getID() {
        return ID;
    }
}
