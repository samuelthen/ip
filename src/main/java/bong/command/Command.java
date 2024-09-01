package bong.command;

import bong.task.TaskList;

import bong.utils.BongException;

public interface Command {
    CommandResult execute(TaskList tasks) throws BongException;
    boolean isExit();
}