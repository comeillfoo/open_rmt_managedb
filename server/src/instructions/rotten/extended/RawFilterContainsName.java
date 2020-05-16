package instructions.rotten.extended;

import instructions.rotten.ITitled;
import instructions.rotten.RawDecree;

import java.io.Serializable;

public final class RawFilterContainsName extends RawDecree implements ITitled, Serializable {
    public static final String NAME = "filter_contains_name";
    public static final String BRIEF = "Находит элементы коллекции по заданному имени";
    public static final String SYNTAX = NAME + " [name]";
    public static final int ARGNUM = 1;
    private final String TITLE;

    public RawFilterContainsName(String name) { TITLE = name; }

    @Override
    public final String Name() { return TITLE; }
}
