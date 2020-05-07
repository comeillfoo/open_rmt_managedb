package instructions.rotten.extended;

import entities.Junker;
import entities.IJunked;
import instructions.rotten.RawCommiter;

public class RawRemoveLower extends RawCommiter implements IJunked<Junker> {
    public static final String NAME = "remove_lower";
    public static final String BRIEF = "удаляет из коллекции элементы, меньшие чем заданный";
    public static final String SYNTAX = NAME + " {element}";
    public static final int ARGNUM = 1;

    @Override
    public Junker getData() {
        return junker;
    }

    public RawRemoveLower setData(Junker attachedData) {
        junker = attachedData;
        return this;
    }
}
