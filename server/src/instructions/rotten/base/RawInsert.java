package instructions.rotten.base;

import entities.Junker;
import instructions.rotten.IJunked;
import instructions.rotten.IClued;
import instructions.rotten.RawCommitter;

public final class RawInsert extends RawCommitter implements IClued, IJunked {
    public static final String NAME = "insert";
    public static final String BRIEF = "Добавляет элемент с указанным [key] в колекцию.";
    public static final String SYNTAX = NAME + " [key] {element}";
    public static final int ARGNUM = 2;
    private final Integer KEY;

    public RawInsert(Integer key, Junker junk) {
        super(junk);
        KEY = key;
    }

    @Override
    public final Integer Key() {
        return KEY;
    }

    @Override
    public final Junker Params() {
        return JUNK;
    }
}
