package Map.Occupiable;

import Map.Occupant.Crate;
import Map.Occupant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {
    private Tile t;
    private Crate crate;

    @BeforeEach
    void setUp() {
        t = new Tile();
        crate = new Crate(0, 0, 'a');
    }

    @Test
    void setAndGetOccupant() {
        t.setOccupant(crate);
        assertTrue(t.getOccupant().isPresent());
        assertSame(crate, t.getOccupant().get());
    }

    @Test
    void shouldReturnRepresentation() {
        t.setOccupant(null);
        assertEquals('.', t.getRepresentation());
        t.setOccupant(crate);
        assertEquals('a', t.getRepresentation());
        t.setOccupant(new Player(0, 0));
        assertEquals('@', t.getRepresentation());
    }
}