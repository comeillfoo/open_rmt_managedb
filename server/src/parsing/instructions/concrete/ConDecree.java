package parsing.instructions.concrete;

import parsing.customer.Receiver;
import parsing.instructions.Command;
import parsing.instructions.Decree;

/**
 * Абстракция всех комманд, использующихся на сервере.
 * Помимо информации о себе, должны ОБЯЗАТЕЛЬНО иметь ссылку на
 * {@link parsing.customer.Receiver}, в котором они исполняют комманды
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Decree
 * @see Command
 */
public abstract class ConDecree extends Decree {
  // ссылка на текущий контроллер коллекции
  protected final Receiver sieve;

  /**
   * Конструктор, устанавливающий ссылку на
   * управленца коллекцией
   * @param sieve текущий управленец коллекцией
   */
  public ConDecree(Receiver sieve) { this.sieve = sieve; }
}
