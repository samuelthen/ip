package bong.utils;

/**
 * Represents a custom exception specific to the Bong application.
 */
public class BongException extends Exception {
    /**
     * Constructs a new {@code BongException} with the specified detail message.
     *
     * @param message The detail message for this exception.
     */
    public BongException(String message) {
        super(message);
    }
}