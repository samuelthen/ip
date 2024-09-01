package bong.task;

import java.util.ArrayList;

import bong.utils.BongException;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) throws BongException {
        if (index < 0 || index >= tasks.size()) {
            throw new BongException("Invalid bong.task number!");
        }
        return tasks.get(index);
    }

    public Task removeTask(int index) throws BongException {
        if (index < 0 || index >= tasks.size()) {
            throw new BongException("Invalid bong.task number!");
        }
        return tasks.remove(index);
    }

    public ArrayList<Task> getList() {
        return new ArrayList<>(tasks);
    }

    public int size() {
        return tasks.size();
    }
}