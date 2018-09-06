
import Exceptions.InvalidMapException;
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
        //TODO

    }

    /**
     * Can be done using functional concepts.
     * @return Whether or not the win condition has been satisfied
     */
    public boolean isWin() {
        //TODO
        return false; // You may also modify this line.
    }

    /**
     * When no crates can be moved but the game is not won, then deadlock has occurred.
     *
     * @return Whether deadlock has occurred
     */
    public boolean isDeadlocked() {
        //TODO
        return false; // You may also modify this line.
    }

    /**
     * Print the map to console
     */
    public void display() {
        //TODO

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
        //TODO
        return false; // You may also modify this line.
    }


}
