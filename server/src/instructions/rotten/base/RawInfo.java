package instructions.rotten.base;

import instructions.rotten.RawDecree;

import java.io.Serializable;

public final class RawInfo extends RawDecree implements Serializable {
  public static final String NAME = "info";
  public static final String BRIEF = "выводит информацию о коллекции";
  public static final String SYNTAX = NAME;
  public static final int ARGNUM = 0;
}
