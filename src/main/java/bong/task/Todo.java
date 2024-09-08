package bong.task;

/**
 * Represents a to-do task that needs to be completed.
 */
public class Todo extends Task {
    /**
     * Constructs a new {@code Todo} task with the specified description and completion status.
     *
     * @param description The description of the to-do task.
     * @param isDone The completion status of the task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone, null);
    }

    @Override
    public String getTypeIcon() {
        return "[T]";
    }

    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + description;
    }

    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }
}
