package bong.task;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a task with a specific deadline in the Bong application.
 * Inherits from the {@code Task} class.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

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
        this.by = parseDateTime(by);
    }

    /**
     * Parses the given date and time string into a {@code LocalDateTime} object.
     *
     * @param by The date and time string to be parsed.
     * @return The parsed {@code LocalDateTime} object.
     * @throws IllegalArgumentException If the input string does not match any known date format.
     */
    private LocalDateTime parseDateTime(String by) {
        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                return LocalDateTime.parse(by, formatter);
            } catch (DateTimeParseException e) {
                // If parsing fails, continue to try the next format
            }

            try {
                LocalDate date = LocalDate.parse(by, formatter);
                return date.atStartOfDay();
            } catch (DateTimeParseException e) {
                // If parsing fails, continue to try the next format
            }
        }
        throw new IllegalArgumentException("Invalid date format: " + by);
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
