package instructions.rotten.base;

import entities.Junker;
import instructions.rotten.IClued;
import instructions.rotten.IJunked;
import instructions.rotten.RawCommitter;

import java.io.Serializable;

/**
 * "Сырая" команда "update".
 * содержит основную информацию о команде.
 * Заменяет объект коллекции,соответствующий id, на новый, составленный пользователем.
 *  @author Come_1LL_F00 aka Lenar Khannanov
 *  @author Leargy aka Anton Sushkevich
 */
public final class RawUpdate extends RawCommitter implements IClued, IJunked, Serializable {
    public final static String NAME = "update";
    public static final String BRIEF = "заменяет объект коллекции,соответствующий id, на новый, составленный пользователем.";
    public static final String SYNTAX = "update [id] {element}";
    public static final int ARGNUM = 0;
    private final Integer ID;

    /**
     * конструктор команды, принимающий "id" объекта коллекции и данные об объекте коллекции.
     * @param id
     * @param junk
     */
    public RawUpdate(Integer id, Junker junk) {
        super(junk);
        ID = id;
    }

    /**
     * Возвращает "ключ" объекта.
     * @return Integer
     */
    @Override
    public final Integer Key() { return ID; }

    /**
     * Возвращает объект, содержащий данные об объекте коллекции.
     * @return Junker
     */
    @Override
    public final Junker Params() { return JUNK;}
}
