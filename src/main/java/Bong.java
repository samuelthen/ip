import java.util.Scanner;
import java.util.ArrayList;

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
        ArrayList<String> list = new ArrayList<>();

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                System.out.println(line);
                System.out.println(endingMsg);
                System.out.println(line);
                break;
            } else if (userInput.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    int num = i + 1;
                    System.out.println("    " + num + ". " + list.get(i));
                }
            } else {
                System.out.println(line);
                System.out.println("    added: " + userInput);
                System.out.println(line);
                list.add(userInput);
            }
        }

        scanner.close();
    }
}
