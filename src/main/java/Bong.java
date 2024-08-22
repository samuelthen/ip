import java.util.Scanner;

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

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                System.out.println(line);
                System.out.println(endingMsg);
                System.out.println(line);
                break;
            }

            System.out.println(line);
            System.out.println("    " + userInput);
            System.out.println(line);
        }

        scanner.close();
    }
}
