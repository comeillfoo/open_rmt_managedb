package communication;


import instructions.rotten.RawDecree;

import java.io.Serializable;

/**
 * Класс, объектами которого возможно будет осуществляться обмен с сервером
 */
public class ClientPackage implements Serializable {
    private static final long serialVersionUID = 1L; //индификатор сериализации

    private Junker organizationData;
    private RawDecree command;

    private String commandReport;
    private String errorReport;

    public ClientPackage(RawDecree command) {
        this.command = command;
    }
    public ClientPackage(RawDecree command, Junker organizationData) {
        this(command);
        this.organizationData = organizationData;
    }

    public ClientPackage(RawDecree commandData, Junker organizationData, String stringDatum) {
        this(commandData,organizationData);
        this.commandReport = stringDatum;
    }


    public void setErrorReport(String errorReport) {
        this.errorReport = errorReport;
    }
    public void setCommandReport(String commandReport) { this.commandReport = commandReport;}

    public RawDecree getCommand() {
        return command;
    }
    public Junker getOrganization() {
        return organizationData;
    }
    public String getReport() {return commandReport;}
    public String getErrorReport() { return errorReport; }
}
