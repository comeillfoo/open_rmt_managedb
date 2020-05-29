package instructions.rotten;

/**
 * Обобщение для т.н. "сырых"
 * комманд, которые имеют дополнительный
 * (ключевой) параметр, который нужно учесть
 * при создании точной команды
 * @param <K> ключ элементов коллекции
 *  @author Come_1LL_F00 aka Lenar Khannanov
 *  @author Leargy aka Anton Sushkevich
 */
public interface IClued {
  /**
   * Свойство взятие ключа
   * @return ключ
   */
  Integer Key();
}
