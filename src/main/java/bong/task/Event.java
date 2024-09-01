package bong.task;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents an event task with a specific start and end time in the Bong application.
 * Inherits from the {@code Task} class.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

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
     * Constructs a new {@code Event} task with the specified description, start and end time, and status.
     *
     * @param description The description of the event task.
     * @param from The start date and time for the event in string format.
     * @param to The end date and time for the event in string format.
     * @param isDone Whether the task is marked as done.
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);
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
     * @return The string representation of the event task, including its type, status, description, start, and end time.
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
