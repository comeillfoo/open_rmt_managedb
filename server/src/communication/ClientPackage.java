package communication;

import instructions.rotten.RawDecree;

import java.io.Serializable;

/**
 * Класс, объектами которого
 * осуществляется обмен между клиентом
 * и сервером
 * @author Leargy aka Anton Sushkevich
 * @author Come_1LL_F00 aka Lenar Khannanov
 */
public class ClientPackage implements Serializable {
    private static final long serialVersionUID = 1L; // идентификатор сериализации
    private RawDecree command;
    private Report report;

    /**
     * Базовый конструктор,
     * принимающий сырую команду
     * в качестве клиентского запроса
     * @param command сырая команда
     */
    public ClientPackage(RawDecree command) {
        this.command = command;
    }

    /**
     * Допконструктор, принимающий
     * сырую команду в качестве запроса,
     * и отчет о выполнении команды, в
     * качестве результата работы команды
     * @param commandData клиентский запрос
     * @param stringData отчет о работе
     */
    public ClientPackage(RawDecree commandData, Report stringData) {
        this(commandData);
        this.report = stringData;
    }

    /**
     * Сеттер для установки
     * кастомного отчета. Честно,
     * не фанат такого. Даже никакой
     * логики нет.
     * @param report заменяемый отчет
     */
    public void setReport(Report report) {
        this.report = report;
    }

    /**
     * Свойство взятия подготавливаемой команды,
     * требующей исполнения
     * @return клиентский запрос
     */
    public RawDecree getCommand() {
        return command;
    }

    /**
     * Свойство взятия результата работы
     * @return Report отчетность о результатах работы
     */
    public Report getReport() {return report;}
}