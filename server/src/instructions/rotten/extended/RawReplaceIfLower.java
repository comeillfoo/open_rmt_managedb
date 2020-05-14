package instructions.rotten.extended;

import entities.Junker;
import instructions.rotten.IClued;
import instructions.rotten.IJunked;
import instructions.rotten.RawCommitter;

public final class RawReplaceIfLower extends RawReplaceIf {
    public static final String NAME = "replace_if_lower";
    public static final String BRIEF = "Находит элементы коллекции по заданному имени";
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
