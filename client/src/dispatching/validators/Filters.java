package dispatching.validators;

import exceptions.NonComplianceException;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * Абстракный класс содержащий статические методв филтьров.
 * @author Leargy aka Anton Sushkevich
 * @author Come_1LL_F00 aka Lenar Khannanov
 */
public abstract class Filters {
    /**
     * Фильтр проверки полученного от пользователя очередного значения double.
     * @param requests функцианальный интерфэйс Predicate.
     * @param message сообщение выводимое повторно при запросе очередного знаений.
     * @param scanner
     * @return Double
     */
    public static Double scanDouble(Predicate<Double> requests, String message, Scanner scanner) {
        Double number = null;
        do {
            try {
                System.out.print(message);
                number = Double.valueOf(scanner.nextLine());
                if (!requests.test(number)) {
                    throw new NonComplianceException("\nNumber isn't match the demand.");
                }
            }catch (InputMismatchException | NumberFormatException | NonComplianceException e) {
                if (e.getClass().equals(NonComplianceException.class)) {
                    ((NonComplianceException) e).getMessage();
                }else {
                    System.err.println("\nEntered invalid number format.");
                }
            }
        } while (number == null || !requests.test(number));
        return number;
    }

    /**
     * Фильтр проверки полученного от пользователя очередного значения long.
     * @param requests функцианальный интерфэйс Predicate
     * @param message сообщение выводимое повторно при запросе очередного знаений.
     * @param scanner
     * @return Long
     */
    public static Long scanLong(Predicate<Long> requests, String message, Scanner scanner) {
    Long number = null;
        do {
            try {
                System.out.print(message);
                number = Long.valueOf(scanner.nextLine());
                if (!requests.test(number)) {
                    throw new NonComplianceException("\nNumber isn't match the demand.");
                }
            }catch (InputMismatchException | NumberFormatException | NonComplianceException e) {
                if (e.getClass().equals(NonComplianceException.class)) {
                    ((NonComplianceException) e).getMessage();
                }else {
                    System.err.println("\nEntered invalid number format.");
                }
            }
        } while (number == null || !requests.test(number));
        return number;
    }

    /**
     * Фильтр проверки полученного от пользователя очередного значения string.
     * @param requests функцианальный интерфэйс Predicate
     * @param message сообщение выводимое повторно при запросе очередного знаений.
     * @param scanner
     * @return String
     */
    public static String scanLine(Predicate<String> requests, String message, Scanner scanner) {
        String line = null;
        do {
            try {
                System.out.print(message);
                line = scanner.nextLine();
                if (!requests.test(line)) {
                    throw new NonComplianceException("\nLine isn't match the demand.");
                }
            }catch (NonComplianceException e){
                if (e.getClass().equals(NonComplianceException.class)) {
                    e.getMessage();
                }else {
                    System.err.println("\nEntered invalid line format.");
                }
            }
        }while (!requests.test(line));
        return line;
    }
}
