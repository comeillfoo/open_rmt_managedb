package parsing.instructions.rotten.base;

/**
 * Обобщение для т.н. "сырых"
 * комманд, которые имеют дополнительный
 * (ключевой) параметр, который нужно учесть
 * при создании точной команды
 * @param <K> ключ элементов коллекции
 */
public interface IClued<K> {
  /**
   * Свойство взятие ключа
   * @return ключ
   */
  K Key();
}
