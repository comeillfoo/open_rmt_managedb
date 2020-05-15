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

  public static final String NAME = "execute_script";
  public static final String BRIEF = "исполняет скрипт по указанному имени файла";
  public static final String SYNTAX = NAME + " [file_name]";
  public static final int ARGNUM = 1;
}
