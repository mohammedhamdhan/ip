import java.util.Arrays;
import java.util.Scanner;

public class Orca {
    public static void main(String[] args) {
        String line = "----------------------------------------";
        String logo =
                // Each backslash in the ASCII art must be written as `\\` in the Java string.
                "  ___    ____     ____      _   \n"
                        + " / _ \\  |  _ \\   / ___|    / \\ \n"
                        + "| | | | | |_) | | |       / _ \\ \n"
                        + "| |_| | |  _ <  | |___   / ___ \\ \n"
                        + " \\___/  |_| \\_\\  \\____| /_/   \\_\\\n";

        System.out.println(logo +"\n" + line);
        System.out.println("Hello! I'm Orca \nWhat can I do for you today?\n" + line);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(line + "\n" + "Bye! Hope to see you again soon :)\n" + line);
                break;
            }
            System.out.println(line + "\n" + input + "\n" + line);
        }
    }
}
