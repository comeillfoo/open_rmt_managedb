package parsing.customer.local;

import entities.Organization;
import logging.customer.ReceiverLogger;
import parsing.customer.Indicator;
import parsing.customer.Receiver;
import parsing.customer.bootstrapper.LoaferLoader;

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
  public TotalCommander(LoaferLoader<Organization> loader, ReceiverLogger logger) { super(loader, logger); }
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
    return info.toString();
  }

  /**
   * Обший метод для добавления элемента в коллекцию
   * @param key ключ элемента, на который пишется элемент
   * @param value записываемый элемент
   * @param menace  признак, по которому данный элемент должен добавится
   */
  @Override
  public void add(Integer key, Organization value, Indicator menace) {
    // проверка: добавляем ли мы ошибку природы
    if (value != null) {
      // проверка: нужно ли добавлять данное детище
      if (menace.verify(value))
        value = database.put((key == null)? value.Key() : key, value); // добавляем и возвращаем, то чем заменили (null если добавили)
      else peacher().notify(null, null); // TODO: пояснить, что данному элементу нету прохода на район нашей коллекции
    } else peacher().notify(null, null); // TODO: попорицать клиента, за его жалкую попытку создать нам проблемы
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
  public void remove(Integer key, Organization value, Indicator menace) {
    // проверяем нужно ли удалять элемент по ключу
    if ((key != null) && (value == null)) {
      // проверяем есть ли такой ключ в базе
      if (database.containsKey(key)) {
        // выбрасываем этот мусор из коллекции, и, чтобы подстраховаться, даем ссылку команде, ради дальнейших проверок
        value = database.remove(key);
        peacher().notify(null, null); // TODO: сформировать отчетность
      } else peacher().notify(null, null); //TODO: попытка обращения по несуществующему ключу (Боги олимпа накажут его за это)
    } else if ((key == null) && (value != null)) {
      // проверили: нужно ли удалить элемент по значению и дальше смотрим, а есть ли эта тварь в коллекции вообще
      if (database.containsValue(value)) {
        // если все-таки есть, то пытаемся найти ключ этой падлы
        search(key, value, (v)->(true)); // а чо зря функцию писал, вот и понадобилась
        // делаем проверку: нужно ли удалять этот элемент
        if (menace.verify(value)) {
          // удаляем и сверяем с требуемым на удаление
          if (value.equals(database.remove(key)))
            peacher().notify(null, null); // TODO: сформивать отчет, что удаление прошло успешно
          else peacher().notify(null, null); // TODO: повиниться, что случайно удалили не того
        } else peacher().notify(null, null); // TODO: уведомить, что звезды не сошлись, поэтому элемент не удален
      } else peacher().notify(null, null); // TODO: уведомить клиента, что он (дебил) зря нас беспокоит и такого элемента в коллекции нет
    } else if ((key != null) && (value != null)) {
      // проверяем: содержит ли коллекция и ключ, и значение
      if ((database.containsKey(key)) || (database.containsValue(value))) {
        // проверяем: а нужно ли нам все это
        if (menace.verify(value)) {
          // проверяем: удалилось ли
          if (database.remove(key, value)) peacher().notify(null, null); // TODO: уведомить, что все в порядке
          else peacher().notify(null, null); // TODO: уведомить, что клиент налажал с данными
        } else peacher().notify(null, null); // TODO: уведомить, что у элемента и коллекции разные интересы, что дело не в нем, а в коллекции (хотя больше в нем)
      } else peacher().logboard(null, null); // TODO: потеря-потерь, подстава-подстав: лажа с данными
    } else peacher().logboard(null, null); // TODO: дергаем логгер и жалуемся, что нас неправильно вызвали
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
  public void search(Integer key, Organization value, Indicator menace) {
    // проверка: по ключу ли искать
    if ((key != null) && (value == null)) {
      // проверка: есть ли данный ключ в коллекции
      if (database.containsKey(key)) {
        Organization buffer = database.getOrDefault(key, value); // сохраняем найденный элемент
        peacher().notify(null, null); // TODO: посвятить клиента, что мы что-то нашли
        if (menace.verify(buffer)) {
          value = buffer; // возвращаем найденное наверх
          peacher().notify(null, null); // TODO: уведомить, что у нас полная совместимость с найденным элементов
        } else peacher().notify(null, null); // TODO: уведомить, что звезды не сошлись, элемент не подходит
      } else peacher().notify(null, null); // TODO: уведомить, что такого ключа нет
    } else if ((key == null) && (value != null)) {
      // проверка: есть ли элемент в коллекции
      if (database.containsValue(value)) {
        // Хайпим на Stream API
        Organization finalized = value; // копируем значение, чтобы оно оставалось постоянным, требование ФИ
        key = database.entrySet() // получаем множество пар ключ-значение
            .stream() // преобразуем в поток
            .filter(entry -> entry.getValue().equals(finalized) && menace.verify(entry.getValue())) // оставляем только значения, равные нашему и соответствующие условию
            .map(Map.Entry::getKey) // сохраняем их ключи
            .findFirst() // берем первый ключ
            .orElse(key); // или звоним бывшему (сохраняем прежний ключ)
        // возврат ключа наверх
        peacher().notify(null, null); // TODO: дать ответочку за ключик золотой
      } else peacher().notify(null, null); // TODO: пояснить за то, что данного элемента нет
    } else peacher().logboard(null, null); // TODO: дергаем логгер и жалуемся, что нас неправильно вызвали
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
    peacher().notify(null, null); // TODO: возможно и здесь нужно подергать логгер и создать клиентское уведомление
    return bufferSurvey.toString(); // возвращаем результат
  }

  /**
   * Убирать за собой - тоже нужно уметь
   */
  @Override
  public void clear() {
    database.clear(); // очистка коллекции стандартным методом
    peacher().notify(null, null); // TODO: я хочу знать, что все хорошо (ваш клиент)
  }

  /**
   * Свойство получения дежурного логгера.
   * Нужен, чтобы в наследниках переопределить и возвращать
   * более прокачанный логгер.
   * @return ссылка на текущий логгер
   */
  protected ReceiverLogger peacher() {
    return whistleblower;
  }
}
