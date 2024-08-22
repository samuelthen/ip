import java.util.Scanner;
import java.util.ArrayList;

class Task {
    private String description;
    private boolean isDone;

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
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
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
            } else {
                Task newTask = new Task(userInput);
                list.add(newTask);
                System.out.println(line);
                System.out.println("     added: " + userInput);
                System.out.println(line);
            }
        }

        scanner.close();
    }
}
