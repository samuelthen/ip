package bong.command;

import java.util.ArrayList;

import bong.task.Task;
import bong.task.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand implements Command {

    /**
     * Executes the command to list all tasks in the provided {@code TaskList}.
     *
     * @param tasks The {@code TaskList} from which tasks will be listed.
     * @return A {@code CommandResult} containing the list of tasks.
     */
    @Override
    public CommandResult execute(TaskList tasks) {
        return new CommandResult(formatTaskList(tasks.getList()), false);
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

    /**
     * Formats the task list into a string representation.
     *
     * @param list The {@code ArrayList} of {@code Task} objects to be formatted.
     * @return A string representation of the task list.
     */
    private String formatTaskList(ArrayList<Task> list) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, list.get(i)));
        }
        return sb.toString();
    }
}