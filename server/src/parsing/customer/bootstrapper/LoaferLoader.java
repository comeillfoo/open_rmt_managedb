package parsing.customer.bootstrapper;

import entities.Mappable;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Скелет подгрузчика коллекции и этим все сказано
 * @param <V> тип элементов подгружаемой коллекции
 */
public interface LoaferLoader<V extends Mappable> {
  List<V> load();
  void unload(List<V> elements);
  /**
   * Метод выполняющий проверку на правильное заполнение файла или наличие кошачьего наполнителя в файле.
   * Если не будут заполнены обязательные поля, метод заполняет их значениями по умолчанию.
   * @param foil я задолбался опечатываться, так что оставлю с таким названием, так смешнее
   * @return true/false
   * @throws IOException типичное исключение системы ввода/выводы, которое, как неприступная дева, никогда ничего о себе не расскажет
   */
  boolean checkFile(File foil) throws IOException;

  /**
   * Свойство записи названия
   * переменной окружения в поле
   * @param varName название переменной окружения
   */
  void Environment(String varName);

  /**
   * Свойство получения,
   * признака того, что коллекция
   * уже подгружена
   * @return признак загрузки коллекции
   */
  boolean Loaded();

  /**
   * Свойство для получения даты создания файла
   * @return строковое представление даты создания файла или загрузчика
   */
  String Birth();
}
