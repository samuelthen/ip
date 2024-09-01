package bong.utils;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import bong.command.AddCommand;
import bong.command.Command;
import bong.command.DeleteCommand;
import bong.command.ExitCommand;
import bong.command.ListCommand;
import bong.command.MarkCommand;
import bong.command.UnmarkCommand;

public class ParserTest {

    private final Parser parser = new Parser();

    @Test
    public void testParseCommand_exitCommand() throws BongException {
        Command command = parser.parseCommand("bye");
        assertInstanceOf(ExitCommand.class, command);
    }

    @Test
    public void testParseCommand_listCommand() throws BongException {
        Command command = parser.parseCommand("list");
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    public void testParseCommand_markCommand() throws BongException {
        Command command = parser.parseCommand("mark 1");
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    public void testParseCommand_unmarkCommand() throws BongException {
        Command command = parser.parseCommand("unmark 1");
        assertInstanceOf(UnmarkCommand.class, command);
    }

    @Test
    public void testParseCommand_deleteCommand() throws BongException {
        Command command = parser.parseCommand("delete 1");
        assertInstanceOf(DeleteCommand.class, command);
    }

    @Test
    public void testParseCommand_todoCommand() throws BongException {
        Command command = parser.parseCommand("todo Read book");
        assertInstanceOf(AddCommand.class, command);
    }

    @Test
    public void testParseCommand_deadlineCommand() throws BongException {
        Command command = parser.parseCommand("deadline Submit report /by 2024-09-01");
        assertInstanceOf(AddCommand.class, command);
    }

    @Test
    public void testParseCommand_eventCommand() throws BongException {
        Command command = parser.parseCommand("event Conference /from 2024-09-01 /to 2024-09-03");
        assertInstanceOf(AddCommand.class, command);
    }

    @Test
    public void testParseCommand_unknownCommand() {
        BongException exception = assertThrows(BongException.class, () -> {
            parser.parseCommand("unknown command");
        });
        assertEquals("I'm sorry, I don't understand that command.", exception.getMessage());
    }
}