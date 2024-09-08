package bong;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The {@code Main} class serves as the entry point for the JavaFX application.
 * It initializes the JavaFX stage and scene, sets up the UI components,
 * and injects an instance of the {@code Bong} class to handle user interactions.
 */
public class Main extends Application {

    private static final int MIN_HEIGHT = 220;
    private static final int MIN_WIDTH = 417;
    private Bong bong = new Bong();

    /**
     * The main entry point for the JavaFX application.
     * This method is called after the JavaFX runtime initializes.
     * It sets up the primary stage, loads the main window from the FXML file,
     * and injects the {@code Bong} instance into the controller.
     *
     * @param stage The primary stage for this JavaFX application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(MIN_HEIGHT);
            stage.setMinWidth(MIN_WIDTH);
            fxmlLoader.<MainWindow>getController().setBong(bong);  // inject the Bong instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
