package viewmodel.panes;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import model.Exceptions.InvalidMapException;
import model.LevelManager;
import model.Map.Cell;
import viewmodel.Config;
import viewmodel.MapRenderer;
import viewmodel.SceneManager;

import java.io.File;

/**
 * Represents the main menu in the game
 */
public class LevelSelectPane extends BorderPane {
    private VBox leftContainer;
    private Button returnButton;
    private Button playButton;
    private Button chooseMapDirButton;
    private ListView<String> levelsListView;
    private VBox centerContainer;
    private Canvas levelPreview;

    /**
     * Instantiate the member components and connect and style them. Also set the callbacks.
     * Use 20 for VBox spacing
     */
    public LevelSelectPane() {
        //TODO
        leftContainer = new VBox(20);
        returnButton = new Button("Return");
        playButton = new Button("Play");
        chooseMapDirButton = new Button("Choose map directory");
        levelsListView = new ListView<>();
        centerContainer = new VBox();
        levelPreview = new Canvas();

        connectComponents();
        styleComponents();
        setCallbacks();
    }

    /**
     * Connects the components together (think adding them into another, setting their positions, etc). Reference
     * the other classes in the {@link javafx.scene.layout.Pane} package.
     */
    private void connectComponents() {
        //TODO
        leftContainer.getChildren().addAll(returnButton, chooseMapDirButton, levelsListView, playButton);
        centerContainer.getChildren().add(levelPreview);
        this.setLeft(leftContainer);
        this.setCenter(centerContainer);
    }

    /**
     * Apply CSS styling to components. Also sets the {@link LevelSelectPane#playButton} to be disabled.
     */
    private void styleComponents() {
        //TODO
        this.getStylesheets().add(Config.CSS_STYLES);
        leftContainer.getStyleClass().add("side-menu");
        returnButton.getStyleClass().add("big-button");
        playButton.getStyleClass().add("big-button");
        chooseMapDirButton.getStyleClass().add("big-button");
        levelsListView.getStyleClass().add("list-cell");
        centerContainer.getStyleClass().add("big-vbox");
        playButton.setDisable(true);
    }

    /**
     * Set the event handlers for the 3 buttons and listview.
     * <p>
     * Hints:
     * The return button should show the main menu scene
     * The chooseMapDir button should prompt the user to choose the map directory, and load the levels
     * The play button should set the current level based on the current level name (see LevelManager), show
     * the gameplay scene, and start the level timer.
     * The listview, based on which item was clicked, should set the current level (see LevelManager), render the
     * preview (see {@link MapRenderer#render(Canvas, Cell[][])}}, and set the play button to enabled.
     */
    private void setCallbacks() {
        //TODO
        returnButton.setOnMouseClicked(event -> SceneManager.getInstance().showMainMenuScene());
        chooseMapDirButton.setOnMouseClicked(event -> promptUserForMapDirectory());

        playButton.setOnMouseClicked(event -> {
            try {
                LevelManager.getInstance().setLevel(levelsListView.getSelectionModel().getSelectedItem());
                SceneManager.getInstance().showGamePlayScene();
                LevelManager.getInstance().startLevelTimer();
            } catch (InvalidMapException e) {
                e.printStackTrace();
            }
        });

        levelsListView.setOnMouseClicked(event -> {
            try {
                LevelManager.getInstance().setLevel(levelsListView.getSelectionModel().getSelectedItem());
                MapRenderer.render(levelPreview, LevelManager.getInstance().getGameLevel().getMap().getCells());
                if (levelsListView.getSelectionModel().getSelectedItem() != null) {
                    playButton.setDisable(false);
                }
            } catch (InvalidMapException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Popup a DirectoryChooser window to ask the user where the map folder is stored.
     * Update the LevelManager's map directory afterwards, and potentially
     * load the levels from disk using LevelManager (if the user didn't cancel out the window)
     */
    private void promptUserForMapDirectory() {
        //TODO
        DirectoryChooser dc = new DirectoryChooser();
        File directory = dc.showDialog(SceneManager.getInstance().getStage());
        if (directory != null) {
            playButton.setDisable(true);
            LevelManager.getInstance().setMapDirectory(directory.toString());
            LevelManager.getInstance().loadLevelNamesFromDisk();
            levelsListView.setItems(LevelManager.getInstance().getLevelNames());
        }
    }
}
