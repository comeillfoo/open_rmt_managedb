package instructions.rotten.base;

import instructions.rotten.RawDecree;

/**
 * "Сырая" команда "exit".
 * содержит основную информацию о команде.
 * заканчивает работу приложения.
 */
public class RawExit extends RawDecree {
    public static final String NAME = "exit";
    public static final String BRIEF = "заканчивает работу приложения.";
    public static final String SYNTAX = NAME;
    public static final int ARGNUM = 0;

    @Override
    public String toString() { return NAME; }
}
