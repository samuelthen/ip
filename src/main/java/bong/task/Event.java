package bong.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a specific start and end time in the Bong application.
 * Inherits from the {@code Task} class.
 */
public class Event extends Task {

    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new {@code Event} task with the specified description, start and end time, and status.
     *
     * @param description The description of the event task.
     * @param from The start date and time for the event in string format.
     * @param to The end date and time for the event in string format.
     * @param isDone Whether the task is marked as done.
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone, Task.parseDateTime(from, true));
        this.from = Task.parseDateTime(from, true);
        this.to = Task.parseDateTime(to, false);
    }

    /**
     * Returns the type icon representing an event task.
     *
     * @return The string "[E]" representing an event task.
     */
    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return The string representation of the event task (type, status, description, start, and end time).
     */
    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + description
                + " (from: " + from.format(DEFAULT_FORMATTER) + " to: " + to.format(DEFAULT_FORMATTER) + ")";
    }

    /**
     * Returns the formatted string to be saved in the file.
     *
     * @return The formatted string to be saved in the file, representing the event task.
     */
    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + from.format(DEFAULT_FORMATTER) + " | "
                + to.format(DEFAULT_FORMATTER);
    }
}
