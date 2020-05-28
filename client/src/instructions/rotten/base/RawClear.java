package instructions.rotten.base;

import instructions.rotten.RawDecree;

import java.io.Serializable;

/**
 * "Сырая" команда "clear".
 * содержит основную информацию о команде.
 * очищает коллекцию.
 */
public final class RawClear extends RawDecree implements Serializable {
  public static final String NAME = "clear";
  public static final String BRIEF = "очищает коллекцию.";
  public static final String SYNTAX = NAME;
  public static final int ARGNUM = 0;

}
