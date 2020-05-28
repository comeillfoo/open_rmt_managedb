package instructions.concrete.extended;

import communication.Report;
import entities.Organization;
import entities.comparators.OrganizationTitleComparator;
import instructions.concrete.base.Committer;
import parsing.customer.Receiver;
import parsing.customer.distro.LimboKeeper;

import java.util.Map;

public final class ReplaceIfGreater extends ReplaceIf {
  public ReplaceIfGreater(LimboKeeper r, Integer p, Organization o) {
    super(r, p, o);
  }

  /**
   * Общий метод исполнения для всех исполняемых комманд
   */
  @Override
  public Report execute() {
    Receiver<Integer, Organization> REAL = (Receiver<Integer, Organization>)SIEVE;
    Map<Integer, Integer> keys = REAL.getBy(Organization::Key);
    if (keys.containsKey(KEY)) {
      Organization replaced = null;
      Organization[] replaceds = new Organization[]{replaced};
      Integer[] KEYS = new Integer[]{KEY};
      REAL.search(KEYS, replaceds, (org)->(true));
      boolean isReplaced = new OrganizationTitleComparator().compare(EMBEDDED, replaceds[0]) > 0;
      REAL.add(KEYS, new Organization[]{EMBEDDED}, (org)->(isReplaced));
      REAL.search(KEYS, replaceds, (org)->(true));
      if (!(replaceds[0] == EMBEDDED) && (isReplaced))
        return new Report(0, "Проведена успешная замена по ключу: " + KEYS[0]);
      else return new Report(0, "Заменить элемент не удалось, т.к. "
          + (isReplaced? "произошла ошибка при замене": "элемент оказался меньше"));
    } else return new Report(2, "Ключ " + KEY + " не найден в коллекции");
  }

  public static final String NAME = "replace_if_greater";
  public static final String BRIEF = "заменяет на новое значение по ключу [key], если оно больше старого";
  public static final String SYNTAX = NAME + " [key] {element}";
  public static final int ARGNUM = 2;

  @Override
  public String toString() { return NAME; }
}
