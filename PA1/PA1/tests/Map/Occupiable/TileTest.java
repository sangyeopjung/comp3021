package Map.Occupiable;

import Map.Occupant.Crate;
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
}