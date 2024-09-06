package bong;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    @FXML
    private Button sendButton;

    private Bong bong;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/speaking.png"));
    private Image bongImage = new Image(this.getClass().getResourceAsStream("/images/cool.png"));

    /**
     * Initializes the main window controller.
     * Binds the vertical scroll value of the scroll pane to the height of the dialog container.
     * Displays a welcome message from Bong when the application starts.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // Show welcome message when the application starts
        dialogContainer.getChildren().add(DialogBox.getBongDialog(new Bong().showWelcomeMessage(), bongImage));
    }

    /**
     * Injects the {@code Bong} instance into this controller.
     *
     * @param d The {@code Bong} instance to be used by this controller.
     */
    public void setBong(Bong d) {
        bong = d;
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

        // Display dialog for user input and Bong's response
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBongDialog(response, bongImage)
        );

        // Check if the user input is "bye"
        if (input.trim().equalsIgnoreCase("bye")) {
            // Show goodbye message
            dialogContainer.getChildren().add(
                    DialogBox.getBongDialog("Goodbye! Have a nice day!", bongImage)
            );

            // Close the application after a short delay
            Platform.runLater(() -> {
                try {
                    Thread.sleep(2000); // Wait for 2 seconds before closing
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.exit();
            });
        }

        userInput.clear();
    }
}
