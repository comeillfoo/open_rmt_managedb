package parsing.instructions.concrete.base;

import communication.Report;
import parsing.customer.Receiver;
import parsing.instructions.concrete.ConDecree;

/**
 * Команда сохранения текущего состояния коллекции
 * Приходит только от сервера
 * При выходе - автосохранение
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see ConDecree
 * @see Decree
 * @see Command
 */
public final class Save extends ConDecree {
  /**
   * Конструктор, устанавливающий ссылку на
   * управленца коллекцией
   *
   * @param sieve текущий управленец коллекцией
   */
  public Save(Receiver sieve) {
    super(sieve);
  }

  /**
   * Сохраняйся - если хочешь быть здоров,
   * Сохраняйся - чтоб забыть о всех тревог,
   * Не забывай ты сохраняться,
   * Если хочешь быть здоров.
   */
  @Override
  public Report execute() {
    if (sieve == null)
      return new Report(1, "Ссылка на коллекцию не была обнаружена, пожалуйста, свяжитесь со своим системным администратором");
    sieve.save();
    return new Report(0, "Коллекция успешно сохранена");
  }
  public static final String NAME = "save";
  public static final String BRIEF = "Сохраняет коллекцию в файл.";
  public static final String SYNTAX = NAME;
  public static final int ARGNUM = 0;
}
