package instructions.rotten.extended;

import entities.Junker;
import instructions.rotten.IClued;
import instructions.rotten.RawCommiter;

public class RawInsert extends RawCommiter implements IClued<Integer> {
  protected final Integer key;
  /**
   * Конструктор, устанавливающий параметры
   * добавляемого объекта и ключ добавления
   * @param key ключ добавляемого элемента
   * @param parameters инкапсуляция параметров объекта
   */
  protected RawInsert(Integer key, Junker parameters) {
    this.key = key;
  }

  @Override
  public Integer Key() {
    return key;
  }

  public static final String NAME = "insert";
  public static final String BRIEF = "Добавляет элемент с указанным [key] в колекцию.";
  public static final String SYNTAX = NAME + " [key] {element}";
  public static final int ARGNUM = 2;
}
