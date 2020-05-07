package instructions.rotten.extended;

import entities.Junker;
import instructions.rotten.IClued;
import instructions.rotten.RawCommiter;

public class RawUpdate extends RawCommiter implements IClued<Integer> {
  protected final Integer id;
  /**
   * Конструктор, устанавливающий параметры
   * добавляемого объекта
   *
   * @param parameters инкапсуляция параметров объекта
   */
  protected RawUpdate(Integer id, Junker parameters) {
    this.id = id;
  }

  @Override
  public Integer Key() { return id; }
}
