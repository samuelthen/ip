package bong.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public abstract String getTypeIcon(); // to be implemented by subclasses

    @Override
    public abstract String toString(); // to be implemented by subclasses

    public String toFileString() {
        return (isDone ? "1" : "0") + " | " + description;
    }
}
