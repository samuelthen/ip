package bong.task;

import bong.utils.BongException;

import java.util.ArrayList;

/**
 * Manages a list of tasks, allowing for adding, retrieving, and removing tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a new {@code TaskList} with an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a new {@code TaskList} with the specified list of tasks.
     *
     * @param tasks The list of tasks to initialize the {@code TaskList}.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The {@code Task} to add to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves a task from the list by index.
     *
     * @param index The index of the task to retrieve.
     * @return The {@code Task} at the specified index.
     * @throws BongException If the index is out of bounds.
     */
    public Task getTask(int index) throws BongException {
        if (index < 0 || index >= tasks.size()) {
            throw new BongException("Invalid task number!");
        }
        return tasks.get(index);
    }

    /**
     * Removes a task from the list by index.
     *
     * @param index The index of the task to remove.
     * @return The {@code Task} that was removed.
     * @throws BongException If the index is out of bounds.
     */
    public Task removeTask(int index) throws BongException {
        if (index < 0 || index >= tasks.size()) {
            throw new BongException("Invalid task number!");
        }
        return tasks.remove(index);
    }

    /**
     * Returns a copy of the list of tasks.
     *
     * @return An {@code ArrayList} containing all tasks.
     */
    public ArrayList<Task> getList() {
        return new ArrayList<>(tasks);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }
}