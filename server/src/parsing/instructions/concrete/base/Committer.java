package parsing.instructions.concrete.base;

import entities.Organization;
import parsing.customer.Receiver;
import parsing.instructions.concrete.ConDecree;

/**
 * Абстракция всех комманд, добавляющих что-то в коллекцию
 */
public abstract class Committer extends ConDecree {
  protected final Organization embedded;
  /**
   * Конструктор, устанавливающий ссылку на
   * управленца коллекцией, а также объект
   * добавляемого элемента
   * @param sieve текущий управленец коллекцией
   * @param added добавляемый элемент
   */
  public Committer(Receiver sieve, Organization added) {
    super(sieve);
    embedded = added;
  }
}
