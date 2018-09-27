package Map.Occupiable;

import Map.Cell;
import Map.Occupant.Occupant;

import java.util.Optional;

/**
 * A floor tile that can be occupied by a player or crates
 */
public class Tile extends Cell implements Occupiable {

    private Occupant occupant;

    @Override
    public void setOccupant(Occupant o) {
        this.occupant = o;
    }

    @Override
    public void removeOccupant() {
        this.occupant = null;
    }

    @Override
    public Optional<Occupant> getOccupant() {
        return Optional.ofNullable(occupant);
    }

    /**
     * @return If the occupant is present, returns the occupant's representation. Otherwise, returns the empty tile
     * character (a period)
     */
    @Override
    public char getRepresentation() {
        return Optional.ofNullable(occupant).isPresent()
                ? (Optional.ofNullable(occupant).get()).getRepresentation()
                : '.';
    }
}