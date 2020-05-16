package instructions.rotten.extended;

import entities.Junker;
import instructions.rotten.IJunked;
import instructions.rotten.RawCommitter;

import java.io.Serializable;

public final class RawRemoveLower extends RawCommitter implements IJunked, Serializable {
    public static final String NAME = "remove_lower";
    public static final String BRIEF = "удаляет из коллекции элементы, меньшие чем заданный";
    public static final String SYNTAX = NAME + " {element}";
    public static final int ARGNUM = 1;

    /**
     * Конструктор, устанавливающий параметры
     * добавляемого объекта
     * @param junk
     */
    public RawRemoveLower(Junker junk) { super(junk); }

    @Override
    public final Junker Params() { return JUNK; }
}
