package instructions.rotten.extended;

import entities.Junker;
import instructions.rotten.IClued;
import instructions.rotten.IJunked;
import instructions.rotten.RawCommitter;

import java.io.Serializable;

public abstract class RawReplaceIf extends RawCommitter implements IClued, IJunked, Serializable {
  protected final Integer KEY;

  /**
   * Конструктор, устанавливающий параметры
   * добавляемого объекта
   * @param junk
   */
  protected RawReplaceIf(Integer key, Junker junk) {
    super(junk);
    KEY = key;
  }

  @Override
  public final Junker Params() {
    return JUNK;
  }

  @Override
  public final Integer Key() { return KEY; }

}
