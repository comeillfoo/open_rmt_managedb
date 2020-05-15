package instructions.rotten.base;

import instructions.rotten.RawDecree;

import java.io.Serializable;

public final class RawHelp extends RawDecree implements Serializable {
    public static final String NAME = "help";
    public static final String BRIEF = "выводит справку по доступным командам";
    public static final String SYNTAX = NAME;
    public static final int ARGNUM = 0;

}
