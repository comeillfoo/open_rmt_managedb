package instructions.concrete.extended;

import communication.Report;
import entities.Organization;
import entities.comparators.OrganizationTitleComparator;
import instructions.concrete.base.Committer;
import parsing.customer.Receiver;
import parsing.customer.distro.LimboKeeper;

import java.util.Map;

public class ReplaceIfGreater extends ReplaceIf {
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
      REAL.search(KEY, replaced, (org)->(true));
      boolean isReplaced = new OrganizationTitleComparator().compare(EMBEDDED, replaced) > 0;
      REAL.add(KEY, EMBEDDED, (org)->(isReplaced));
      REAL.search(KEY, replaced, (org)->(true));
      if ((replaced == EMBEDDED) && (isReplaced))
        return new Report(0, "Проведена успешная замена по ключу: " + KEY);
      else return new Report(0, "Заменить элемент не удалось, т.к. "
          + (isReplaced? "элемент оказался меньше" : "произошла ошибка при замене"));
    } else return new Report(2, "Ключ " + KEY + " не найден в коллекции");
  }
}
