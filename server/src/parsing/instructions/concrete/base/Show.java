package parsing.instructions.concrete.base;


import communication.Report;
import entities.Organization;
import parsing.customer.Receiver;
import parsing.instructions.concrete.ConDecree;

/**
 * Команда получения информации
 * о каждом элементе коллекции
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public final class Show extends ConDecree {
  /**
   * Конструктор, устанавливающий ссылку на
   * управленца коллекцией
   *
   * @param sieve текущий управленец коллекцией
   */
  public Show(Receiver sieve) {
    super(sieve);
  }

  /**
   * Магия, что показывает
   * будущее элементов коллекции
   */
  @Override
  public Report execute() {
    if (sieve == null)
      return new Report(1, "Ссылка на коллекцию не была обнаружена, пожалуйста, свяжитесь со своим системным администратором");
    Receiver<Integer, Organization> realSiever = (Receiver<Integer, Organization>) sieve;
    String info = sieve.survey((org)->(true));
    if ((info == null) || (info.isEmpty()))
      return new Report(0, "Информация по элементам данной коллекции не найдено/либо коллекция пуста");
    return new Report(0, "Коллекция состоит из следующих элементов:\n" + info);
  }
  public static final String NAME = "show";
  public static final String BRIEF = "выводит все элементы в stdout";
  public static final String SYNTAX = NAME;
  public static final int ARGNUM = 0;
}
