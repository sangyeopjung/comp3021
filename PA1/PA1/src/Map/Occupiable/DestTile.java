package Map.Occupiable;

import Map.Occupant.Crate;
import Map.Occupant.Occupant;

import java.nio.file.DirectoryStream;
import java.util.function.Function;

/**
 * A destination tile. To win the game, we must push the crate with the corresponding ID onto this tile
 */
public class DestTile extends Tile {
    private char destID;

    /**
     * @param destID The destination uppercase char corresponding to a crate with the same lowercase letter
     */
    public DestTile(char destID) {
        this.destID = destID;
    }

    /**
     * @return Whether or not this destination tile has been completed, i.e. a crate with the matching lowercase letter
     * is currently occupying this tile.
     */
    public boolean isCompleted() {
        if (getOccupant().isPresent()
                && getOccupant().get() instanceof Crate) {
            return Character.toUpperCase(((Crate)getOccupant().get())
                    .getID()) == getDestID();
        } else {
            return false;
        }
    }

    /**
     * @return The uppercase letter corresponding to the crate with the matching lowercase letter
     */
    private char getDestID() {
        return destID;
    }

    @Override
    public char getRepresentation() {
        return getOccupant().isPresent()
                ? ((Occupant)getOccupant().get()).getRepresentation()
                : destID;
    }
}
