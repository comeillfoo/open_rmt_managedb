package instructions.concrete.base;

import communication.Report;
import instructions.concrete.ConDecree;
import parsing.customer.Receiver;

/**
 * Команда очистки коллекции
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see instructions.concrete.ConDecree
 * @see parsing.instructions.Decree
 * @see Command
 */
public final class Clear extends ConDecree {
  /**
   * Конструктор, устанавливающий ссылку на
   * управленца коллекцией
   *
   * @param sieve текущий управленец коллекцией
   */
  public Clear(Receiver sieve) {
    super(sieve);
  }

  /**
   * Магия, очищающая коллекцию
   */
  @Override
  public Report execute() {
    if (sieve == null)
      return new Report(1, "Ссылка на коллекцию не была обнаружена, пожалуйста, свяжитесь с Вашим системным администратором");
    try {
      sieve.clear();
    } catch (UnsupportedOperationException e) {
      return new Report(0xBAD, "Извините, но данный тип коллекции не поддерживает операцию очистки");
    }
    return new Report(0, "Очистка коллекции успешна");
  }
  public static final String NAME = "clear";
  public static final String BRIEF = "очищает коллекцию";
  public static final String SYNTAX = NAME;
  public static final int ARGNUM = 0;
}
