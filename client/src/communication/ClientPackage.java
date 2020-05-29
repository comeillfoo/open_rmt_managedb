package communication;

import instructions.rotten.RawDecree;

import java.io.Serializable;
import java.util.Timer;

/**
 * Класс, объектами которого возможно будет осуществляться обмен с сервером.
 * @author Leargy aka Anton Sushkevich
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @see Serializable
 */
public class ClientPackage implements Serializable {
    private static final long serialVersionUID = 1L; //индификатор сериализации
    private RawDecree command;
    private Report report;

    /**
     * конструктор, принимающий единственный аргумент: объект "сырой" команды.
     * @param command
     */
    public ClientPackage(RawDecree command) {
        this.command = command;
    }

    /**
     * конструктор, принимающий объект "сырой" команды и Объект, содержащий отчет о работе сервера.
     * @param commandData
     * @param stringData
     */
    public ClientPackage(RawDecree commandData, Report stringData) {
        this(commandData);
        this.report = stringData;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public RawDecree getCommand() {
        return command;
    }

    public Report getReport() {return report; }
}
