package bong.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a generic task with a description and a completion status.
 */
public abstract class Task implements Comparable<Task> {

    private static final DateTimeFormatter[] FORMATTERS = {
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"), // e.g., "31/12/2023 2359"
            DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"), // e.g., "31/12/2023 23:59"
            DateTimeFormatter.ofPattern("d-M-yyyy HHmm"), // e.g., "31-12-2023 2359"
            DateTimeFormatter.ofPattern("d-M-yyyy HH:mm"), // e.g., "31-12-2023 23:59"
            DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"), // e.g., "31 Dec 2023 2359"
            DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"), // e.g., "31 Dec 2023 23:59"
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"), // e.g., "2023-12-31 2359"
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"), // e.g., "2023-12-31 23:59"
            DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"), // e.g., "Dec 31 2023 2359"
            DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"), // e.g., "Dec 31 2023 23:59"

            DateTimeFormatter.ofPattern("d/M/yyyy"), // e.g., "31/12/2023"
            DateTimeFormatter.ofPattern("d-M-yyyy"), // e.g., "31-12-2023"
            DateTimeFormatter.ofPattern("d MMM yyyy"), // e.g., "31 Dec 2023"
            DateTimeFormatter.ofPattern("yyyy-MM-dd"), // e.g., "2023-12-31"
            DateTimeFormatter.ofPattern("MMM dd yyyy"), // e.g., "Dec 31 2023"
    };
    protected String description;
    protected boolean isDone;
    private LocalDateTime sortingDate;

    /**
     * Constructs a new {@code Task} with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone The completion status of the task.
     */
    public Task(String description, boolean isDone, LocalDateTime sortingDate) {
        this.description = description;
        this.isDone = isDone;
        this.sortingDate = sortingDate;
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
     * @param isStartOfDay If true, the time is set to 00:00 for date-only inputs; otherwise, it's set to 23:59.
     * @return The parsed {@code LocalDateTime} object.
     * @throws IllegalArgumentException If the input string does not match any known date format.
     */
    public static LocalDateTime parseDateTime(String dateTimeString, boolean isStartOfDay) {
        for (DateTimeFormatter formatter : FORMATTERS) {
            LocalDateTime localDateTime = tryParseDateTime(dateTimeString, formatter, isStartOfDay);
            if (localDateTime != null) {
                return localDateTime;
            }
        }
        throw new IllegalArgumentException("Invalid date format: " + dateTimeString);
    }

    private static LocalDateTime tryParseDateTime(String dateTimeString,
                                                  DateTimeFormatter formatter,
                                                  boolean isStartOfDay) {
        try {
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            // Parsing failed, continue to the next format
        }

        try {
            LocalDate date = LocalDate.parse(dateTimeString, formatter);
            if (isStartOfDay) {
                return date.atTime(0, 0);
            } else {
                return date.atTime(23, 59);
            }
        } catch (DateTimeParseException e) {
            // Parsing failed, continue to the next format
        }
        return null;
    }

    @Override
    public int compareTo(Task other) {
        if (this.sortingDate == null && other.sortingDate == null) {
            return 0;
        }
        if (this.sortingDate == null) {
            return 1;
        }
        if (other.sortingDate == null) {
            return -1;
        }
        return this.sortingDate.compareTo(other.sortingDate);
    }
}
