package Map.Occupant;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CrateTest {

    @Test
    void getRepresentation() {
        Crate c = new Crate(0, 0, 'a');
        assertEquals('a', c.getRepresentation());
    }
}