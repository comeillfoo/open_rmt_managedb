package dataSection;

import communication.Segment;

/**
 * Класс содержащий в себе все доступные для клиента команды.
 * Подразумевается при подключении к серверу пересылка этим сервером доступного списка команд.
 * Список команд будет зависеть от привелегий клиента.("Возможен будет удаленный админ")
 */
public class CommandList implements Commands {
    private String[] notArgumentedCommands;
    private String[] argumentedCommands;
    public CommandList(){
        //временное решение с установлением списка доступных команд
        notArgumentedCommands = new StringBuilder().append("help clear info show sum_of_annual_turnover").toString().split(" ");
        argumentedCommands = new StringBuilder().append("" +
                "insert remove_key update filter_contains_name max_by_date remove_lower ").toString().split(" ");
    }


    @Override
    public String[] getArgumenterCommandList() {
        return argumentedCommands;
    }

    @Override
    public String[] getNotArgumentedCommandList() {
        return notArgumentedCommands;
    }

    //метод для добавления списка команд отправленного сервером после установления соединения.
    @Override
    public void setList(Segment parcel) {
        //ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(parcel.getByteData()));
    }
}
