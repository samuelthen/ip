package bong;

import bong.utils.BongException;
import bong.command.Command;
import bong.command.CommandResult;
import bong.utils.Parser;
import bong.utils.Storage;
import bong.task.TaskList;
import bong.utils.Ui;

public class Bong {
    private static final String FILE_PATH = "./data/bong.txt"; // Hardcoded file path
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

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

    public static void main(String[] args) {
        new Bong().run();
    }
}
