package Map.Occupant;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {

    @Test
    void getRepresentation() {
        Player p = new Player(0, 0);
        assertEquals('@', p.getRepresentation());
    }
}