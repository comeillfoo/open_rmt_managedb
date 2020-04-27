package parsing.customer;

import entities.Mappable;

/**
 * Часть паттерна "Команда", классы которого реализуют
 * всю бизнес-логику обработки коллекции.
 * Каждый новенький Receiver, вылезая из пеленок, должен
 * мучиться с коллекцией и уметь в ней почти все:
 * <ol>
 *   <li>Добавлять элементы по ключу и признаку</li>
 *   <li>Удалять элементы по ключу и признаку</li>
 *   <li>Искать элементы по ключу и признаку</li>
 *   <li>Давать информацию об элементах, прошедших проверку</li>
 *   <li>Опустошать коллекцию</li>
 * </ol>
 * @author Leargy aka Anton Sushkevich
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @param <K> тип используемого в коллекции ключа
 * @param <V> тип элемента коллекции
 */
public interface Receiver<K, V extends Mappable<K>> {
  /**
   * Типичный about me на сайтах,
   * должен выдавать как можно больше
   * читабельной информации о хранимой коллекции
   * @return текстовая информация о коллекции
   */
  String review();

  /**
   *
   * @param key
   * @param element
   * @param menace
   */
  void add(K key, V element, Indicator menace);

  /**
   *
   * @param key
   * @param element
   * @param menace
   */
  void remove(K key, V element, Indicator menace);

  /**
   *
   * @param key
   * @param element
   * @param menace
   */
  void search(K key, V element, Indicator menace);

  /**
   * Делаем обзор на коллекцию, фильтруя базар
   * @param menace this very фильтр
   * @return текстовая информация об элементах
   */
  String survey(Indicator menace);

  /**
   * Убирать за собой - тоже нужно уметь
   */
  void clear();
}
