package instructions.rotten;


import entities.Junker;
import instructions.Command;

import java.io.Serializable;

/**
 * Сырая абстракция всех комманд, добавляющих что-то в коллекцию.
 * Не содержит ссылку на исполнителя, однако имеет сборщик параметров
 * добавляемого объекта.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see RawDecree
 * @see Decree
 * @see Command
 */
public abstract class RawCommitter extends RawDecree implements Serializable {
    protected final Junker JUNK;
    /**
     * Конструктор, устанавливающий параметры
     * добавляемого объекта
     * @param parameters инкапсуляция параметров объекта
     */
    protected RawCommitter(Junker junk) {
        JUNK = junk;
    }
}
