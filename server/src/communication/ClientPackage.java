package communication;

import instructions.rotten.RawDecree;

import java.io.Serializable;

/**
 * Класс, объектами которого возможно будет осуществляться обмен с сервером.
 * @author Leargy aka Anton Sushkevich
 * @author Come_1LL_F00 aka Lenar Khannanov
 */
public class ClientPackage implements Serializable {
    private static final long serialVersionUID = 1L; //индификатор сериализации
    private RawDecree command;
    private Report report;

    /**
     * @param command
     */
    public ClientPackage(RawDecree command) {
        this.command = command;
    }

    /**
     * @param commandData
     * @param stringData
     */
    public ClientPackage(RawDecree commandData, Report stringData) {
        this(commandData);
        this.report = stringData;
    }

    /**
     * @param report
     */
    public void setReport(Report report) {
        this.report = report;
    }

    /**
     * @return RawDecree
     */
    public RawDecree getCommand() {
        return command;
    }

    /**
     * @return Report
     */
    public Report getReport() {return report;}
}