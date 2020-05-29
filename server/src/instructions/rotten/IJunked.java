package instructions.rotten;

import entities.Junker;

/**
 * интерфэйс для пометки команд, принимающих объекты организации в качестве аргумента.
 *  @author Come_1LL_F00 aka Lenar Khannanov
 *  @author Leargy aka Anton Sushkevich
 */
public interface IJunked {
    /**
     * Свойство возвращения данных об объекте организации.
     * @return Junker
     */
    Junker Params();
}
