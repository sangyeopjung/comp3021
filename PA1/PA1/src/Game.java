
import Exceptions.InvalidMapException;
import Exceptions.InvalidSizeMapException;
import Map.*;
import Map.Occupant.Crate;
import Map.Occupiable.DestTile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Holds the necessary components for running the game.
 */
public class Game {

    private Map m;
    private int numRows;
    private int numCols;
    private char[][] rep;

    /**
     * Loads and reads the map line by line, instantiates and initializes Map m.
     * Print out the number of rows, then number of cols (on two separate lines).
     *
     * @param filename the map text filename
     * @throws InvalidMapException
     */
    public void loadMap(String filename) throws InvalidMapException {
        File file = new File(filename);

        try (
            Scanner sc = new Scanner(file)
        ){
            m = new Map();
            numRows = sc.nextInt();
            numCols = sc.nextInt();
            rep = new char[numRows][numCols];

            System.out.println(numRows);
            System.out.println(numCols);

            int r = -1;
            while (sc.hasNext()) {
                String line = sc.next();
                r++;

                if (r >= numRows) {
                    throw new InvalidSizeMapException("Map has wrong number of rows");
                }
                if (line.length() != numCols) {
                    throw new InvalidSizeMapException("Map has wrong number of columns");
                }

                for (int c = 0; c < numCols; c++) {
                    rep[r][c] = line.charAt(c);
                }
            }

            if (r < numRows-1) {
                throw new InvalidSizeMapException("Map has wrong number of rows");
            }
            sc.close();
            m.initialize(numRows, numCols, rep);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Can be done using functional concepts.
     * @return Whether or not the win condition has been satisfied
     */
    public boolean isWin() {
        for (DestTile tile: m.getDestTiles()) {
            if (! tile.isCompleted()) {
                return false;
            }
        }
        return true;
    }

    /**
     * When no crates can be moved but the game is not won, then deadlock has occurred.
     *
     * @return Whether deadlock has occurred
     */
    public boolean isDeadlocked() {
        for (Crate crate: m.getCrates()) {
            if (canMove(crate)) {
                return false;
            }
        }
        return true;
    }

    private boolean canMove(Crate crate) {
        int r = crate.getR();
        int c = crate.getC();

        if ((m.isOccupiableAndNotOccupiedWithCrate(r+1, c)
            && m.isOccupiableAndNotOccupiedWithCrate(r-1, c))
            || m.isOccupiableAndNotOccupiedWithCrate(r, c+1)
            && m.isOccupiableAndNotOccupiedWithCrate(r, c-1)) {
            return true;
        }
        return false;
    }

    /**
     * Print the map to console
     */
    public void display() {
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                System.out.print(m.getCells()[r][c].getRepresentation());
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * @param c The char corresponding to a move from the user
     *          w: up
     *          a: left
     *          s: down
     *          d: right
     *          r: reload map (resets any progress made so far)
     * @return Whether or not the move was successful
     */
    public boolean makeMove(char c) {
        switch (c) {
            case 'w':
                return m.movePlayer(Map.Direction.UP);
            case 'a':
                return m.movePlayer(Map.Direction.LEFT);
            case 's':
                return m.movePlayer(Map.Direction.DOWN);
            case 'd':
                return m.movePlayer(Map.Direction.RIGHT);
            case 'r':
                try { m.initialize(numRows, numCols, rep); } catch (InvalidMapException e) {}
                return true;
            default:
                return false;
        }
    }
}
