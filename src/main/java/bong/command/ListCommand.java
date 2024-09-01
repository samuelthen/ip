package bong.command;

import java.util.ArrayList;

import bong.task.Task;
import bong.task.TaskList;

public class ListCommand implements Command {
    @Override
    public CommandResult execute(TaskList tasks) {
        return new CommandResult(formatTaskList(tasks.getList()), false);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private String formatTaskList(ArrayList<Task> list) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, list.get(i)));
        }
        return sb.toString();
    }
}
