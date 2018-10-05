
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    private char[][] invalidMap =
            {{'1', '2', '3'},
                    {'1', '3', '5'}};
    private char[][] goodMap =
            {{'1', '2', '3'},
                    {'1', '3', '5'},
                    {'1', '3', '5'}};
    private static String FILENAME = "test.txt";
    private static String FILENAME2 = "test2.txt";
    private static String FILENAME3 = "test3.txt";

    private static Map m = new Map();

    @AfterAll
    public static void cleanupFiles() {
        try {
            Files.deleteIfExists(Paths.get(new File(".").getAbsolutePath() + File.separator + FILENAME));
            Files.deleteIfExists(Paths.get(new File(".").getAbsolutePath() + File.separator + FILENAME2));
            Files.deleteIfExists(Paths.get(new File(".").getAbsolutePath() + File.separator + FILENAME3));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSaveMapSuccess() {
        m.createMap(FILENAME, goodMap);
    }

    @Test
    public void testSaveMapInvalid() {
        assertThrows(IllegalArgumentException.class, () -> m.createMap(FILENAME, invalidMap));
    }

    @Test
    public void testLoadMapSuccess() throws BadMapException {
        m.createMap(FILENAME2, goodMap);
        var ans = m.loadMap(FILENAME2, 2, 2);
        assertTrue(Arrays.deepEquals(ans, goodMap));
    }

    @Test
    public void testLoadBadMap() {
        m.createMap(FILENAME3, goodMap);
        assertThrows(BadMapException.class, () -> m.loadMap(FILENAME3, 10, 10));
    }
}