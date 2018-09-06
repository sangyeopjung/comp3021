package Map;

import Exceptions.InvalidMapException;
import Exceptions.InvalidNumberOfPlayersException;
import Exceptions.UnknownElementException;
import Map.Occupant.Crate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {
    private Map m;

    private int rows = 5;
    private int cols = 6;
    private char[][] goodMap = {
            {'#', '#', '#', '#', '#', '#'},
            {'#', '.', '.', '.', '.', '#'},
            {'.', '@', '.', 'a', 'b', '#'},
            {'#', '.', '.', 'A', 'B', '#'},
            {'#', '#', '#', '#', '#', '#'},
    };

    @BeforeEach
    void setUp() throws InvalidMapException {
        m = new Map();
        m.initialize(rows, cols, goodMap);
    }

    @Test
    void getDestTiles() {
        assertEquals(2, m.getDestTiles().stream().filter(x -> "AB".contains("" + x.getRepresentation())).count());
    }
}