package instructions.rotten.base;

import instructions.rotten.RawDecree;

import java.io.Serializable;

/**
 * "Сырая" команда "save".
 * содержит основную информацию о команде.
 * сохраняет коллекцию.Доступна только серверу.
 */
public final class RawSave extends RawDecree{
  public static final String NAME = "save";
  public static final String BRIEF = "сохраняет коллекцию в файл";
  public static final String SYNTAX = NAME;
  public static final int ARGNUM = 0;
}
