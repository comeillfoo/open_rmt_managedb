package instructions.concrete.extended;

import communication.Report;
import entities.Organization;
import entities.comparators.OrganizationTitleComparator;
import instructions.concrete.base.Committer;
import parsing.customer.Indicator;
import parsing.customer.Receiver;

import java.util.Map;

/**
 * Абстракция команд, удаляющих
 * все элементы по условию
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public abstract class RemoveThan extends Committer {
  /**
   * Конструктор, устанавливающий ссылку на
   * управленца коллекцией, а также объект
   * добавляемого элемента
   * @param sieve текущий управленец коллекцией
   * @param added добавляемый элемент
   * @param menace условие удаления
   */
  public RemoveThan(Receiver sieve, Organization added) {
    super(sieve, added);
  }
}
