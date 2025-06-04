package ZooManagementSystem.Utils;

import java.util.Scanner;

public class InputUtil {

    public static String promptString(Scanner input, String message) {
        System.out.println(message);
        return input.nextLine();
    }

    public static double promptPositiveDouble(Scanner input, String message, String errorMessage) {
        double value;
        while (true) {
            System.out.println(message);
            String line = input.nextLine();
            try {
                value = Double.parseDouble(line);
                if (value > 0) {
                    return value;
                }
                System.out.println(errorMessage);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public static int promptIntInRange(Scanner input, String message, String errorMessage, int min, int max) {
        int value;
        while (true) {
            System.out.println(message);
            String line = input.nextLine();
            try {
                value = Integer.parseInt(line);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println(errorMessage + " (Expected range: " + min + "-" + max + ")");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
    }
}