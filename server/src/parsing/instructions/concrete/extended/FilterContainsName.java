package parsing.instructions.concrete.extended;

import entities.Organization;
import parsing.customer.Receiver;

import java.util.function.Function;

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
}
