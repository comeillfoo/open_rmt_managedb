package instructions.rotten;


import entities.Junker;
import instructions.Command;
import instructions.Decree;

/**
 * Сырая абстракция всех комманд, добавляющих что-то в коллекцию.
 * Не содержит ссылку на исполнителя, однако имеет сборщик параметров
 * добавляемого объекта.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see instructions.rotten.RawDecree
 * @see Decree
 * @see Command
 */
public abstract class RawCommiter extends RawDecree {
    protected Junker junker;
    /**
     * Конструктор, устанавливающий параметры
     * добавляемого объекта
     * @param parameters инкапсуляция параметров объекта
     */
    protected RawCommiter() {}
    /**
     * Свойство получения сборщика параметров
     * @return объект инкапсуляции параметров
     */
}
