package exceptions;

/**
 * Исключение выбрасываемое при несоблюдении некотрого условия.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public class NonComplianceException extends Exception {
    private String exceptionMessage;
    /**
     * Конструктор, принимающий контекст ошибки.
     * @param message
     */
    public NonComplianceException(String exceptionMessage) {this.exceptionMessage = exceptionMessage;}
    /**
     * Метод, выводящий контекст ошибки.
     * @return String
     */
    public String getMessage() {
        System.err.println(exceptionMessage);
        return exceptionMessage;
    }
}
