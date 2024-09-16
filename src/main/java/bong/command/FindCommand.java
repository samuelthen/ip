package bong.command;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public CommandResult execute(TaskList tasks) {
        List<Map.Entry<Integer, Task>> matchingTasksWithIndex = IntStream.range(0, tasks.getList().size())
                .filter(i -> tasks.getList().get(i).getDescription().toLowerCase().contains(keyword))
                .mapToObj(i -> new AbstractMap.SimpleEntry<>(i + 1, tasks.getList().get(i)))
                .collect(Collectors.toList());

        return new CommandResult(formatMatchingTasks(matchingTasksWithIndex), false);
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
     * @param matchingTasksWithIndex The list of indexes and tasks that match the search keyword.
     * @return A formatted string of matching tasks or a message if none are found.
     */
    private String formatMatchingTasks(List<Map.Entry<Integer, Task>> matchingTasksWithIndex) {
        if (matchingTasksWithIndex.isEmpty()) {
            return "No matching tasks found.";
        }

        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (Map.Entry<Integer, Task> entry : matchingTasksWithIndex) {
            int actualIndex = entry.getKey();
            Task task = entry.getValue();

            sb.append(String.format("%d. %s \n", actualIndex, task));
        }
        return sb.toString();
    }

}
