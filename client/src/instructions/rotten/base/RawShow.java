package instructions.rotten.base;

import instructions.rotten.RawDecree;

import java.io.Serializable;

/**
 * "Сырая" команда "show".
 * содержит основную информацию о команде.
 * выводит все элементы в stdout.
 */
public final class RawShow extends RawDecree implements Serializable {
  public static final String NAME = "show";
  public static final String BRIEF = "выводит все элементы в stdout.";
  public static final String SYNTAX = NAME;
  public static final int ARGNUM = 0;
}
