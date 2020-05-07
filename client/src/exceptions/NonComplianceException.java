package exceptions;

public class NonComplianceException extends Exception {
    private String exceptionMessage;
    public NonComplianceException(String exceptionMessage) {this.exceptionMessage = exceptionMessage;}

    public String getMessage() {
        System.err.println(exceptionMessage);
        return exceptionMessage;
    }
}
