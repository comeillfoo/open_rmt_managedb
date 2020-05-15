package instructions.concrete.extended;

import entities.Organization;
import parsing.customer.Receiver;

public final class FilterContainsName extends FilterContains<Integer, Organization, String> {
  /**
   * Обычный конструктор, связывающий
   * команду с исполнителем и геттером,
   * которым достаем ЗНАЧЕНИЕ поля.
   *
   * @param sieve  ссылка на ресивер
   * @param filter поле, которое ищем
   */
  public FilterContainsName(Receiver sieve, String filter) {
    super(sieve, filter, Organization::Name);
  }

  public static final String NAME = "filter_contains_name";
  public static final String BRIEF = "Находит элементы коллекции по заданному имени";
  public static final String SYNTAX = NAME + " [name]";
  public static final int ARGNUM = 1;
}
