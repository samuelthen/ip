package bong.command;

import bong.task.Task;
import bong.task.TaskList;
import bong.utils.BongException;

public class DeleteCommand implements Command {
    private final int taskIndex;

    public DeleteCommand(String args) throws BongException {
        try {
            this.taskIndex = Integer.parseInt(args.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new BongException("Please provide a valid bong.task number.");
        }
    }

    @Override
    public CommandResult execute(TaskList tasks) throws BongException {
        Task removedTask = tasks.removeTask(taskIndex);
        return new CommandResult(
                String.format("Noted. I've removed this bong.task:\n  %s\nNow you have %d tasks in the list.",
                        removedTask, tasks.size()),
                true
        );
    }

    @Override
    public boolean isExit() {
        return false;
    }
}