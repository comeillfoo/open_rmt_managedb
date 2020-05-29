package instructions.rotten.base;

import instructions.rotten.RawDecree;

import java.io.Serializable;

/**
 * "Сырая" команда "help".
 * содержит основную информацию о команде.
 * выводит справку по доступным командам.
 *  @author Come_1LL_F00 aka Lenar Khannanov
 *  @author Leargy aka Anton Sushkevich
 */
public final class RawHelp extends RawDecree implements Serializable {
    public static final String NAME = "help";
    public static final String BRIEF = "выводит справку по доступным командам.";
    public static final String SYNTAX = NAME;
    public static final int ARGNUM = 0;
}
