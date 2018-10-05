import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.Scanner;

public class Map {

    /**
     * This method saves to file a rectangular block of text (representing a map in PA1) based on the given dimensions.
     * Before saving the map, print out the number of rows on the first line, the number of columns on the second line,
     * and the actual block of text starting from the third line.
     *
     * @param filename The filename to save the block of text. Assume it includes the .txt extension.
     * @param map      The grid of chars to save
     * @throws IllegalArgumentException If # of rows < 3 or # cols < 3
     */
    public void createMap(String filename, char[][] map) throws IllegalArgumentException {
        File file = new File(filename);

        try(
            PrintWriter pr = new PrintWriter(file)
        ) {
            int row = map.length;
            int col = Integer.MAX_VALUE;
            for (int i = 0; i < row; i++) {
                col = Integer.min(col, map[i].length);
            }

            if (row < 3 || col < 3)
                throw new IllegalArgumentException();

            pr.print(row);
            pr.println();
            pr.print(col);

            for (int i = 0; i < row; i++) {
                pr.println();
                for (int j = 0; j < col; j++) {
                    pr.print(map[i][j]);
                }
            }

        } catch(FileNotFoundException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    /**
     * Loads a map into a 2d char array. Note that the format of the text file will be the same
     * format as we saved it: first row  has an integer representing how many rows, second row has an integer
     * representing how many columns, and the rest of the rows is the actual block of text.
     *
     * @param filename The filename of the map
     * @param minRows  the minimum number of rows this map must have. If violated, throw BadMapException
     * @param minCols  the minimum number of cols this map must have. If violated, throw BadMapException
     * @return The 2d char representing the map.
     * @throws BadMapException if the minRows or minCols constraints are violated.
     */
    public char[][] loadMap(String filename, int minRows, int minCols) throws BadMapException {
        File file = new File(filename);

        try (
            Scanner sc = new Scanner(file)
        ){
            int row = sc.nextInt();
            int col = sc.nextInt();

            if (row < minRows || col < minCols)
                throw new BadMapException();

            char[][] map = new char[row][col];

            int r = -1;
            while (sc.hasNext()) {
                String line = sc.next();
                r++;

                if (r >= row) {
                    throw new BadMapException();
                }
                if (line.length() != col) {
                    throw new BadMapException();
                }

                for (int c = 0; c < col; c++) {
                    map[r][c] = line.charAt(c);
                }
            }

            if (r < row-1) {
                throw new BadMapException();
            }

            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadMapException();
        }
    }


}
