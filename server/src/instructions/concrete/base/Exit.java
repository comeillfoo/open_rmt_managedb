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
   *
   * @param sieve текущий управленец коллекцией
   */
  public Exit(Receiver sieve) {
    super(sieve);
  }

  /**
   * Общий метод исполнения для всех исполняемых комманд
   */
  @Override
  public Report execute() {
    if (sieve == null)
      return new Report(1, "Ссылка на коллекцию не была обнаружена, пожалуйста, свяжитесь со своим системным администратором");
    sieve.save();
    System.exit(0);
    return new Report(0xDEAD, "Критическая ошибка: не удалось завершить работу сервера");
  }
  public static final String NAME = "exit";
  public static final String BRIEF = "завершить программу";
  public static final String SYNTAX = NAME;
  public static final int ARGNUM = 0;
}
