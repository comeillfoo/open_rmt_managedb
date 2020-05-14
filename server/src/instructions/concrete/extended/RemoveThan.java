package instructions.concrete.extended;

import entities.Organization;
import instructions.concrete.base.Committer;
import parsing.customer.Receiver;

public abstract class RemoveThan extends Committer {
  /**
   * Конструктор, устанавливающий ссылку на
   * управленца коллекцией, а также объект
   * добавляемого элемента
   *
   * @param sieve текущий управленец коллекцией
   * @param added добавляемый элемент
   */
  public RemoveThan(Receiver sieve, Organization added) {
    super(sieve, added);
  }
}
