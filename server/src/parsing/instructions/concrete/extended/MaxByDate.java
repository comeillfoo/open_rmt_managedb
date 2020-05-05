package parsing.instructions.concrete.extended;

import entities.Organization;
import parsing.customer.Receiver;

import java.time.ZonedDateTime;
import java.util.function.Function;

/**
 * Конкретная команда поиска
 * максимального элемента по дате
 * его создания
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkebich
 * @see MaxBy
 * @see parsing.instructions.concrete.ConDecree
 * @see parsing.instructions.Command
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
