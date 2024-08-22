import java.util.Scanner;
import java.util.ArrayList;

abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public abstract String getTypeIcon(); // to be implemented by subclasses

    @Override
    public abstract String toString(); // to be implemented by subclasses
}

class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTypeIcon() {
        return "[T]";
    }

    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + description;
    }
}

class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + description + " (by: " + by + ")";
    }
}

class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + description + " (from: " + from + " to: " + to + ")";
    }
}

public class Bong {
    public static void main(String[] args) {
        String line = "    ____________________________________________________________";
        String openingMsg1 = "    Hello! I'm Bong";
        String openingMsg2 = "    What can I do for you?";
        String endingMsg = "    Bye. Hope to see you again soon!";

        System.out.println(line);
        System.out.println(openingMsg1);
        System.out.println(openingMsg2);
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        while (true) {
            String userInput = scanner.nextLine().trim();
            if (userInput.equals("bye")) {
                System.out.println(line);
                System.out.println(endingMsg);
                System.out.println(line);
                break;
            } else if (userInput.equals("list")) {
                System.out.println(line);
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    int num = i + 1;
                    System.out.println("     " + num + ". " + list.get(i));
                }
                System.out.println(line);
            } else if (userInput.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(userInput.substring(5)) - 1;
                if (taskNumber >= 0 && taskNumber < list.size()) {
                    list.get(taskNumber).markAsDone();
                    System.out.println(line);
                    System.out.println("     Nice! I've marked this task as done:");
                    System.out.println("       " + list.get(taskNumber));
                    System.out.println(line);
                } else {
                    System.out.println(line);
                    System.out.println("     Invalid task number!");
                    System.out.println(line);
                }
            } else if (userInput.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(userInput.substring(7)) - 1;
                if (taskNumber >= 0 && taskNumber < list.size()) {
                    list.get(taskNumber).markAsNotDone();
                    System.out.println(line);
                    System.out.println("     OK, I've marked this task as not done yet:");
                    System.out.println("       " + list.get(taskNumber));
                    System.out.println(line);
                } else {
                    System.out.println(line);
                    System.out.println("     Invalid task number!");
                    System.out.println(line);
                }
            } else if (userInput.startsWith("todo ")) {
                String taskDescription = userInput.substring(5).trim();
                Task newTask = new Todo(taskDescription);
                list.add(newTask);
                System.out.println(line);
                System.out.println("     Got it. I've added this task:");
                System.out.println("       " + newTask);
                System.out.println("     Now you have " + list.size() + " tasks in the list.");
                System.out.println(line);
            } else if (userInput.startsWith("deadline ")) {
                String[] parts = userInput.substring(9).split(" /by ");
                String taskDescription = parts[0].trim();
                String by = parts[1].trim();
                Task newTask = new Deadline(taskDescription, by);
                list.add(newTask);
                System.out.println(line);
                System.out.println("     Got it. I've added this task:");
                System.out.println("       " + newTask);
                System.out.println("     Now you have " + list.size() + " tasks in the list.");
                System.out.println(line);
            } else if (userInput.startsWith("event ")) {
                String[] parts = userInput.substring(6).split(" /from | /to ");
                String taskDescription = parts[0].trim();
                String from = parts[1].trim();
                String to = parts[2].trim();
                Task newTask = new Event(taskDescription, from, to);
                list.add(newTask);
                System.out.println(line);
                System.out.println("     Got it. I've added this task:");
                System.out.println("       " + newTask);
                System.out.println("     Now you have " + list.size() + " tasks in the list.");
                System.out.println(line);
            } else {
                System.out.println(line);
                System.out.println("     I'm sorry, I don't understand that command.");
                System.out.println(line);
            }
        }

        scanner.close();
    }
}
