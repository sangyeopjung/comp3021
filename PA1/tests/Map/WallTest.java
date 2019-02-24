package Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WallTest {

    @Test
    void
    getRepresentation() {
        Wall w = new Wall();
        assertEquals('#', w.getRepresentation());
    }
}