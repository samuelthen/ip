package bong.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Represents a task with a specific deadline in the Bong application.
 * Inherits from the {@code Task} class.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /**
     * Constructs a new {@code Deadline} task with the specified description, deadline, and status.
     *
     * @param description The description of the deadline task.
     * @param by The deadline date and time for the task in string format.
     * @param isDone Whether the task is marked as done.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = super.parseDateTime(by);
    }

    /**
     * Returns the type icon representing a deadline task.
     *
     * @return The string "[D]" representing a deadline task.
     */
    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return The string representation of the deadline task, including its type, status, description, and deadline.
     */
    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + description + " (by: " + by.format(DEFAULT_FORMATTER) + ")";
    }

    /**
     * Returns the formatted string to be saved in the file.
     *
     * @return The formatted string to be saved in the file, representing the deadline task.
     */
    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + by.format(DEFAULT_FORMATTER);
    }
}
