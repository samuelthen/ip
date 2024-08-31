package bong.utils;

import bong.command.CommandResult;

import java.util.Scanner;

public class Ui {
    private static final String LINE = "    ____________________________________________________________";
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        this.showLine();
        System.out.println("    Hello! I'm bong.Bong");
        System.out.println("    What can I do for you?");
        this.showLine();
    }

    public void sayBye() {
        this.showLine();
        System.out.println("    Bye. Hope to see you again soon!");
        this.showLine();
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showCommandResult(CommandResult result) {
        this.showLine();
        System.out.println(result.getFeedbackToUser());
        this.showLine();
    }

    public void showError(String message) {
        this.showLine();
        System.out.println("     OOPS!!! " + message);
        this.showLine();
    }

    public void close() {
        scanner.close();
    }
}
