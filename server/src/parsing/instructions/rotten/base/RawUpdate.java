package parsing.instructions.rotten.base;

import entities.Junker;

public class RawUpdate extends RawCommiter implements IClued<Integer> {
  protected final Integer id;
  /**
   * Конструктор, устанавливающий параметры
   * добавляемого объекта
   *
   * @param parameters инкапсуляция параметров объекта
   */
  protected RawUpdate(Integer id, Junker parameters) {
    super(parameters);
    this.id = id;
  }

  @Override
  public Integer Key() { return id; }
}
