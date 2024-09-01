package bong.utils;

import bong.command.*;
import bong.task.Deadline;
import bong.task.Event;
import bong.task.Todo;

public class Parser {
    public Command parseCommand(String userInput) throws BongException {
        String[] parts = userInput.split(" ", 2);
        String commandWord = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1] : "";

        if (commandWord.equals("bye")) {
            return new ExitCommand();
        } else if (commandWord.equals("list")) {
            return new ListCommand();
        } else if (commandWord.equals("mark")) {
            return new MarkCommand(arguments);
        } else if (commandWord.equals("find")) {
            return new FindCommand(arguments);
        } else if (commandWord.equals("unmark")) {
            return new UnmarkCommand(arguments);
        } else if (commandWord.equals("delete")) {
            return new DeleteCommand(arguments);
        } else if (commandWord.equals("todo")) {
            return new AddCommand(new Todo(arguments, false));
        } else if (commandWord.equals("deadline")) {
            String[] subparts = arguments.split(" /by ");
            String taskDescription = subparts[0].trim();
            if (taskDescription.isEmpty()) {
                throw new BongException("The description of a deadline cannot be empty.");
            }
            String by = subparts[1].trim();
            return new AddCommand(new Deadline(taskDescription, by, false));
        } else if (commandWord.equals("event")) {
            String[] subparts = arguments.split(" /from | /to ");
            String taskDescription = subparts[0].trim();
            if (taskDescription.isEmpty()) {
                throw new BongException("The description of an event cannot be empty.");
            }
            String from = subparts[1].trim();
            String to = subparts[2].trim();
            return new AddCommand(new Event(taskDescription, from, to, false));
        } else {
            throw new BongException("I'm sorry, I don't understand that command.");
        }

    }
}