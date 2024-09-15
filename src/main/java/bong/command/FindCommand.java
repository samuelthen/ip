package bong.command;

import java.util.List;
import java.util.stream.Collectors;

import bong.task.Task;
import bong.task.TaskList;

/**
 * Represents a command that finds tasks in the task list that contain a given keyword.
 */
public class FindCommand implements Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    /**
     * Executes the FindCommand by searching for tasks in the task list that contain the keyword.
     *
     * @param tasks The list of tasks to search through.
     * @return A CommandResult containing a message with the matching tasks or a message indicating
     *         no matches were found.
     */
    @Override
    public CommandResult execute(TaskList tasks) {
        List<Task> matchingTasks = tasks.getList().stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword))
                .collect(Collectors.toList());

        return new CommandResult(formatMatchingTasks(matchingTasks), false);
    }

    /**
     * Indicates whether this command should exit the program.
     *
     * @return false, as the FindCommand does not cause the program to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Formats the list of matching tasks into a string. If no tasks match, it returns
     * a message indicating that no matching tasks were found.
     *
     * @param matchingTasks The list of tasks that match the search keyword.
     * @return A formatted string of matching tasks or a message if none are found.
     */
    private String formatMatchingTasks(List<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            return "No matching tasks found.";
        }

        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            sb.append(String.format("%d.%s %s\n", i + 1, matchingTasks.get(i).getTypeIcon(), matchingTasks.get(i)));
        }
        return sb.toString();
    }
}
