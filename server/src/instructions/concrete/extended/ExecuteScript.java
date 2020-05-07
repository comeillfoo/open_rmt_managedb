package instructions.concrete.extended;

import communication.Report;
import instructions.concrete.ConDecree;
import parsing.customer.Receiver;

/**
 *
 */
public class ExecuteScript extends ConDecree {
  /**
   * Конструктор, устанавливающий ссылку на
   * управленца коллекцией
   *
   * @param sieve текущий управленец коллекцией
   */
  public ExecuteScript(Receiver sieve) {
    super(sieve);
  }

  /**
   * Общий метод исполнения для всех исполняемых комманд
   */
  @Override
  public Report execute() {
    return null;
  }
}
