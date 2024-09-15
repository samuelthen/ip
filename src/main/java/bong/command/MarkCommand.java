package bong.command;

import bong.task.Task;
import bong.task.TaskList;
import bong.utils.BongException;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand implements Command {
    private final int taskIndex;

    /**
     * Constructs a {@code MarkCommand} with the specified task index.
     *
     * @param args The index of the task to be marked as done, provided as a string.
     * @throws BongException If the provided index is not a valid integer or is out of bounds.
     */
    public MarkCommand(String args) throws BongException {
        try {
            this.taskIndex = Integer.parseInt(args.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new BongException("Please provide a valid task number.");
        }
    }

    /**
     * Executes the command to mark the specified task as done in the given {@code TaskList}.
     *
     * @param tasks The {@code TaskList} in which the task will be marked as done.
     * @return A {@code CommandResult} indicating the task has been marked as done.
     * @throws BongException If the task index is out of bounds.
     */
    @Override
    public CommandResult execute(TaskList tasks) throws BongException {
        Task task = tasks.getTask(taskIndex);
        task.markAsDone();
        return new CommandResult(
                String.format("Nice! I've marked this task as done:\n  %s", task),
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

