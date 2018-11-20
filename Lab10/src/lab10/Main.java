package lab10;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {

    private final IntegerProperty timerSeconds = new SimpleIntegerProperty();
    private Timer timer = new Timer(true); //declare as daemon, so application exits when Platform.exit is called
    private Label timeLabel = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @param s Seconds duration
     * @return A string that formats the duration stopwatch style
     */
    private static String format(int s) {
        return String.format("%02d:%02d:%02d", s / 3600, (s % 3600) / 60, (s % 60));
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lab 10");
        primaryStage.setScene(timerScene());
        primaryStage.show();
        startLevelTimer();
    }

    private Scene timerScene() {
        bindTimerToTime(timerSeconds);
        timeLabel.setFont(new Font("Arial", 30));
        setCallbacks();
        return new Scene(timeLabel);
    }

    /**
     * TODO
     * Set the callback for the label: when clicked, it should stop then start the timer from 0.
     */
    private void setCallbacks() {
        timeLabel.setOnMouseClicked(e -> {
            stopLevelTimer();
            startLevelTimer();
        });
    }

    /**
     * Cancels the existing timer and assigns it to a new instance
     */
    private void stopLevelTimer() {
        timer.cancel();
        timer = new Timer(true);
    }

    /**
     * TODO
     * First, set the timerSeconds integerProperty to 0.
     * Then, pass timer's scheduleAtFixedRate a new TimerTask anonymous object. This object should override
     * the run method. Inside the run method, we should call Platform.RunLater(), which itself takes a lambda
     * expression which updates our timerSeconds IntegerProperty. Increment it by 1 every 1000 milliseconds.
     * <p>
     * The timer's scheduleAtFixedRate should have 1000 delay and a period of 1000 ms.
     */
    private void startLevelTimer() {
        timerSeconds.setValue(0);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> timerSeconds.set(timerSeconds.getValue()+1));
            }
        }, 1000, 1000);
    }

    /**
     * TODO
     * Bind the textProperty of the timeLabel to the value of the timerProperty. You may find the following
     * functions useful:
     * <p>
     * textProperty().bind
     * Bindings.createStringBinding()
     * timerProperty.get()
     * format()
     *
     * @param timerProperty The IntegerProperty counting elapsed seconds
     */
    private void bindTimerToTime(IntegerProperty timerProperty) {
        timeLabel.textProperty().bind(
                Bindings.createStringBinding(
                        () -> format(timerProperty.get()),
                        timerProperty
                )
        );
    }
}
