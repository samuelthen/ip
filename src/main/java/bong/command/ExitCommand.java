package bong.command;

import bong.task.TaskList;

public class ExitCommand implements Command {
    @Override
    public CommandResult execute(TaskList tasks) {
        return new CommandResult("Bye. Hope to see you again soon!", false);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
