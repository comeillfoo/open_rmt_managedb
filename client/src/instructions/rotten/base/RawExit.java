package instructions.rotten.base;

import instructions.rotten.RawDecree;

import java.io.Serializable;

/**
 * "Сырая" команда "exit".
 * содержит основную информацию о команде.
 * заканчивает работу приложения.
 */
public class RawExit extends RawDecree implements Serializable {
    public static final String NAME = "exit";
    public static final String BRIEF = "заканчивает работу приложения.";
    public static final String SYNTAX = NAME;
    public static final int ARGNUM = 0;

}
