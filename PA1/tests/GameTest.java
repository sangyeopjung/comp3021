import Exceptions.InvalidMapException;
import Exceptions.UnknownElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InaccessibleObjectException;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    Game g;

    @BeforeEach
    void setUp() throws InvalidMapException {
        g = new Game();
        g.loadMap("tests/goodmap.txt");
    }

    @Test
    void loadMapFailure() throws InvalidMapException {
        g.loadMap("asdlfkjasdlkfj"); //should not throw exception
        assertThrows(InvalidMapException.class, () -> g.loadMap("tests/badmap.txt"));
    }

    @Test
    void shouldNotWinGame() {
        assertFalse(g.isWin());
    }

    @Test
    void shouldWinGame() {
        g.makeMove('s');
        g.makeMove('w');
        g.makeMove('d');
        g.makeMove('d');
        g.makeMove('d');
        g.makeMove('d');
        g.makeMove('d');
        g.makeMove('d');
        g.display();
        assertTrue(g.isWin());
    }

    @Test
    void shouldNotBeDeadlocked() {
        assertFalse(g.isDeadlocked());
    }

    @Test
    void shouldBeDeadlocked() throws InvalidMapException {
        g.loadMap("tests/deadlockmap.txt");
        g.display();
        assertTrue(g.isDeadlocked());
    }

    @Test
    void shouldMove() {
        assertTrue(g.makeMove('d'));
    }

    @Test
    void shouldNotMove() {
        assertFalse(g.makeMove('a'));
        assertFalse(g.makeMove('v'));
    }

    @Test
    void shouldReinitialize() {
        assertTrue(g.makeMove('r'));
    }

    @Test
    void shouldThrowInvalidColSizeMapException() throws InvalidMapException {
        assertThrows(InvalidMapException.class, () -> g.loadMap("tests/badcolsizemap.txt"));
    }

    @Test
    void shouldThrowInvalidTooManyRowSizeMapException() throws InvalidMapException {
        assertThrows(InvalidMapException.class, () -> g.loadMap("tests/badrowmanysizemap.txt"));
    }

    @Test
    void shouldThrowInvalidTooFewRowSizeMapException() throws InvalidMapException {
        assertThrows(InvalidMapException.class, () -> g.loadMap("tests/badrowfewsizemap.txt"));
    }
}