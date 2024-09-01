package bong.command;

import bong.task.Task;
import bong.task.TaskList;

import java.util.List;
import java.util.stream.Collectors;

public class FindCommand implements Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public CommandResult execute(TaskList tasks) {
        List<Task> matchingTasks = tasks.getList().stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword))
                .collect(Collectors.toList());

        return new CommandResult(formatMatchingTasks(matchingTasks), false);
    }

    @Override
    public boolean isExit() {
        return false;
    }

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