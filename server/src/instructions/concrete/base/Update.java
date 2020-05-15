package instructions.concrete.base;

import communication.Report;
import entities.Organization;
import parsing.customer.Receiver;

/**
 * Команда обновления элемента
 * коллекции по идентификатору
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see parsing.instructions.concrete.ConDecree
 * @see parsing.instructions.Decree
 * @see parsing.instructions.Command
 */
public final class Update extends Committer {
  protected final Integer id;

  /**
   * Конструктор, устанавливающий ссылку на
   * управленца коллекцией, а также идентификатор
   * обновляемого элемента и элемент, которым
   * заменяем
   * @param sieve текущий управленец коллекцией
   * @param id идентификатор обновляемого элемента
   * @param added заменитель
   */
  public Update(Receiver sieve, Integer id, Organization added) {
    super(sieve, added);
    this.id = id;
  }

  /**
   * Постигаем дзен, а точнее кайдзен
   */
  @Override
  public Report execute() {
    if (SIEVE == null)
      return new Report(1, "Ссылка на коллекцию не была обнаружена, пожалуйста, свяжитесь с Вашим системным администратором");
    if (id == null)
      return new Report(1, "Неправильный формат идентификатора обновляемого элемента");
    if (EMBEDDED == null)
      return new Report(1, "Обнаружена попытка добавить неопределенный элемент");
    Receiver<Integer, Organization> realSiever = (Receiver<Integer, Organization>) SIEVE;
    Integer key = null;
    Integer[] keys = new Integer[]{key};
    realSiever.search(keys, new Organization[]{}, (org)->(org.Key().equals(id)));
    if (key != null) {
      Organization litmus = EMBEDDED;
      Organization[] buffers = new Organization[]{litmus};
      realSiever.add(keys, buffers, (org)->(true));
      if (buffers[0].equals(EMBEDDED))
        return new Report(0, "Элемент успешно обновлен");
      else return new Report(0xCCCF, "Возникли ошибки при добавлении элемента");
    } else return new Report(0xCCCF, "Элемент с заданным идентификатором не найден");
  }
  public static final String NAME = "update";
  public static final String BRIEF = "Обновляет значение элемента, id которого равен заданному";
  public static final String SYNTAX = NAME + "[id] {element}";
  public static final int ARGNUM = 2;

  @Override
  public String toString() { return NAME; }
}
