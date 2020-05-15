package instructions.concrete.extended;

import communication.Report;
import entities.Organization;
import entities.comparators.OrganizationTitleComparator;
import instructions.concrete.base.Committer;
import parsing.customer.Receiver;
import parsing.customer.distro.LimboKeeper;

import java.util.Map;

public final class ReplaceIfLower extends ReplaceIf {
  public ReplaceIfLower(LimboKeeper r, Integer p, Organization o) { super(r, p, o); }

  /**
   * Общий метод исполнения для всех исполняемых комманд
   */
  @Override
  public Report execute() {
    Receiver<Integer, Organization> REAL = (Receiver<Integer, Organization>)SIEVE;
    Map<Integer, Integer> keys = REAL.getBy(Organization::Key);
    if (keys.containsKey(KEY)) {
      Organization replaced = null;
      REAL.search(KEY, replaced, (org)->(true));
      boolean isReplaced = new OrganizationTitleComparator().compare(EMBEDDED, replaced) < 0;
      REAL.add(KEY, EMBEDDED, (org)->(isReplaced));
      REAL.search(KEY, replaced, (org)->(true));
      if ((replaced == EMBEDDED) && (isReplaced))
        return new Report(0, "Проведена успешная замена по ключу: " + KEY);
      else return new Report(0, "Заменить элемент не удалось, т.к. "
          + (isReplaced? "элемент оказался больше" : "произошла ошибка"));
    } else return new Report(2, "Ключ " + KEY + " не найден в коллекции");
  }

  public static final String NAME = "replace_if_lower";
  public static final String BRIEF = "заменяет на новое значение по ключу [key], если оно меньше старого";
  public static final String SYNTAX = NAME + " [key] {element}";
  public static final int ARGNUM = 2;

  @Override
  public String toString() { return NAME; }
}
