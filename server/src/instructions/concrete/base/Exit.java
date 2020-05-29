package instructions.concrete.base;

import communication.Report;
import instructions.concrete.ConDecree;
import parsing.customer.Receiver;

/**
 * Команда, завершения серверного приложения,
 * приходит только от сервера
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see instructions.concrete.ConDecree
 * @see Decree
 * @see Command
 */
public final class Exit extends ConDecree {
  /**
   * Конструктор, устанавливающий ссылку на
   * управленца коллекцией
   * @param sieve текущий управленец коллекцией
   */
  public Exit(Receiver sieve) {
    super(sieve);
  }

  /**
   * Алгоритм исполнения выхода
   * из программы
   * @return отчет о выполнении команды
   */
  @Override
  public Report execute() {
    if (SIEVE == null)
      return new Report(1, "Ссылка на коллекцию не была обнаружена, пожалуйста, свяжитесь со своим системным администратором");
    SIEVE.save();
    System.exit(0);
    return new Report(0xDEAD, "Критическая ошибка: не удалось завершить работу сервера");
  }

  public static final String NAME = "exit";
  public static final String BRIEF = "завершить программу";
  public static final String SYNTAX = NAME;
  public static final int ARGNUM = 0;

  /**
   * Метод возврата названия команды
   * @return название команды
   */
  @Override
  public String toString() { return NAME; }
}
