package instructions.rotten.base;

import entities.Junker;
import instructions.rotten.IClued;
import instructions.rotten.IJunked;
import instructions.rotten.RawCommitter;

import java.io.Serializable;

/**
 * "Сырая" команда "insert".
 * содержит основную информацию о команде.
 * Добавляет элемент с указанным [key] в колекцию.
 */
public final class RawInsert extends RawCommitter implements IClued, IJunked, Serializable {
    public static final String NAME = "insert";
    public static final String BRIEF = "Добавляет элемент с указанным [key] в колекцию.";
    public static final String SYNTAX = NAME + " [key] {element}";
    public static final int ARGNUM = 2;
    private final Integer KEY;

    /**
     * конструктор команды, принимающий "ключ" объекта коллекции и данные об объекте коллекции.
     * @param key
     * @param junk
     */
    public RawInsert(Integer key, Junker junk) {
        super(junk);
        KEY = key;
    }

    /**
     * Возвращает "ключ" объекта.
     * @return Integer
     */
    @Override
    public final Integer Key() {
        return KEY;
    }

    /**
     * Возвращает объект, содержащий данные об объекте коллекции.
     * @return Junker
     */
    @Override
    public final Junker Params() {
        return JUNK;
    }
}
