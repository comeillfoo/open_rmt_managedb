package instructions.rotten.extended;

import entities.Junker;
import instructions.rotten.IClued;
import instructions.rotten.IJunked;
import instructions.rotten.RawCommitter;

import java.io.Serializable;

public final class RawReplaceIfLower extends RawReplaceIf implements Serializable {
    public static final String NAME = "replace_if_lower";
    public static final String BRIEF = "заменяет на новое значение по ключу [key], если оно меньше старого";
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
