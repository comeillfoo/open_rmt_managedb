package instructions.rotten.extended;

import instructions.rotten.ITitled;
import instructions.rotten.RawDecree;

public final class RawFilterContainsName extends RawDecree implements ITitled {
    public static final String NAME = "filter_contains_name";
    public static final String BRIEF = "Находит элементы коллекции по заданному имени";
    public static final String SYNTAX = NAME + " [name]";
    public static final int ARGNUM = 1;
    private final String TITLE;

    public RawFilterContainsName(String name) { TITLE = name; }

    @Override
    public final String Name() { return TITLE; }
}
