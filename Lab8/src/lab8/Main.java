package lab8;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URISyntaxException;

public class Main extends Application {
    public static final int CELL_SIZE = 32;
    private static Image tile = null;
    private static Image crate = null;

    /**
     * TODO: load the images, use static{}
     */
    {
        try {
            crate = new Image(Main.class.getResource("/assets/images/crate.png").toURI().toString());
            tile = new Image(Main.class.getResource("/assets/images/tile.png").toURI().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * TODO: set the title to "Lab 8", set the scene, and show the stage
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        int rows = 10;
        int cols = 16;
        primaryStage.setTitle("Lab 8");
        primaryStage.setScene(checkerboardScene(rows, cols));
        primaryStage.show();
    }

    /**
     * TODO:
     * Generate the canvas with the alternating checkerboard pattern of tiles and crates.
     * Place the canvas inside a VBox before returning it in a scene
     *
     * @param rows Number of rows
     * @param cols Number of cols
     * @return The canvas with the images drawn onto it. The top left should always be a crate.
     */
    public Scene checkerboardScene(int rows, int cols) {
        var canvas = new Canvas(rows*CELL_SIZE, cols*CELL_SIZE);
        var gc = canvas.getGraphicsContext2D();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i%2 != j%2) {
                    gc.drawImage(tile, i*CELL_SIZE, j*CELL_SIZE);
                } else {
                    gc.drawImage(crate, i*CELL_SIZE, j*CELL_SIZE);
                }
            }
        }
        var root = new VBox();
        root.getChildren().add(canvas);

        return new Scene(root);
    }
}
