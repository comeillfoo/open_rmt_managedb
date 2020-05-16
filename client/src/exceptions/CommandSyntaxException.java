package exceptions;

/**
 * Исключение выбрасываемое во время проверки синтаксиса введенной команды.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public class CommandSyntaxException extends Exception {
    private String message;

    /**
     * Конструктор, принимающий контекст ошибки.
     * @param message
     */
    public CommandSyntaxException(String message) {
        this.message = message;
    }

    /**
     * Метод, выводящий контекст ошибки.
     * @return String
     */
    public String getMessage(){
        System.err.println(message);
        return message;
    }
}
