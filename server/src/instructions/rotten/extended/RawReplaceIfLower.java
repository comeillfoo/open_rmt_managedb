package instructions.rotten.extended;

import entities.Junker;

import java.io.Serializable;

/**
 * "Сырая" команда "replace_if_lower".
 * содержит основную информацию о команде.
 * заменяет на новое значение по ключу [key], если оно меньше старого.
 *  @author Come_1LL_F00 aka Lenar Khannanov
 *  @author Leargy aka Anton Sushkevich
 */
public final class RawReplaceIfLower extends RawReplaceIf implements Serializable {
    public static final String NAME = "replace_if_lower";
    public static final String BRIEF = "заменяет на новое значение по ключу [key], если оно меньше старого.";
    public static final String SYNTAX = NAME + " [key] {element}";
    public static final int ARGNUM = 2;

    /**
     * Конструктор, устанавливающий параметры
     * добавляемого объекта
     * @param key
     * @param junk
     */
    public RawReplaceIfLower(Integer key, Junker junk) {
        super(key, junk);
    }
}
