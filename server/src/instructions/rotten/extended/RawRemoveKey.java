package instructions.rotten.extended;

import instructions.rotten.IClued;
import instructions.rotten.RawDecree;

public class RawRemoveKey extends RawDecree implements IClued<Integer> {
  protected final Integer key;

  public RawRemoveKey(Integer key) { this.key = key; }

  @Override
  public Integer Key() { return key; }

  public static final String NAME = "remove_key";
  public static final String BRIEF = "Удаляет элемент по [key].";
  public static final String SYNTAX = NAME + " [key]";
  public static final int ARGNUM = 2;
}
