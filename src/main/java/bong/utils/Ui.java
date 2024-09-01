package bong.utils;

import bong.command.CommandResult;

import java.util.Scanner;

/**
 * The {@code Ui} class handles all interactions with the user, including displaying messages,
 * reading input, and showing results.
 */
public class Ui {
    private static final String LINE = "    ____________________________________________________________";
    private Scanner scanner;

    /**
     * Constructs a new {@code Ui} object and initializes the scanner for user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        this.showLine();
        System.out.println("    Hello! I'm bong.Bong");
        System.out.println("    What can I do for you?");
        this.showLine();
    }

    /**
     * Displays the goodbye message to the user.
     */
    public void sayBye() {
        this.showLine();
        System.out.println("    Bye. Hope to see you again soon!");
        this.showLine();
    }

    /**
     * Reads a command from the user.
     *
     * @return The command string entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays a horizontal line to separate sections in the output.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays the result of executing a command to the user.
     *
     * @param result The {@code CommandResult} object containing feedback for the user.
     */
    public void showCommandResult(CommandResult result) {
        this.showLine();
        System.out.println(result.getFeedbackToUser());
        this.showLine();
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        this.showLine();
        System.out.println("     OOPS!!! " + message);
        this.showLine();
    }

    /**
     * Closes the scanner used for user input.
     */
    public void close() {
        scanner.close();
    }
}