package bong.command;

import bong.task.Task;
import bong.task.TaskList;
import bong.utils.BongException;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand implements Command {
    private final int taskIndex;

    /**
     * Constructs a {@code DeleteCommand} with the specified task index.
     *
     * @param args The index of the task to be deleted, provided as a string.
     * @throws BongException If the provided index is not a valid integer or is out of bounds.
     */
    public DeleteCommand(String args) throws BongException {
        try {
            this.taskIndex = Integer.parseInt(args.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new BongException("Please provide a valid task number.");
        }
    }

    /**
     * Executes the command to delete the task at the specified index from the given {@code TaskList}.
     *
     * @param tasks The {@code TaskList} from which the task will be removed.
     * @return A {@code CommandResult} containing the feedback to the user and the modification status.
     * @throws BongException If the index is out of bounds.
     */
    @Override
    public CommandResult execute(TaskList tasks) throws BongException {
        Task removedTask = tasks.removeTask(taskIndex);
        return new CommandResult(
                String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.",
                        removedTask, tasks.size()),
                true
        );
    }

    /**
     * Indicates whether this command signifies an exit action.
     *
     * @return {@code false} as this command does not signify an exit action.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}