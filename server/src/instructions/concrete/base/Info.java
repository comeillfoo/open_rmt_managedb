package instructions.concrete.base;

import communication.Report;
import instructions.concrete.ConDecree;
import parsing.customer.Receiver;

/**
 * Команда получения информации о коллекции
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see instructions.concrete.ConDecree
 * @see parsing.instructions.Decree
 * @see parsing.instructions.Command
 */
public final class Info extends ConDecree {
  /**
   * Конструктор, устанавливающий ссылку на
   * управленца коллекцией
   *
   * @param sieve текущий управленец коллекцией
   */
  public Info(Receiver sieve) {
    super(sieve);
  }

  /**
   * Магия, которую Боги даровали
   * нам, чтобы получить информацию о
   * коллекции
   */
  @Override
  public Report execute() {
    if (sieve == null)
      return new Report(1, "Ссылка на коллекцию не была обнаружена, пожалуйста, свяжитесь с Вашим системным администратором");
    String buffer = sieve.review();
    if ((buffer == null) || (buffer.isEmpty()))
      return new Report(0xFEED, "Информация по данной коллекции не найдено");
    else return new Report(0, "Информация по Вашей коллекции:\n" + buffer);
  }
  public static final String NAME = "info";
  public static final String BRIEF = "выводит информацию о коллекции";
  public static final String SYNTAX = NAME;
  public static final int ARGNUM = 0;
}
