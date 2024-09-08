package bong.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a generic task with a description and a completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    private static final DateTimeFormatter[] FORMATTERS = {
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),       // e.g., "31/12/2023 2359"
            DateTimeFormatter.ofPattern("d-M-yyyy HHmm"),       // e.g., "31-12-2023 2359"
            DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"),    // e.g., "31 Dec 2023 2359"
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),    // e.g., "2023-12-31 23:59"
            DateTimeFormatter.ofPattern("d MMM yyyy"),          // e.g., "31 Dec 2023"
            DateTimeFormatter.ofPattern("d/M/yyyy"),            // e.g., "31/12/2023"
            DateTimeFormatter.ofPattern("d-M-yyyy"),            // e.g., "31-12-2023"
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),          // e.g., "2023-12-31"
            DateTimeFormatter.ofPattern("MMMM d, yyyy"),        // e.g., "December 31, 2023"
            DateTimeFormatter.ofPattern("MMM d, yyyy")          // e.g., "Dec 31, 2023"
    };

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

    /**
     * Parses the given date and time string into a {@code LocalDateTime} object.
     *
     * @param dateTimeString The date and time string to be parsed.
     * @return The parsed {@code LocalDateTime} object.
     * @throws IllegalArgumentException If the input string does not match any known date format.
     */
    public LocalDateTime parseDateTime(String dateTimeString) {
        for (DateTimeFormatter formatter : FORMATTERS) {
            LocalDateTime localDateTime = tryParseDateTime(dateTimeString, formatter);
            if (localDateTime != null) {
                return localDateTime;
            }
        }
        throw new IllegalArgumentException("Invalid date format: " + dateTimeString);
    }

    private LocalDateTime tryParseDateTime(String dateTimeString, DateTimeFormatter formatter) {
        try {
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            // Parsing failed, continue to the next format
        }

        try {
            LocalDate date = LocalDate.parse(dateTimeString, formatter);
            return date.atStartOfDay();
        } catch (DateTimeParseException e) {
            // Parsing failed, continue to the next format
        }

        return null;
    }
}