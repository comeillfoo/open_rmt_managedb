package parsing.customer;

import entities.Organization;

/**
 * Конкретный пацан в паттерне. Оперирует организациями, как
 * своими методами. Знает: какую реализация предложить, дабы
 * исполнить любую прихоть клиента.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Receiver
 * @see Commander
 */
public final class TotalCommander extends Commander<Integer, Organization> {
  /**
   * Пояснит за коллекцию и ее элементы. Помнит даже
   * дату рождения своей подопечной и ее корни
   * @return текстовая информация о хранимой коллекции
   */
  @Override public String review() {
    Class studyee = elements.getClass();
    StringBuilder info = new StringBuilder("\t* Collection canonical name: " + studyee.getCanonicalName() + ";\n");
    info.append("\t* Collection creation date: " + creationDate + ";\n");
    info.append("\t* Collection element type name: Organization extends Mappable<Integer>;\n");
    info.append("\t* Is collection empty?: " + elements.isEmpty() + ";\n");
    info.append("\t* Collection size: " + elements.size() + ";");
    return info.toString();
  }
}
