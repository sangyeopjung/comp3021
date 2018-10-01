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

    @Test
    void shouldThrowUnknownElementException() {
        char[][] badMap = {
                {'#', '#', '#', '#', '#', '#'},
                {'#', '.', ')', '.', '.', '#'},
                {'.', '@', '.', 'a', 'b', '#'},
                {'#', '.', '.', 'A', 'B', '#'},
                {'#', '#', '#', '#', '#', '#'},
        };

        assertThrows(UnknownElementException.class, () -> m.initialize(rows, cols, badMap));
    }

    @Test
    void shouldThrowInvalidNumberOfPlayersExceptionWhenNoPlayers() {
        char[][] badMapNoPlayers = {
                {'#', '#', '#', '#', '#', '#'},
                {'#', '.', '.', '.', '.', '#'},
                {'.', '.', '.', 'a', 'b', '#'},
                {'#', '.', '.', 'A', 'B', '#'},
                {'#', '#', '#', '#', '#', '#'},
        };

        m = new Map();
        assertThrows(InvalidNumberOfPlayersException.class, () -> m.initialize(rows, cols, badMapNoPlayers));
    }

    @Test
    void shouldThrowInvalidNumberOfPlayersExceptionWhenMultiplePlayers() {
        char[][] badMapTooMany = {
                {'#', '#', '#', '#', '#', '#'},
                {'#', '.', '.', '.', '.', '#'},
                {'.', '@', '@', 'a', 'b', '#'},
                {'#', '.', '.', 'A', 'B', '#'},
                {'#', '#', '#', '#', '#', '#'},
        };

        m = new Map();
        assertThrows(InvalidNumberOfPlayersException.class, () -> m.initialize(rows, cols, badMapTooMany));
    }

    @Test
    void shouldMovePlayer() {
        assertTrue(m.movePlayer(Map.Direction.DOWN));
        assertTrue(m.movePlayer(Map.Direction.RIGHT));
        assertTrue(m.movePlayer(Map.Direction.RIGHT));
        assertTrue(m.movePlayer(Map.Direction.UP));
    }

    @Test
    void shouldNotMovePlayer() throws InvalidMapException {
        char[][] map = {
                {'#', '#', 'd', '#', '#', '#'},
                {'#', '.', 'd', '.', '.', '#'},
                {'a', 'a', '@', 'c', 'c', '#'},
                {'#', '.', 'b', 'A', 'B', '#'},
                {'#', '#', 'b', '#', '#', '#'},
        };

        m.initialize(rows, cols, map);
        assertFalse(m.movePlayer(Map.Direction.LEFT));
        assertFalse(m.movePlayer(Map.Direction.RIGHT));
        assertFalse(m.movePlayer(Map.Direction.UP));
        assertFalse(m.movePlayer(Map.Direction.DOWN));
    }
}