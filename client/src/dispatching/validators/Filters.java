package dispatching.validators;

import entities.Checker;
import exceptions.NonComplianceException;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Filters {
    public static Double scanDouble(Checker<Double> requests, String message, Scanner scanner) {
        Double number = null;
        do {
            try {
                System.out.print(message);
                number = Double.valueOf(scanner.nextLine());
                if (!requests.check(number)) {
                    throw new NonComplianceException("\nNumber isn't match the demand.");
                }
            }catch (InputMismatchException | NumberFormatException | NonComplianceException e) {
                if (e.getClass().equals(NonComplianceException.class)) {
                    ((NonComplianceException) e).getMessage();
                }else {
                    System.err.println("\nEntered invalid number format.");
                }
            }
        } while (number == null || !requests.check(number));
        return number;
    }

    public static Long scanLong(Checker<Long> requests, String message, Scanner scanner) {
    Long number = null;
        do {
            try {
                System.out.print(message);
                number = Long.valueOf(scanner.nextLine());
                if (!requests.check(number)) {
                    throw new NonComplianceException("\nNumber isn't match the demand.");
                }
            }catch (InputMismatchException | NumberFormatException | NonComplianceException e) {
                if (e.getClass().equals(NonComplianceException.class)) {
                    ((NonComplianceException) e).getMessage();
                }else {
                    System.err.println("\nEntered invalid number format.");
                }
            }
        } while (number == null || !requests.check(number));
        return number;
    }

    public static String scanLine(Checker<String> requests, String message, Scanner scanner) {
        String line = null;
        do {
            try {
                System.out.print(message);
                line = scanner.nextLine();
                if (!requests.check(line)) {
                    throw new NonComplianceException("\nLine isn't match the demand.");
                }
            }catch (NonComplianceException e){
                if (e.getClass().equals(NonComplianceException.class)) {
                    e.getMessage();
                }else {
                    System.err.println("\nEntered invalid line format.");
                }
            }
        }while (!requests.check(line));
        return line;
    }
}
