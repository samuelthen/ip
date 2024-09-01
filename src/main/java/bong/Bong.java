package bong;

import bong.utils.BongException;
import bong.command.Command;
import bong.command.CommandResult;
import bong.utils.Parser;
import bong.utils.Storage;
import bong.task.TaskList;
import bong.utils.Ui;

/**
 * The {@code Bong} class is the main entry point of the application.
 * It initializes the necessary components and starts the main program loop
 * to process user commands.
 */
public class Bong {
    private static final String FILE_PATH = "./data/bong.txt"; // Hardcoded file path
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a new {@code Bong} instance.
     * Initializes the UI, storage, and parser, and loads tasks from the storage file.
     * If the storage file cannot be loaded, an empty task list is initialized.
     */
    public Bong() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (BongException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main program loop.
     * Continuously reads user input, parses commands, executes them, and displays results.
     * The loop terminates when the user issues an exit command.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                Command command = parser.parseCommand(userInput);
                CommandResult result = command.execute(tasks);
                ui.showCommandResult(result);
                isExit = command.isExit();
                if (result.isModified()) {
                    storage.save(tasks.getList());
                }
            } catch (BongException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.close();
    }

    /**
     * The main method that serves as the entry point of the application.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        new Bong().run();
    }
}