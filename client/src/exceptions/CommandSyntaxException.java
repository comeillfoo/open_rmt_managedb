package exceptions;

/**
 * Исключение выбрасываемое во время проверки синтаксиса введенной команды.
 */
public class CommandSyntaxException extends Exception {
    private String message;

    public CommandSyntaxException(String message) {
        this.message = message;
    }

    public String getMessage(){
        System.err.println(message);
        return message;
    }
}
