package parsing.customer.local;

import entities.Mappable;
import entities.Organization;
import czerkaloggers.RadioLogger;
import parsing.customer.Indicator;
import parsing.customer.Receiver;
import parsing.customer.bootstrapper.LoaferLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Конкретный пацан в паттерне. Оперирует организациями, как
 * своими методами. Знает: какую реализация предложить, дабы
 * исполнить любую прихоть клиента.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Receiver
 * @see Commander
 */
public class TotalCommander extends Commander<Integer, Organization> {
  // builders

  /**
   * Стандартный конструктор
   * менеджера коллекции
   * @param logger
   */
  public TotalCommander(LoaferLoader<Organization> loader, RadioLogger logger) { super(loader, logger); }

  /**
   * Пояснит за коллекцию и ее элементы. Помнит даже
   * дату рождения своей подопечной и ее корни
   * @return текстовая информация о хранимой коллекции
   */
  @Override public String review() {
    Class studyee = database.getClass();
    StringBuilder info = new StringBuilder("\t* Collection canonical name: " + studyee.getCanonicalName() + ";\n");
    info.append("\t* Collection creation date: " + creationDate + ";\n");
    info.append("\t* Collection element type name: Organization extends Mappable<Integer>;\n");
    info.append("\t* Is collection empty?: " + database.isEmpty() + ";\n");
    info.append("\t* Collection size: " + database.size() + ";");
    peacher().logboard(0, "Данные о коллекции успешно предоставлены");
    return info.toString();
  }

  /**
   * Обший метод для добавления элемента в коллекцию
   * @param key ключ элемента, на который пишется элемент
   * @param value записываемый элемент
   * @param menace  признак, по которому данный элемент должен добавится
   */
  @Override
  public void add(Integer[] key, Organization[] value, Indicator menace) {
    // проверка: добавляем ли мы ошибку природы
    if (value[0] != null) {
      // проверка: нужно ли добавлять данное детище
      if (menace.verify(value[0]))
        value[0] = database.put(key[0] == null? value[0].Key() : key[0], value[0]);
      else peacher().notify(3, "Не удалось добавить элемент: не удовлетворяет условию");
    } else peacher().notify(1, "Обнаружена попытка добавления пустого элемента: выполнение метода прервано");
  }

  /**
   * Общий метод удаления элемента из коллекции:
   * Три режима удаления
   * <ul>
   *   <li>Удаление по ключу</li>
   *   <li>Удаление по элменту</li>
   *   <li>Удаление по ключу и элементу</li>
   * </ul>
   * 1. Удаление по ключу включает:
   * <ul>
   *   <li>Проверка: есть ли такой ключ в базе</li>
   *   <li>Удаление элемента</li>
   * </ul>
   * Этот режим не предполагает пользовательских проверок на соответствие элемента
   * 2. Удаление по элементу:
   * <ul>
   *   <li>Проверка: есть ли такой элемент в коллекции</li>
   *   <li>Поиск ключа этого элемента</li>
   *   <li>Проверка: подходит ли наш элемент под заданные условия</li>
   *   <li>Удаление элемента</li>
   *   <li>Проверка: а то ли мы вообще удалили</li>
   * </ul>
   * Данный режим уже предполагает проверки, на соответсвие признаку
   * 3. Миксованное удаление (диджей *бан):
   * <ul>
   *   <li>Проверка: содержатся ли предоставленные данные одновременно в коллекции (хотя бы даже не в связке)</li>
   *   <li>Проверка: есть ли связка ключа и элемента</li>
   *   <li>Удаление</li>
   * </ul>
   * @param key ключ, по которому происходит удаление
   * @param value удаляемый элемент
   * @param menace признак того, нужно ли удалять данный элемент
   */
  @Override
  public void remove(Integer[] key, Organization[] value, Indicator menace) {
    // проверяем нужно ли удалять элемент по ключу
    if ((key[0] != null) && (value[0] == null)) {
      // проверяем есть ли такой ключ в базе
      if (database.containsKey(key[0])) {
        // выбрасываем этот мусор из коллекции, и, чтобы подстраховаться, даем ссылку команде, ради дальнейших проверок
        value[0] = database.remove(key[0]);
        peacher().notify(0, "Элемент по ключу " + key[0] + " удален");
      } else peacher().notify(3, "Невозможно удалить элемент: ключ " + key[0] + " не найден");
    } else if ((key[0] == null) && (value[0] != null)) {
      // проверили: нужно ли удалить элемент по значению и дальше смотрим, а есть ли эта тварь в коллекции вообще
      if (database.containsValue(value[0])) {
        // если все-таки есть, то пытаемся найти ключ этой падлы
        search(key, value, (v)->(true)); // а чо зря функцию писал, вот и понадобилась
        // делаем проверку: нужно ли удалять этот элемент
        if (menace.verify(value[0])) {
          // удаляем и сверяем с требуемым на удаление
          if (value[0].equals(database.remove(key[0])))
            peacher().notify(0, "Элемент " + value[0] + " успешно удален");
          else peacher().notify(2, "Случайно удален не тот элемент, данные будут восстановлены");
        } else peacher().notify(3, "Невозможно стереть элемент: не удовлетворяет условию удаления");
      } else peacher().notify(1, "Нельзя удалить элемент " + value[0] + ": из-за его отсутствия");
    } else if ((key[0] != null) && (value[0] != null)) {
      // проверяем: содержит ли коллекция и ключ, и значение
      if ((database.containsKey(key[0])) || (database.containsValue(value[0]))) {
        // проверяем: а нужно ли нам все это
          if (menace.verify(value[0])) {
          // проверяем: удалилось ли
          if (database.remove(key[0], value[0])) peacher().notify(0, "Удаление прошло успешно");
          else peacher().notify(1, "Не удалось удалить элемент по ключу " + key[0] + " и значению " + value[0]);
        } else peacher().notify(3, "Не удалось удалить элемент: не подходит под условия");
      } else peacher().logboard(2, "Данные для удаления не найдены в коллекции");
    } else peacher().logboard(0xEE, "Произошел неправильный вызов метода remove");
  }

  /**
   * Общий метод поиска элемента. Данный метод, в отличие от предыдущего,
   * поддерживает лишь два режима поиска:
   * 1. По ключу
   * 2. По элементу
   * @param key ключ, по которому ищется элемент
   * @param value элемент, который мы ищем
   * @param menace  признак того, нужен ли нам данный элемент
   */
  @Override
  public void search(Integer[] key, Organization[] value, Indicator menace) {
    // проверка: по ключу ли искать
    if ((key[0] != null) && (value[0] == null)) {
      // проверка: есть ли данный ключ в коллекции
      if (database.containsKey(key[0])) {
        Organization buffer = database.getOrDefault(key[0], value[0]); // сохраняем найденный элемент
        peacher().notify(0, "В базе найдены данные по Вашему ключу " + key[0]);
        if (menace.verify(buffer)) {
          value[0] = buffer; // возвращаем найденное наверх
          peacher().notify(0, "Условие удовлетворено, данные предоставлены");
        } else peacher().notify(3, "Условие не удовлетворено, данные не могут быть предоставлены");
      } else peacher().notify(1, "Ключ " + key[0] + " отсутствует в коллекции, данные по нему не могут быть найдены");
    } else if ((key[0] == null) && (value[0] != null)) {
      // проверка: есть ли элемент в коллекции
      if (database.containsValue(value[0])) {
        // Хайпим на Stream API
        Organization finalized = value[0]; // копируем значение, чтобы оно оставалось постоянным, требование ФИ
        key[0] = database.entrySet() // получаем множество пар ключ-значение
            .stream() // преобразуем в поток
            .filter(entry -> entry.getValue().equals(finalized) && menace.verify(entry.getValue())) // оставляем только значения, равные нашему и соответствующие условию
            .map(Map.Entry::getKey) // сохраняем их ключи
            .findFirst() // берем первый ключ
            .orElse(key[0]); // или звоним бывшему (сохраняем прежний ключ)
        // возврат ключа наверх
        peacher().notify(0, "Найден ключ элемента " + value[0]);
      } else peacher().notify(2, "Элемент " + value[0] + " отсутствует в коллекции");
    } else if ((key[0] == null) && (value[0] == null)) {
      // ищем ключ первого элемента, удовлетворяющего признаку
      Integer buffer_key =
          database
          .entrySet()
          .stream()
          .filter((enter)->(menace.verify(enter.getValue())))
          .findFirst().get().getKey();
      // возвращаем ключ
      key[0] = buffer_key;
    } else peacher().logboard(1, "Не корректный вызов функции поиска");
  }

  /**
   * Делаем обзор на коллекцию, фильтруя базар
   * @param menace this very фильтр
   * @return текстовая информация об элементах
   */
  @Override
  public String survey(Indicator menace) {
    StringBuilder bufferSurvey = new StringBuilder();
    // добавляем к текущей пустой строке весь текст с готовой информацией
    bufferSurvey.append(database.entrySet() // получаем пары ключ-значение
        .stream() // преобразуем в поток, дабы быть на волне
        .filter(entry -> menace.verify(entry.getValue())) // отсеиваем только не нужные
        .map((entry)->("KEY: " + entry.getKey() + ";\n\tVALUE: " + entry.getValue() + "\n")) // преобразуем в строки с информацией
        .reduce((left, right)->(left + right))); // формируем единый текст
    // Нужно юзать Stream API
    // for (Map.Entry<Integer, Organization> entry : database.entrySet())
    //  if (menace.verify(entry.getValue()))
    //    bufferSurvey.append("KEY: " + entry.getKey() + ";\n\tVALUE: " + entry.getValue() + "\n");
    peacher().logboard(0, "Данные по элементам коллекции предоставлены");
    return bufferSurvey.toString(); // возвращаем результат
  }

  /**
   * Убирать за собой - тоже нужно уметь
   */
  @Override
  public void clear() {
    database.clear(); // очистка коллекции стандартным методом
    peacher().notify(0, "Коллекции успешно очищена");
  }

  /**
   * Свойство получения дежурного логгера.
   * Нужен, чтобы в наследниках переопределить и возвращать
   * более прокачанный логгер.
   * @return ссылка на текущий логгер
   */
  protected RadioLogger peacher() {
    return whistleblower;
  }

  /**
   * Сеттер для коллекции,
   * после простых проверок,
   * тупо загружает все элементы
   * в базу
   * @param loaded список загруженных элементов
   */
  @Override
  public void DataRebase(List<Organization> loaded) {
    loaded
        .stream()
        .forEach((org) -> { database.put(org.Key(), org); });
    peacher().logboard(0, "Коллекция успешно загружена");
  }

  /**
   *
   */
  @Override
  public void save() {
    List<Organization> unload = new ArrayList<>();
    database
        .entrySet()
        .stream()
        .forEach((Map.Entry<Integer, Organization> org)->{ unload.add(org.getValue()); });
    breadLoader.unload(unload);
    peacher().notify(0, "Коллекция успешно сохранена");
  }
}
