package bong.command;

import bong.task.Task;
import bong.task.TaskList;

import bong.utils.BongException;

/**
 * Represents a command to unmark a task as not done.
 */
public class UnmarkCommand implements Command {
    private final int taskIndex;

    /**
     * Constructs an {@code UnmarkCommand} with the specified task index.
     *
     * @param args The index of the task to be unmarked as not done, provided as a string.
     * @throws BongException If the provided index is not a valid integer or is out of bounds.
     */
    public UnmarkCommand(String args) throws BongException {
        try {
            this.taskIndex = Integer.parseInt(args.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new BongException("Please provide a valid task number.");
        }
    }

    /**
     * Executes the command to unmark the specified task as not done in the given {@code TaskList}.
     *
     * @param tasks The {@code TaskList} in which the task will be unmarked as not done.
     * @return A {@code CommandResult} indicating the task has been unmarked as not done.
     * @throws BongException If the task index is out of bounds.
     */
    @Override
    public CommandResult execute(TaskList tasks) throws BongException {
        Task task = tasks.getTask(taskIndex);
        task.markAsNotDone();
        return new CommandResult(
                String.format("OK, I've marked this task as not done yet:\n  %s", task),
                true
        );
    }

    /**
     * Indicates that this command does not signify an exit action.
     *
     * @return {@code false} as this command does not signify an exit action.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}