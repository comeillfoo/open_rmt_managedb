package parsing.instructions.concrete.extended;

import entities.Organization;
import parsing.customer.Receiver;

import java.time.ZonedDateTime;
import java.util.function.Function;

/**
 *
 */
public final class MaxByDate extends MaxBy<Integer, Organization, ZonedDateTime> {
  /**
   * Конструктор, устанавливающий ссылку на
   * управленца коллекцией. Также предустанавливает
   * геттер, который находит максимальный элемент по
   * его дате создания
   * @param sieve текущий управленец коллекцией
   */
  public MaxByDate(Receiver<Integer, Organization> sieve) {
    super(sieve, Organization::getCreationDate);
  }
}
