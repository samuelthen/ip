package bong.utils;

import bong.command.AddCommand;
import bong.command.Command;
import bong.command.DeleteCommand;
import bong.command.ExitCommand;
import bong.command.ListCommand;
import bong.command.MarkCommand;
import bong.command.UnmarkCommand;

import bong.task.Deadline;
import bong.task.Event;
import bong.task.Todo;

/**
 * The {@code Parser} class is responsible for parsing user input into commands
 * that the Bong application can execute.
 */
public class Parser {
    /**
     * Parses the user input and returns the corresponding {@code Command} object.
     *
     * @param userInput The input string provided by the user.
     * @return The {@code Command} object representing the user's command.
     * @throws BongException If the user input cannot be parsed into a valid command.
     */
    public Command parseCommand(String userInput) throws BongException {
        String[] parts = userInput.split(" ", 2);
        String commandWord = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (commandWord) {
            case "bye" -> {
                return new ExitCommand();
            }
            case "list" -> {
                return new ListCommand();
            }
            case "mark" -> {
                return new MarkCommand(arguments);
            }
            case "unmark" -> {
                return new UnmarkCommand(arguments);
            }
            case "delete" -> {
                return new DeleteCommand(arguments);
            }
            case "todo" -> {
                return new AddCommand(new Todo(arguments, false));
            }
            case "deadline" -> {
                String[] subparts = arguments.split(" /by ");
                String taskDescription = subparts[0].trim();
                if (taskDescription.isEmpty()) {
                    throw new BongException("The description of a deadline cannot be empty.");
                }
                String by = subparts[1].trim();
                return new AddCommand(new Deadline(taskDescription, by, false));
            }
            case "event" -> {
                String[] subparts = arguments.split(" /from | /to ");
                String taskDescription = subparts[0].trim();
                if (taskDescription.isEmpty()) {
                    throw new BongException("The description of an event cannot be empty.");
                }
                String from = subparts[1].trim();
                String to = subparts[2].trim();
                return new AddCommand(new Event(taskDescription, from, to, false));
            }
            default -> throw new BongException("I'm sorry, I don't understand that command.");
        }
    }
}