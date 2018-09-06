package Map.Occupiable;

import Map.Occupant.Crate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DestTileTest {
    private DestTile d;
    private Crate correctCrate;
    private Crate wrongCrate;

    @BeforeEach
    void setUp() {
        d = new DestTile('A');
        correctCrate = new Crate(0, 0, 'a');
        wrongCrate = new Crate(0, 0, 'b');
    }

    @Test
    void isCompletedSuccess() {
        d.setOccupant(correctCrate);
        assertTrue(d.isCompleted());
    }

}