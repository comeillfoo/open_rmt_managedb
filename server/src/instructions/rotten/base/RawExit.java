package instructions.rotten.base;

import instructions.rotten.RawDecree;

public class RawExit extends RawDecree {
    public static final String NAME = "exit";
    public static final String BRIEF = "завершить программу";
    public static final String SYNTAX = NAME;
    public static final int ARGNUM = 0;

    @Override
    public String toString() { return NAME; }
}
