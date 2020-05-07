package communication;

import instructions.rotten.RawDecree;

import java.io.Serializable;

/**
 * Класс, объектами которого возможно будет осуществляться обмен с сервером
 */
public class ClientPackage implements Serializable {
    private static final long serialVersionUID = 1L; //индификатор сериализации
    private RawDecree command;
    private String commandReport;
    private String errorReport;

    public ClientPackage(RawDecree command) {
        this.command = command;
    }


    public ClientPackage(RawDecree commandData, String stringData) {
        this(commandData);
        this.commandReport = stringData;
    }

    public void setErrorReport(String errorReport) {
        this.errorReport = errorReport;
    }
    public void setCommandReport(String commandReport) { this.commandReport = commandReport;}

    public RawDecree getCommand() {
        return command;
    }
    public String getReport() {return commandReport;}
    public String getErrorReport() { return errorReport; }
}
