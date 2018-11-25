package viewmodel;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.Map.Cell;
import model.Map.Occupant.Player;
import model.Map.Occupiable.DestTile;
import model.Map.Occupiable.Occupiable;

import java.net.URISyntaxException;

import static viewmodel.Config.LEVEL_EDITOR_TILE_SIZE;

/**
 * Renders maps onto canvases
 */
public class MapRenderer {
    private static Image wall = null;
    private static Image crateOnTile = null;
    private static Image crateOnDest = null;

    private static Image playerOnTile = null;
    private static Image playerOnDest = null;

    private static Image dest = null;
    private static Image tile = null;

    static {
        try {
            wall = new Image(MapRenderer.class.getResource("/assets/images/wall.png").toURI().toString());
            crateOnTile = new Image(MapRenderer.class.getResource("/assets/images/crateOnTile.png").toURI().toString());
            crateOnDest = new Image(MapRenderer.class.getResource("/assets/images/crateOnDest.png").toURI().toString());
            playerOnTile = new Image(MapRenderer.class.getResource("/assets/images/playerOnTile.png").toURI().toString());
            playerOnDest = new Image(MapRenderer.class.getResource("/assets/images/playerOnDest.png").toURI().toString());
            dest = new Image(MapRenderer.class.getResource("/assets/images/dest.png").toURI().toString());
            tile = new Image(MapRenderer.class.getResource("/assets/images/tile.png").toURI().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Render the map onto the canvas. This method can be used in Level Editor
     * <p>
     * Hint: set the canvas height and width as a multiple of the rows and cols
     *
     * @param canvas The canvas to be rendered onto
     * @param map    The map holding the current state of the game
     */
    static void render(Canvas canvas, LevelEditorCanvas.Brush[][] map) {
        //TODO
        int row = map.length;
        int col = map.length > 0 ? map[0].length : 0;

        canvas.setHeight(row*LEVEL_EDITOR_TILE_SIZE);
        canvas.setWidth(col*LEVEL_EDITOR_TILE_SIZE);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Image image;
                char rep = map[i][j].getRep();
                if (rep == LevelEditorCanvas.Brush.CRATE_ON_TILE.getRep()) {
                    image = crateOnTile;
                } else if (rep == LevelEditorCanvas.Brush.CRATE_ON_DEST.getRep()) {
                    image = crateOnDest;
                } else if (rep == LevelEditorCanvas.Brush.PLAYER_ON_TILE.getRep()) {
                    image = playerOnTile;
                } else if (rep == LevelEditorCanvas.Brush.PLAYER_ON_DEST.getRep()) {
                    image = playerOnDest;
                } else if (rep == LevelEditorCanvas.Brush.DEST.getRep()) {
                    image = dest;
                } else if (rep == LevelEditorCanvas.Brush.TILE.getRep()) {
                    image = tile;
                } else {
                    image = wall;
                }
                gc.drawImage(image, j*LEVEL_EDITOR_TILE_SIZE, i*LEVEL_EDITOR_TILE_SIZE);
            }
        }
    }

    /**
     * Render the map onto the canvas. This method can be used in GamePlayPane and LevelSelectPane
     * <p>
     * Hint: set the canvas height and width as a multiple of the rows and cols
     *
     * @param canvas The canvas to be rendered onto
     * @param map    The map holding the current state of the game
     */
    public static void render(Canvas canvas, Cell[][] map) {
        //TODO
        int row = map.length;
        int col = map.length > 0 ? map[0].length : 0;

        canvas.setHeight(row*LEVEL_EDITOR_TILE_SIZE);
        canvas.setWidth(col*LEVEL_EDITOR_TILE_SIZE);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Image image;
                Cell rep = map[i][j];
                if (rep instanceof DestTile) {
                    if (((DestTile)rep).getOccupant().isPresent()) {
                        if (((DestTile)rep).getOccupant().get() instanceof Player) {
                            image = playerOnDest;
                        } else {
                            image = crateOnDest;
                        }
                    } else {
                        image = dest;
                    }
                } else if (rep instanceof Occupiable) {
                    if (((Occupiable)rep).getOccupant().isPresent()) {
                        if (((Occupiable)rep).getOccupant().get() instanceof Player) {
                            image = playerOnTile;
                        } else {
                            image = crateOnTile;
                        }
                    } else {
                        image = tile;
                    }
                } else {
                    image = wall;
                }
                gc.drawImage(image, j*LEVEL_EDITOR_TILE_SIZE, i*LEVEL_EDITOR_TILE_SIZE);
            }
        }
    }
}
