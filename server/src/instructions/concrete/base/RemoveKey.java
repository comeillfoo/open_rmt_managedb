package instructions.concrete.base;

import communication.Report;
import entities.Organization;
import instructions.concrete.ConDecree;
import parsing.customer.Receiver;

/**
 * Команда удаления элемента по ключу
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see instructions.concrete.ConDecree
 * @see parsing.instructions.Decree
 * @see Command
 */
public final class RemoveKey extends ConDecree {
  protected final Integer key;
  /**
   * Конструктор, устанавливающий ссылку на
   * управленца коллекцией и ключ элемента,
   * который подлежит удалению
   * @param sieve текущий управленец коллекцией
   * @param removable_key ключ удаляемого элемента
   */
  public RemoveKey(Receiver sieve, Integer removable_key) {
    super(sieve);
    this.key = removable_key;
  }

  /**
   * Нагадили, следы замели,
   * если есть ключ
   */
  @Override
  public Report execute() {
    if (SIEVE == null)
      return new Report(1, "Ссылка на коллекцию не была обнаружена, пожалуйста, свяжитесь со своим системным администратором");
    if (key == null)
      return new Report(1, "Неправильный формат ключа удаляемого элемента");
    Receiver<Integer, Organization> realSiever = SIEVE;
    Organization buffer = null;
    Organization[] buffers = new Organization[]{buffer};
    Integer[] keys = new Integer[]{key};
    realSiever.search(keys, buffers, (org)->(true));
    if (buffers[0] == null)
      return new Report(0xCCCF, "Элемента по заданному ключу не существует");
    else {
      realSiever.remove(keys, new Organization[]{buffer}, (org)->(true));
      return new Report(0, "Элемент успешно удален");
    }
  }
  public static final String NAME = "remove_key";
  public static final String BRIEF = "Удаляет элемент по [key].";
  public static final String SYNTAX = NAME + " [key]";
  public static final int ARGNUM = 2;

  @Override
  public String toString() { return NAME; }
}
