package bong.command;

import bong.task.Task;
import bong.task.TaskList;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand implements Command {
    private final Task taskToAdd;

    /**
     * Constructs an {@code AddCommand} with the specified task to add.
     *
     * @param task The {@code Task} to be added to the task list.
     */
    public AddCommand(Task task) {
        this.taskToAdd = task;
    }

    /**
     * Executes the command to add the task to the given {@code TaskList}.
     *
     * @param tasks The {@code TaskList} to which the task will be added.
     * @return A {@code CommandResult} containing the feedback to the user and the modification status.
     */
    @Override
    public CommandResult execute(TaskList tasks) {
        tasks.addTask(taskToAdd);
        return new CommandResult(
                String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                        taskToAdd, tasks.size()),
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