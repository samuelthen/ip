package bong.task;

/**
 * Represents a generic task with a description and a completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new {@code Task} with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone The completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon representing whether the task is completed.
     *
     * @return The status icon as "[X]" if the task is completed, otherwise "[ ]".
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns the type icon representing the type of task.
     * To be implemented by subclasses.
     *
     * @return The type icon as a string.
     */
    public abstract String getTypeIcon();

    @Override
    public abstract String toString(); // to be implemented by subclasses

    /**
     * Returns a string representation of the task for file storage.
     *
     * @return The string representation of the task suitable for file storage.
     */
    public String toFileString() {
        return (isDone ? "1" : "0") + " | " + description;
    }
}