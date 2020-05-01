package dataSection;

import communication.Segment;

/**
 * Класс содержащий в себе все доступные для клиента команды.
 * Подразумевается при подключении к серверу пересылка этим сервером доступного списка команд.
 * Список команд будет зависеть от привелегий клиента.("Возможен будет удаленный админ")
 */
public class CommandList implements Commands {

    private final String[] notArgumentedCommands;
    private final String[] argumentedCommands;
    public CommandList() {
        //временное решение с установлением списка доступных команд
        this.notArgumentedCommands = "help clear info show sum_of_annual_turnover".split(" ");
        this.argumentedCommands = ("" +
                "insert remove_key update filter_contains_name max_by_date remove_lower ").split(" ");
    }



    @Override
    public String[] getArgumenterCommandList() {
        return this.argumentedCommands.clone();
    }

    @Override
    public String[] getNotArgumentedCommandList() {
        return this.notArgumentedCommands.clone();
    }

    //метод для добавления списка команд отправленного сервером после установления соединения.
    @Override
    public void setList(final Segment parcel) {
        //ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(parcel.getByteData()));
    }
}
