package parsing.instructions.concrete.base;

import communication.Report;
import entities.Organization;
import parsing.customer.Receiver;

/**
 * Команда добавления элемента в коллекцию
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Committer
 * @see parsing.instructions.concrete.ConDecree
 * @see parsing.instructions.Decree
 * @see Command
 */
public final class Add extends Committer{
  /**
   * Конструктор, устанавливающий ссылку на
   * управленца коллекцией, а также объект
   * добавляемого элемента
   * @param sieve текущий управленец коллекцией
   * @param added добавляемый элемент
   */
  public Add(Receiver sieve, Organization added) {
    super(sieve, added);
  }

  /**
   * Магия добавления элемента в коллекцию
   */
  @Override
  public Report execute() {
    Organization litmus = embedded;
    if (embedded == null)
      return new Report(1, "Обнаружена попытка добавить неопределенный элемент");
    if (sieve == null)
      return new Report(1, "Ссылка на коллекцию не была обнаружена, пожалуйста, свяжитесь со своим системным администратором");
    // молись Богам, чтобы это закастилось без ошибок
    ((Receiver<Integer, Organization>)sieve).add(embedded.Key(), embedded, (org)->(true));
    if (litmus.equals(embedded))
      return new Report(0, "Элемент успешно добавлен");
    else return new Report(0xCCCF, "Возникли ошибки при попытки добавления элемента");
  }
  public static final String NAME = "add";
  public static final String BRIEF = "добавляет новый элемент в коллекцию";
  public static final String SYNTAX = NAME + " {element}";
  public static final int ARGNUM = 1;
}
