package bong.command;

import bong.task.TaskList;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand implements Command {

    /**
     * Executes the exit command, which terminates the application.
     *
     * @param tasks The {@code TaskList} is not used in this command.
     * @return A {@code CommandResult} indicating that the application will exit.
     */
    @Override
    public CommandResult execute(TaskList tasks) {
        return new CommandResult("Bye. Hope to see you again soon!", false);
    }

    /**
     * Indicates that this command signifies an exit action.
     *
     * @return {@code true} as this command signifies an exit action.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
