package bong.utils;

import bong.command.AddCommand;
import bong.command.Command;
import bong.command.DeleteCommand;
import bong.command.ExitCommand;
import bong.command.FindCommand;
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
    private static final String SPLIT_REGEX = " ";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String FIND = "find";
    private static final String LIST = "list";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String DELETE = "delete";
    private static final String TODO = "todo";
    private static final String DESCRIPTION_EMPTY_MSG = "The description of %s cannot be empty.";
    private static final String UNKNOWN_COMMAND_MSG = "I'm sorry, I don't understand that command.";

    /**
     * Parses the user input and returns the corresponding {@code Command} object.
     *
     * @param userInput The input string provided by the user.
     * @return The {@code Command} object representing the user's command.
     * @throws BongException If the user input cannot be parsed into a valid command.
     */
    public Command parseCommand(String userInput) throws BongException {
        String[] parts = userInput.split(SPLIT_REGEX, 2);
        String commandWord = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (commandWord) {
            case "bye":
                return new ExitCommand();
            case FIND:
                return new FindCommand(arguments);
            case LIST:
                return new ListCommand();
            case MARK:
                return new MarkCommand(arguments);
            case UNMARK:
                return new UnmarkCommand(arguments);
            case DELETE:
                return new DeleteCommand(arguments);
            case TODO:
                return createTodoCommand(arguments);
            case DEADLINE:
                return createDeadlineCommand(arguments);
            case EVENT:
                return createEventCommand(arguments);
            default:
                throw new BongException(UNKNOWN_COMMAND_MSG);
        }
    }

    private Command createTodoCommand(String arguments) {
        return new AddCommand(new Todo(arguments, false));
    }

    private Command createDeadlineCommand(String arguments) throws BongException {
        String[] subparts = arguments.split(" /by ");
        validateDescription(subparts[0].trim(), DEADLINE);
        String by = subparts[1].trim();
        return new AddCommand(new Deadline(subparts[0].trim(), by, false));
    }

    private Command createEventCommand(String arguments) throws BongException {
        String[] subparts = arguments.split(" /from | /to ");
        validateDescription(subparts[0].trim(), EVENT);
        String from = subparts[1].trim();
        String to = subparts[2].trim();
        return new AddCommand(new Event(subparts[0].trim(), from, to, false));
    }

    private void validateDescription(String description, String type) throws BongException {
        if (description.isEmpty()) {
            throw new BongException(String.format(DESCRIPTION_EMPTY_MSG, type));
        }
    }
}