package bong.command;

import bong.task.TaskList;

import bong.utils.BongException;

/**
 * Represents a command that can be executed.
 */
public interface Command {
    /**
     * Executes the command using the provided {@code TaskList}.
     *
     * @param tasks The {@code TaskList} on which the command will be executed.
     * @return A {@code CommandResult} containing the feedback to the user and the modification status.
     * @throws BongException If an error occurs during command execution.
     */
    CommandResult execute(TaskList tasks) throws BongException;

    /**
     * Indicates whether this command signifies an exit action.
     *
     * @return {@code true} if this command signifies an exit action, {@code false} otherwise.
     */
    boolean isExit();
}
