package parsing.instructions.rotten.base;

import entities.Junker;
import parsing.instructions.Command;
import parsing.instructions.Decree;
import parsing.instructions.rotten.RawDecree;

/**
 * Сырая абстракция всех комманд, добавляющих что-то в коллекцию.
 * Не содержит ссылку на исполнителя, однако имеет сборщик параметров
 * добавляемого объекта.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see RawDecree
 * @see Decree
 * @see Command
 */
public abstract class RawCommiter extends RawDecree {
  protected final Junker parameters;
  /**
   * Конструктор, устанавливающий параметры
   * добавляемого объекта
   * @param parameters инкапсуляция параметров объекта
   */
  protected RawCommiter(Junker parameters) { this.parameters = parameters; }
  /**
   * Свойство получения сборщика параметров
   * @return объект инкапсуляции параметров
   */
  public final Junker Params() { return parameters; }
}
