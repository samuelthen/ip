package bong;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI of the application.
 * Manages the layout and functionality of the user interface,
 * handling user inputs and displaying responses from Bong.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Bong bong;

    private static final Image USER_IMAGE = new Image(MainWindow.class.getResourceAsStream("/images/speaking.png"));
    private static final Image BONG_IMAGE = new Image(MainWindow.class.getResourceAsStream("/images/cool.png"));

    /**
     * Initializes the main window controller.
     * Binds the vertical scroll value of the scroll pane to the height of the dialog container.
     * Displays a welcome message from Bong when the application starts.
     */
    @FXML
    public void initialize() {
        assert USER_IMAGE != null : "User image should be loaded";
        assert BONG_IMAGE != null : "Bong image should be loaded";

        assert scrollPane != null : "ScrollPane should be initialized";
        assert dialogContainer != null : "DialogContainer should be initialized";
        assert userInput != null : "UserInput should be initialized";

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getBongDialog(new Bong().showWelcomeMessage(), BONG_IMAGE));

    }

    /**
     * Injects the {@code Bong} instance into this controller.
     *
     * @param bongInstance The {@code Bong} instance to be used by this controller.
     */
    public void setBong(Bong bongInstance) {
        this.bong = bongInstance;
    }

    /**
     * Handles the user input by creating two dialog boxes: one for the user's input
     * and another for Bong's response. Appends these dialog boxes to the dialog container.
     * Clears the user input after processing. If the input is "bye", displays a goodbye
     * message and closes the application after a short delay.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bong.getResponse(input);
        displayDialog(input, response);

        if (isExitCommand(input)) {
            displayExitMessage();
            scheduleExit();
        }

        userInput.clear();
    }

    /**
     * Displays the user input and Bong's response in the dialog container.
     *
     * @param input    The user input text.
     * @param response The response from Bong.
     */
    private void displayDialog(String input, String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, USER_IMAGE),
                DialogBox.getBongDialog(response, BONG_IMAGE)
        );
    }

    /**
     * Checks if the user input is an exit command.
     *
     * @param input The user input text.
     * @return True if the input is "bye", otherwise false.
     */
    private boolean isExitCommand(String input) {
        return input.trim().equalsIgnoreCase("bye");
    }

    /**
     * Displays a goodbye message and schedules the application to close.
     */
    private void displayExitMessage() {
        dialogContainer.getChildren().add(
                DialogBox.getBongDialog("Goodbye! Have a nice day!", BONG_IMAGE)
        );
    }

    /**
     * Schedules the application to close after a short delay.
     */
    private void scheduleExit() {
        Platform.runLater(() -> {
            try {
                Thread.sleep(2000); // Wait for 2 seconds before closing
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.exit();
        });
    }
}