package bong;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.Node;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a {@code DialogBox} with the specified text and image.
     * Loads the corresponding FXML file to initialize the layout and sets
     * the text and image for the dialog.
     *
     * @param text The text content of the dialog box.
     * @param img  The image representing the speaker's face.
     */
    private DialogBox(String text, Image img) {
        loadFXML();
        setDialogContent(text, img);
    }

    /**
     * Loads the FXML layout for the dialog box.
     */
    private void loadFXML() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the text and image content for the dialog.
     *
     * @param text The text content to display.
     * @param img  The image representing the speaker.
     */
    private void setDialogContent(String text, Image img) {
        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and the text is on the right.
     * This is typically used to distinguish between user and system messages.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a {@code DialogBox} for the user dialog.
     *
     * @param text The text content of the user dialog.
     * @param img  The image representing the user's face.
     * @return A new {@code DialogBox} instance representing the user dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a {@code DialogBox} for the Bong dialog (the bot's response).
     * The dialog box is flipped such that the ImageView is on the left and the text is on the right.
     *
     * @param text The text content of the Bong dialog.
     * @param img  The image representing Bong's face.
     * @return A new {@code DialogBox} instance representing the Bong dialog.
     */
    public static DialogBox getBongDialog(String text, Image img) {
        DialogBox dialogBox = new DialogBox(text, img);
        dialogBox.flip();
        return dialogBox;
    }
}
