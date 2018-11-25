package viewmodel.panes;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Exceptions.InvalidMapException;
import model.LevelManager;
import viewmodel.AudioManager;
import viewmodel.Config;
import viewmodel.MapRenderer;
import viewmodel.SceneManager;
import viewmodel.customNodes.GameplayInfoPane;

import java.util.Optional;

/**
 * Represents the gameplay pane in the game
 */
public class GameplayPane extends BorderPane {

    private final GameplayInfoPane info;
    private VBox canvasContainer;
    private Canvas gamePlayCanvas;
    private HBox buttonBar;
    private Button restartButton;
    private Button quitToMenuButton;

    /**
     * Instantiate the member components and connect and style them. Also set the callbacks.
     * Use 20 for the VBox spacing
     */
    public GameplayPane() {
        //TODO
        LevelManager levelManager = LevelManager.getInstance();
        info = new GameplayInfoPane(levelManager.currentLevelNameProperty(),
                levelManager.curGameLevelExistedDurationProperty(),
                levelManager.getGameLevel().numPushesProperty(),
                levelManager.curGameLevelNumRestartsProperty()
        );
        canvasContainer = new VBox(20);
        gamePlayCanvas = new Canvas();
        buttonBar = new HBox();
        restartButton = new Button("Restart");
        quitToMenuButton = new Button("Quit to menu");

        connectComponents();
        styleComponents();
        setCallbacks();
        renderCanvas();
    }

    /**
     * Connects the components together (think adding them into another, setting their positions, etc).
     */
    private void connectComponents() {
        //TODO
        canvasContainer.getChildren().add(gamePlayCanvas);
        buttonBar.getChildren().addAll(info, restartButton, quitToMenuButton);
        this.setCenter(canvasContainer);
        this.setBottom(buttonBar);
    }

    /**
     * Apply CSS styling to components.
     */
    private void styleComponents() {
        //TODO
        this.getStylesheets().add(Config.CSS_STYLES);
        canvasContainer.getStyleClass().add("big-vbox");
        buttonBar.getStyleClass().add("bottom-menu");
        restartButton.getStyleClass().add("big-button");
        quitToMenuButton.getStyleClass().add("big-button");
    }

    /**
     * Set the event handlers for the 2 buttons.
     * <p>
     * Also listens for key presses (w, a, s, d), which move the character.
     * <p>
     * Hint: {@link GameplayPane#setOnKeyPressed(EventHandler)}  is needed.
     * You will need to make the move, rerender the canvas, play the sound (if the move was made), and detect
     * for win and deadlock conditions. If win, play the win sound, and do the appropriate action regarding the timers
     * and generating the popups. If deadlock, play the deadlock sound, and do the appropriate action regarding the timers
     * and generating the popups.
     */
    private void doActions() {
        renderCanvas();

        LevelManager levelManager = LevelManager.getInstance();
        AudioManager audioManager = AudioManager.getInstance();

        if (levelManager.getGameLevel().isWin()) {
            audioManager.playWinSound();
            levelManager.resetLevelTimer();
            createLevelClearPopup();
        } else if (levelManager.getGameLevel().isDeadlocked()) {
            audioManager.playDeadlockSound();
            levelManager.resetLevelTimer();
            createDeadlockedPopup();
        } else {
            audioManager.playMoveSound();
        }
    }
    private void setCallbacks() {
        //TODO
        restartButton.setOnMouseClicked(event -> doRestartAction());
        quitToMenuButton.setOnMouseClicked(event -> doQuitToMenuAction());

        LevelManager levelManager = LevelManager.getInstance();
        this.setOnKeyPressed(event -> {
            var code = event.getCode();
            if (code == KeyCode.W) {
                if (levelManager.getGameLevel().makeMove('w')) {
                    doActions();
                }
            } else if (code == KeyCode.A) {
                if (levelManager.getGameLevel().makeMove('a')) {
                    doActions();
                }
            } else if (code == KeyCode.S) {
                if (levelManager.getGameLevel().makeMove('s')) {
                    doActions();
                }
            } else if (code == KeyCode.D) {
                if (levelManager.getGameLevel().makeMove('d')) {
                    doActions();
                }
            }
        });
    }

    /**
     * Called when the tries to quit to menu. Show a popup (see the documentation). If confirmed,
     * do the appropriate action regarding the level timer, level number of restarts, and go to the
     * main menu scene.
     */
    private void doQuitToMenuAction() {
        //TODO
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Quit to menu?");
        alert.setContentText("Your progress will be lost.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            LevelManager.getInstance().resetLevelTimer();
            LevelManager.getInstance().resetNumRestarts();
            SceneManager.getInstance().showMainMenuScene();
        }
    }

    /**
     * Called when the user encounters deadlock. Show a popup (see the documentation).
     * If the user chooses to restart the level, call {@link GameplayPane#doRestartAction()}. Otherwise if they
     * quit to menu, switch to the level select scene, and do the appropriate action regarding
     * the number of restarts.
     */
    private void createDeadlockedPopup() {
        //TODO
        ButtonType restart = new ButtonType("Restart", ButtonBar.ButtonData.OK_DONE);
        ButtonType backToMenu = new ButtonType("Back to Menu", ButtonBar.ButtonData.CANCEL_CLOSE);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", restart, backToMenu);
        alert.setTitle("Confirm");
        alert.setHeaderText("Deadlocked! Try again?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == backToMenu) {
            doQuitToMenuAction();
        } else {
            doRestartAction();
        }
    }

    /**
     * Called when the user clears the level successfully. Show a popup (see the documentation).
     * If the user chooses to go to the next level, set the new level, rerender, and do the appropriate action
     * regarding the timers and num restarts. If they choose to return, show the level select menu, and do
     * the appropriate action regarding the number of level restarts.
     * <p>
     * Hint:
     * Take care of the edge case for when the user clears the last level. In this case, there shouldn't
     * be an option to go to the next level.
     */
    private void createLevelClearPopup() {
        //TODO
        ButtonType nextLevel = new ButtonType("Next Level", ButtonBar.ButtonData.OK_DONE);
        ButtonType backToMenu = new ButtonType("Back to Menu", ButtonBar.ButtonData.CANCEL_CLOSE);

        Alert alert;
        if (LevelManager.getInstance().getNextLevelName() != null) {
            alert = new Alert(Alert.AlertType.CONFIRMATION, "Go to next level?", nextLevel, backToMenu);
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION, "You've completed all levels!", backToMenu);
        }
        alert.setTitle("Confirm");
        alert.setHeaderText("Level Cleared!");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == nextLevel) {
            LevelManager lm = LevelManager.getInstance();
            lm.resetLevelTimer();
            lm.resetNumRestarts();
            lm.startLevelTimer();
            try {
                lm.setLevel(lm.getNextLevelName());
                renderCanvas();
            } catch (InvalidMapException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                SceneManager.getInstance().showLevelSelectMenuScene();
            }
        } else {
            SceneManager.getInstance().showLevelSelectMenuScene();
        }
    }

    /**
     * Set the current level to the current level name, rerender the canvas, reset and start the timer, and
     * increment the number of restarts
     */
    private void doRestartAction() {
        //TODO
        try {
            LevelManager levelManager = LevelManager.getInstance();
            levelManager.setLevel(levelManager.currentLevelNameProperty().getValue());
            renderCanvas();
            levelManager.resetLevelTimer();
            levelManager.incrementNumRestarts();
            levelManager.startLevelTimer();
        } catch (InvalidMapException e) {
            e.printStackTrace();
        }
    }

    /**
     * Render the canvas with updated data
     * <p>
     * Hint: {@link MapRenderer}
     */
    private void renderCanvas() {
        //TODO
        MapRenderer.render(gamePlayCanvas, LevelManager.getInstance().getGameLevel().getMap().getCells());
    }
}
