package dataSection;

import java.io.Serializable;

/**
 * Класс, объектами которого возможно будет осуществляться обмен с сервером
 */
public class DataToServer implements Serializable {
    private static final long serialVersionUID = 1L; //индификатор сериализации
    private String[] commandWithArguments;
    private Serializable organization;
    private String errorReport;

    public DataToServer(String[] commandWithArguments){ this.commandWithArguments = commandWithArguments;}
    public DataToServer(String[] commandWithArguments,Serializable organization){ this(commandWithArguments); this.organization = organization;}

    public void setErrorReport(final String erroReport) {
        this.errorReport = erroReport;
    }

    public String[] getCommand() {
        return this.commandWithArguments;
    }
    public Serializable getOrganization() {
        return this.organization;
    }
    public String getErroReport(){ return errorReport;}
}
