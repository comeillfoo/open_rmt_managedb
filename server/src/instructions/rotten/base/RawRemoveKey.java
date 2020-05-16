package instructions.rotten.base;

import entities.Junker;
import instructions.rotten.IClued;
import instructions.rotten.RawCommitter;
import instructions.rotten.RawDecree;

import java.io.Serializable;

public final class RawRemoveKey extends RawDecree implements IClued, Serializable {
    public static final String NAME = "remove_key";
    public static final String BRIEF = "Удаляет элемент по [key].";
    public static final String SYNTAX = NAME + " [key]";
    public static final int ARGNUM = 1;
    private final Integer KEY;

    public RawRemoveKey(Integer key) { KEY = key; }

    @Override
    public final Integer Key() {
        return KEY;
    }
}
