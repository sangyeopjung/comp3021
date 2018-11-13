package lab9;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URISyntaxException;

public class Main extends Application {
    public static final int CELL_SIZE = 32;
    private static Image tile = null;
    private static Image crate = null;

    static {
        try {
            crate = new Image(Main.class.getResource("/assets/images/crate.png").toURI().toString());
            tile = new Image(Main.class.getResource("/assets/images/tile.png").toURI().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private VBox inputsContainer = new VBox(20);
    private Label rowText = new Label("Rows: ");
    private TextField rowInput = new TextField();
    private Label colText = new Label("Cols: ");
    private TextField colInput = new TextField();
    private Button resizeButton = new Button("Resize Board");
    private Canvas canvas = new Canvas();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lab 9");

        var scene = checkerboardScene(8, 8);
        scene.getStylesheets().add("assets/css/styles.css");
        setCallbacks();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * TODO
     * Construct an HBox, and place the inputsContainer and canvas objects into it.
     * The inputsContainer should contain the rowText, rowInput, colText, colInput, and resizeButton
     * Finally, call drawCanvas with the passed rows and cols, and return a scene containing the hbox
     *
     * @param rows initial row size
     * @param cols initial col size
     * @return
     */
    public Scene checkerboardScene(int rows, int cols) {
        var hbox = new HBox();
        inputsContainer.getChildren().addAll(
                rowText, rowInput, colText, colInput, resizeButton);
        hbox.getChildren().addAll(inputsContainer, canvas);
        drawCanvas(rows, cols);
        return new Scene(hbox);
    }

    /**
     * TODO
     * <p>
     * Draw the canvas based on the dimensions
     *
     * @param rows number of rows
     * @param cols number of cols
     */
    private void drawCanvas(int rows, int cols) {
        canvas.setWidth(rows*CELL_SIZE);
        canvas.setHeight(cols*CELL_SIZE);

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
    }

    /**
     * TODO
     * <p>
     * Create a callback for the resizeButton, which calls drawCanvas based on the value currently inside the
     * rowInput and colInput fields. You may place the Integer parsing logic inside a try catch block, and
     * ignore invalid inputs
     */
    public void setCallbacks() {
        resizeButton.setOnAction(e -> {
            try {
                int row = Integer.parseInt(rowInput.getCharacters().toString());
                int col = Integer.parseInt(colInput.getCharacters().toString());
                drawCanvas(row, col);
            } catch (NumberFormatException ex) {}
        });
    }
}
