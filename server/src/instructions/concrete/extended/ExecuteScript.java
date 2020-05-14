package instructions.concrete.extended;

import communication.Report;
import instructions.concrete.ConDecree;
import parsing.customer.Receiver;

/**
 * Команда исполнения скрипта из
 * каталога scripts
 */
public class ExecuteScript extends ConDecree {
  private final String FILE_NAME;
  /**
   * Конструктор, устанавливающий ссылку на
   * управленца коллекцией
   * @param sieve текущий управленец коллекцией
   */
  public ExecuteScript(Receiver sieve, String filename) {
    super(sieve);
    FILE_NAME = filename;
  }

  /**
   * Общий метод исполнения для всех исполняемых комманд
   */
  @Override
  public Report execute() { return null; }
}
