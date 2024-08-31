package bong.command;

import bong.task.Task;
import bong.task.TaskList;
import bong.utils.BongException;

public class UnmarkCommand implements Command {
    private final int taskIndex;

    public UnmarkCommand(String args) throws BongException {
        try {
            this.taskIndex = Integer.parseInt(args.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new BongException("Please provide a valid bong.task number.");
        }
    }

    @Override
    public CommandResult execute(TaskList tasks) throws BongException {
        Task task = tasks.getTask(taskIndex);
        task.markAsNotDone();
        return new CommandResult(
                String.format("OK, I've marked this bong.task as not done yet:\n  %s", task),
                true
        );
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
