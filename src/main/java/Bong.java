import java.util.Scanner;
import java.util.ArrayList;
import utils.BongException;
import utils.Deadline;
import utils.Event;
import utils.Task;
import utils.Todo;

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
            try {
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
                    handleMark(list, userInput.substring(5));
                } else if (userInput.startsWith("unmark ")) {
                    handleUnmark(list, userInput.substring(7));
                } else if (userInput.startsWith("todo ")) {
                    handleTodoTask(list, userInput.substring(5).trim());
                } else if (userInput.startsWith("deadline ")) {
                    handleDeadlineTask(list, userInput.substring(9).trim());
                } else if (userInput.startsWith("event ")) {
                    handleEventTask(list, userInput.substring(6).trim());
                } else if (userInput.startsWith("delete ")) {
                    handleDeleteTask(list, userInput.substring(7).trim());
                } else {
                    throw new BongException("I'm sorry, I don't understand that command.");
                }
            } catch (BongException e) {
                System.out.println(line);
                System.out.println("     OOPS!!! " + e.getMessage());
                System.out.println(line);
            } catch (Exception e) {
                System.out.println(line);
                System.out.println("     Something went wrong: " + e.getMessage());
                System.out.println(line);
            }
        }

        scanner.close();
    }

    private static void handleMark(ArrayList<Task> list, String input) throws BongException {
        try {
            int taskNumber = Integer.parseInt(input.trim()) - 1;
            if (taskNumber >= 0 && taskNumber < list.size()) {
                list.get(taskNumber).markAsDone();
                System.out.println("    ____________________________________________________________");
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       " + list.get(taskNumber));
                System.out.println("    ____________________________________________________________");
            } else {
                throw new BongException("Invalid task number!");
            }
        } catch (NumberFormatException e) {
            throw new BongException("Please provide a valid task number.");
        }
    }

    private static void handleUnmark(ArrayList<Task> list, String input) throws BongException {
        try {
            int taskNumber = Integer.parseInt(input.trim()) - 1;
            if (taskNumber >= 0 && taskNumber < list.size()) {
                list.get(taskNumber).markAsNotDone();
                System.out.println("    ____________________________________________________________");
                System.out.println("     OK, I've marked this task as not done yet:");
                System.out.println("       " + list.get(taskNumber));
                System.out.println("    ____________________________________________________________");
            } else {
                throw new BongException("Invalid task number!");
            }
        } catch (NumberFormatException e) {
            throw new BongException("Please provide a valid task number.");
        }
    }

    private static void handleTodoTask(ArrayList<Task> list, String taskDescription) throws BongException {
        if (taskDescription.isEmpty()) {
            throw new BongException("The description of a todo cannot be empty.");
        }
        Task newTask = new Todo(taskDescription);
        list.add(newTask);
        addedTaskMsg(newTask, list.size());
    }

    private static void handleDeadlineTask(ArrayList<Task> list, String taskDetails) throws BongException {
        try {
            String[] parts = taskDetails.split(" /by ");
            String taskDescription = parts[0].trim();
            if (taskDescription.isEmpty()) {
                throw new BongException("The description of a deadline cannot be empty.");
            }
            String by = parts[1].trim();
            Task newTask = new Deadline(taskDescription, by);
            list.add(newTask);
            addedTaskMsg(newTask, list.size());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BongException("The deadline task must have a valid '/by' date.");
        }
    }

    private static void handleEventTask(ArrayList<Task> list, String taskDetails) throws BongException {
        try {
            String[] parts = taskDetails.split(" /from | /to ");
            String taskDescription = parts[0].trim();
            if (taskDescription.isEmpty()) {
                throw new BongException("The description of an event cannot be empty.");
            }
            String from = parts[1].trim();
            String to = parts[2].trim();
            Task newTask = new Event(taskDescription, from, to);
            list.add(newTask);
            addedTaskMsg(newTask, list.size());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BongException("The event task must have valid '/from' and '/to' dates.");
        }
    }

    private static void handleDeleteTask(ArrayList<Task> list, String input) throws BongException {
        try {
            int taskNumber = Integer.parseInt(input.trim()) - 1;
            if (taskNumber >= 0 && taskNumber < list.size()) {
                Task removedTask = list.remove(taskNumber);
                System.out.println("    ____________________________________________________________");
                System.out.println("     Noted. I've removed this task:");
                System.out.println("       " + removedTask);
                System.out.println("     Now you have " + list.size() + " tasks in the list.");
                System.out.println("    ____________________________________________________________");
            } else {
                throw new BongException("Invalid task number!");
            }
        } catch (NumberFormatException e) {
            throw new BongException("Please provide a valid task number.");
        }
    }

    private static void addedTaskMsg(Task task, int listSize) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + listSize + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }
}
