package instructions.rotten.base;

import instructions.rotten.RawDecree;

import java.io.Serializable;

public final class RawSave extends RawDecree implements Serializable {
  public static final String NAME = "save";
  public static final String BRIEF = "сохраняет коллекцию в файл";
  public static final String SYNTAX = NAME;
  public static final int ARGNUM = 0;
}
