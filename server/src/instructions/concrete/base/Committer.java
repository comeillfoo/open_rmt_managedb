package instructions.concrete.base;

import entities.Organization;
import parsing.customer.Receiver;
import instructions.concrete.ConDecree;

/**
 * Абстракция всех комманд, добавляющих что-то в коллекцию
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see ConDecree
 * @see parsing.instructions.Decree
 * @see parsing.instructions.Command
 */
public abstract class Committer extends ConDecree {
  protected final Organization EMBEDDED;
  /**
   * Конструктор, устанавливающий ссылку на
   * управленца коллекцией, а также объект
   * добавляемого элемента
   * @param sieve текущий управленец коллекцией
   * @param added добавляемый элемент
   */
  public Committer(Receiver sieve, Organization added) {
    super(sieve);
    EMBEDDED = added;
  }

  /**
   * Метод, возвращающий
   * название команды
   * @return название команды
   */
  @Override
  public String toString() { return NAME; }
}
