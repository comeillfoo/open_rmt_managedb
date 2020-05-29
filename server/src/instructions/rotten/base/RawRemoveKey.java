package instructions.rotten.base;

import entities.Junker;
import instructions.rotten.IClued;
import instructions.rotten.RawCommitter;
import instructions.rotten.RawDecree;

import java.io.Serializable;

/**
 * "Сырая" команда "remove_key".
 * содержит основную информацию о команде.
 * Удаляет элемент по [key].
 *  @author Come_1LL_F00 aka Lenar Khannanov
 *  @author Leargy aka Anton Sushkevich
 */
public final class RawRemoveKey extends RawDecree implements IClued, Serializable {
    public static final String NAME = "remove_key";
    public static final String BRIEF = "Удаляет элемент по [key].";
    public static final String SYNTAX = NAME + " [key]";
    public static final int ARGNUM = 1;
    private final Integer KEY;

    /**
     * Конструктор, принимающий "ключ" объекта коллекции.
     * @param key
     */
    public RawRemoveKey(Integer key) { KEY = key; }

    /**
     * Возвращает "ключ" объекта.
     * @return Integer
     */
    @Override
    public final Integer Key() {
        return KEY;
    }
}
