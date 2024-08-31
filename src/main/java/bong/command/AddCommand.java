package bong.command;

import bong.task.Task;
import bong.task.TaskList;

public class AddCommand implements Command {
    private final Task taskToAdd;

    public AddCommand(Task task) {
        this.taskToAdd = task;
    }

    @Override
    public CommandResult execute(TaskList tasks) {
        tasks.addTask(taskToAdd);
        return new CommandResult(
                String.format("Got it. I've added this bong.task:\n  %s\nNow you have %d tasks in the list.",
                        taskToAdd, tasks.size()),
                true
        );
    }

    @Override
    public boolean isExit() {
        return false;
    }
}