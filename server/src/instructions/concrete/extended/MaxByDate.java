package instructions.concrete.extended;

import entities.Organization;
import instructions.Command;
import instructions.concrete.ConDecree;
import parsing.customer.Receiver;

import java.time.ZonedDateTime;

/**
 * Конкретная команда поиска
 * максимального элемента по дате
 * его создания
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkebich
 * @see instructions.concrete.extended.MaxBy
 * @see ConDecree
 * @see Command
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

  public static final String NAME = "max_by_date";
  public static final String BRIEF = "находит самый старый элемент коллекции";
  public static final String SYNTAX = NAME;
  public static final int ARGNUM = 0;

}
