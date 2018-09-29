package Map;

import Exceptions.InvalidMapException;
import Exceptions.InvalidNumberOfPlayersException;
import Exceptions.UnknownElementException;
import Map.Occupant.Crate;
import Map.Occupant.Player;
import Map.Occupiable.DestTile;
import Map.Occupiable.Occupiable;
import Map.Occupiable.Tile;

import java.util.ArrayList;

/**
 * A class holding a the 2D array of cells, representing the world map
 */
public class Map {
    private Cell[][] cells;
    private ArrayList<DestTile> destTiles = new ArrayList<>();
    private ArrayList<Crate> crates = new ArrayList<>();

    private Player player;

    /**
     * This function instantiates and initializes cells, destTiles, crates to the correct map elements (the # char
     * means a wall, @ the player, . is unoccupied Tile, lowercase letter is crate on a Tile,
     * uppercase letter is an unoccupied DestTile).
     *
     * @param rows The number of rows in the map
     * @param cols The number of columns in the map
     * @param rep  The 2d char array read from the map text file
     * @throws InvalidMapException Throw the correct exception when necessary. There should only be 1 player.
     */
    public void initialize(int rows, int cols, char[][] rep) throws InvalidMapException {
        cells = new Cell[rows][cols];
        destTiles = new ArrayList<>();
        crates = new ArrayList<>();
        player = null;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Tile tile;
                switch (rep[i][j]) {
                    // Case: Wall
                    case '#':
                        cells[i][j] = new Wall();
                        break;
                    // Case: Tile
                    case '.':
                        cells[i][j] = new Tile();
                        break;
                    // Case: Player
                    // Error if player already exists in map
                    case '@':
                        if (player != null) {
                            throw new InvalidNumberOfPlayersException(">1 players found!");
                        }

                        player = new Player(i, j);
                        tile = new Tile();
                        tile.setOccupant(player);
                        cells[i][j] = tile;
                        break;
                    default:
                        // Case: Crate
                        if (Character.isLowerCase(rep[i][j])) {
                            tile = new Tile();
                            Crate crate = new Crate(i, j, rep[i][j]);
                            tile.setOccupant(crate);
                            cells[i][j] = tile;
                            crates.add(crate);
                        } // Case: DestTile
                        else if (Character.isUpperCase(rep[i][j])) {
                            cells[i][j] = new DestTile(rep[i][j]);
                            destTiles.add((DestTile) cells[i][j]);
                        } else { // Case: Others
                            throw new UnknownElementException("Unknown char: " + rep[i][j]);
                        }
                }
            }
        }

        if (player == null) {
            throw new InvalidNumberOfPlayersException("0 players found!");
        }
    }

    public ArrayList<DestTile> getDestTiles() {
        return destTiles;
    }

    public ArrayList<Crate> getCrates() {
        return crates;
    }

    public Cell[][] getCells() {
        return cells;
    }

    /**
     * Attempts to move the player in the specified direction. Note that the player only has the strength to push
     * one crate. It cannot push 2 or more crates simultaneously. The player cannot walk through walls or walk beyond
     * map coordinates.
     *
     * @param d The direction the player wants to move
     * @return Whether the move was successful
     */
    public boolean movePlayer(Direction d) {
        int r = player.getR();
        int c = player.getC();
        int destR = r;
        int destC = c;

        switch (d) {
            case UP:
                destR = r-1;
                break;
            case DOWN:
                destR = r+1;
                break;
            case LEFT:
                destC = c-1;
                break;
            case RIGHT:
                destC = c+1;
                break;
        }

        if (isValid(destR, destC)) {
            // Valid and occupiable and no crate
            if (isOccupiableAndNotOccupiedWithCrate(destR, destC)) {
                ((Tile) cells[destR][destC]).setOccupant(player);
                ((Tile) cells[r][c]).removeOccupant();
                player.setPos(destR, destC);
                return true;
            }

            // Valid and occupiable but has crate
            if (cells[destR][destC] instanceof Tile) {
                Crate crate = (Crate) ((Tile) cells[destR][destC]).getOccupant().get();

                if (moveCrate(crate, d)) {
                    ((Tile) cells[destR][destC]).setOccupant(player);
                    ((Tile) cells[r][c]).removeOccupant();
                    player.setPos(destR, destC);
                } else {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Attempts to move the crate into the specified direction by 1 cell. Will only succeed if the destination
     * implements the occupiable interface and is not currently occupied.
     *
     * @param c The crate to be moved
     * @param d The desired direction to move the crate in
     * @return Whether or not the move was successful
     */
    private boolean moveCrate(Crate c, Direction d) {
        int row = c.getR();
        int col = c.getC();

        int destR = row;
        int destC = col;

        switch (d) {
            case UP:
                destR = row-1;
                break;
            case DOWN:
                destR = row+1;
                break;
            case LEFT:
                destC = col-1;
                break;
            case RIGHT:
                destC = col+1;
                break;
        }

        if (isValid(destR, destC) &&
                isOccupiableAndNotOccupiedWithCrate(destR, destC)) {
            ((Tile) cells[destR][destC]).setOccupant(c);
            ((Tile) cells[row][col]).removeOccupant();
            c.setPos(destR, destC);
            return true;
        }

        return false;
    }

    private boolean isValid(int r, int c) {
        return (r >= 0 && r < cells.length && c >= 0 && c < cells[0].length);
    }

    /**
     * @param r The row coordinate
     * @param c The column coordinate
     * @return Whether or not the specified location on the grid is a location which implements Occupiable,
     * yet does not currently have a crate in it. Will return false if out of bounds.
     */
    public boolean isOccupiableAndNotOccupiedWithCrate(int r, int c) {
        return isValid(r, c)
                && cells[r][c] instanceof Tile
                && !(((Tile) cells[r][c]).getOccupant().isPresent()
                    && ((Tile) cells[r][c]).getOccupant().get() instanceof Crate);
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
}
