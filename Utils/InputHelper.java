package Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

    public class InputHelper {

        private static final Scanner scanner = new Scanner(System.in);

        // ------------------ String Input ------------------
        public static String getStringInput(String prompt) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            while (!HelperUtils.isValidString(input)) {
                System.out.println("⚠ Invalid input! Please enter a non-empty string.");
                System.out.print(prompt + " :");
                input = scanner.nextLine().trim();
            }
            return input;
        }

        // ------------------ Integer Input ------------------
        public static int getIntInput(String prompt) {
            System.out.print(prompt );
            String input = scanner.nextLine().trim();

            while (!HelperUtils.isValidString(input) || !input.matches("-?\\d+")) {
                System.out.println("⚠ Please enter a valid integer!");
                System.out.print(prompt+" :");
                input = scanner.nextLine().trim();
            }
            return Integer.parseInt(input);
        }

        // Overloaded with range validation
        public static int getIntInput(String prompt, int min, int max) {
            int num = getIntInput(prompt+" :");
            while (!HelperUtils.isValidNumber(num, min, max)) {
                System.out.println("⚠ Please enter a number between " + min + " and " + max + ".");
                num = getIntInput(prompt);
            }
            return num;
        }

        // ------------------ Double Input ------------------
        public static double getDoubleInput(String prompt) {
            System.out.print(prompt + " :");
            String input = scanner.nextLine().trim();

            while (!HelperUtils.isValidString(input) || !input.matches("-?\\d+(\\.\\d +)?")) {
                System.out.println("⚠ Please enter a valid number!");
                System.out.print(prompt + " :");
                input = scanner.nextLine().trim();
            }

            return Double.parseDouble(input);
        }

        // ------------------ Date Input ------------------
        public static LocalDate getDateInput(String prompt) {
            System.out.print(prompt + " (YYYY-MM-DD): ");
            String input = scanner.nextLine().trim();

            while (!HelperUtils.isValidDate(input)) {
                System.out.println("⚠ Invalid date format or invalid date!");
                System.out.print(prompt + " (YYYY-MM-DD): ");
                input = scanner.nextLine().trim();
            }

            return LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);
        }

        // ------------------ Confirmation Input ------------------
        public static boolean getConfirmation(String prompt) {
            System.out.print(prompt + " (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();

            while (!input.equals("y") && !input.equals("n")) {
                System.out.println("⚠ Please enter 'y' for Yes or 'n' for No.");
                System.out.print(prompt + " (y/n): ");
                input = scanner.nextLine().trim().toLowerCase();
            }

            return input.equals("y");
        }
    }


