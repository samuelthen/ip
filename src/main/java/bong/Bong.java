package bong;

import javafx.application.Application;

import bong.command.Command;
import bong.command.CommandResult;
import bong.task.TaskList;
import bong.utils.BongException;
import bong.utils.Parser;
import bong.utils.Storage;

/**
 * The {@code Bong} class is the main entry point of the application.
 * It initializes the necessary components and starts the main program loop
 * to process user commands.
 */
public class Bong {
    private static final String DEFAULT_FILE_PATH = "./data/bong.txt";
    private final Storage storage;
    private TaskList tasks;
    private final Parser parser;

    /**
     * Constructs a new {@code Bong} instance.
     * Initializes the storage, parser, and loads tasks from the storage file.
     * If the storage file cannot be loaded, an empty task list is initialized.
     */
    public Bong() {
        storage = new Storage(DEFAULT_FILE_PATH);
        parser = new Parser();
        loadTasks();
    }

    /**
     * Loads tasks from storage or initializes an empty task list if loading fails.
     */
    private void loadTasks() {
        try {
            tasks = new TaskList(storage.load());
        } catch (BongException e) {
            tasks = new TaskList();
        }

        assert tasks != null : "TaskList should not be null after initialization";
    }

    /**
     * Processes a user input, executes the corresponding command,
     * and returns the result as a response.
     *
     * @param userInput The user input string.
     * @return The feedback message to the user after executing the command.
     */
    public String getResponse(String userInput) {
        try {
            Command command = parser.parseCommand(userInput);
            assert command != null : "Parsed command should not be null";

            CommandResult result = command.execute(tasks);
            assert result != null : "Command result should not be null";
            if (result.isModified()) {
                storage.save(tasks.getList());
            }
            return result.getFeedbackToUser();
        } catch (BongException e) {
            return e.getMessage();
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return "An error occurred.";
        }
    }

    /**
     * Displays the welcome message for the application.
     *
     * @return A welcome message string.
     */
    public String showWelcomeMessage() {
        return "Hello! I'm Bong.\nWhat can I do for you?";
    }

    /**
     * The main method that serves as the entry point of the application.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
