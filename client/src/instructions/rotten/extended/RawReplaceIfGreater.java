package instructions.rotten.extended;

import entities.Junker;

import java.io.Serializable;

public final class RawReplaceIfGreater extends RawReplaceIf implements Serializable {
    public static final String NAME = "replace_if_greater";
    public static final String BRIEF = "заменяет на новое значение по ключу [key], если оно больше старого";
    public static final String SYNTAX = NAME + " [key] {element}";
    public static final int ARGNUM = 2;

    /**
     * Конструктор, устанавливающий параметры
     * добавляемого объекта
     * @param key
     * @param junk
     */
    public RawReplaceIfGreater(Integer key, Junker junk) {
        super(key, junk);
    }

}
