package instructions.rotten.extended;

import instructions.rotten.RawDecree;

import java.io.Serializable;

/**
 * "Сырая" команда "max_by_date".
 * содержит основную информацию о команде.
 * находит самый старый элемент коллекции.
 */
public final class RawMaxByDate extends RawDecree implements Serializable {
    public static final String NAME = "max_by_date";
    public static final String BRIEF = "находит самый старый элемент коллекции.";
    public static final String SYNTAX = NAME;
    public static final int ARGNUM = 0;
}
