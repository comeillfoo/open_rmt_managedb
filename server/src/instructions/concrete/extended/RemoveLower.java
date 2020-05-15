package instructions.concrete.extended;

import communication.Report;
import entities.Organization;
import entities.comparators.OrganizationTitleComparator;
import parsing.customer.Indicator;
import parsing.customer.Receiver;

import java.util.Map;

public class RemoveLower extends RemoveThan {
  protected final Indicator MENACE;
  /**
   * Конструктор, устанавливающий ссылку на
   * управленца коллекцией, а также объект
   * добавляемого элемента
   *
   * @param sieve текущий управленец коллекцией
   * @param added добавляемый элемент
   */
  public RemoveLower(Receiver sieve, Organization added) {
    super(sieve, added);
    OrganizationTitleComparator cmp = new OrganizationTitleComparator();
    MENACE = (org)->(cmp.compare((Organization) org, EMBEDDED) < 0);
  }

  /**
   * Метод исполнения, для этих команд общее
   * @return отчет выполнения
   */
  public Report execute() {
    Receiver<Integer, Organization> REAL = (Receiver<Integer, Organization>) SIEVE;
    StringBuilder result = new StringBuilder();
    Map<Integer, Integer> keys = REAL.getBy(Organization::Key);
    keys
        .entrySet()
        .stream()
        .forEach((Map.Entry<Integer, Integer> e)-> {
          Organization taken = null;
          REAL.search(e.getKey(), taken, (org)->(true));
          OrganizationTitleComparator cmp = new OrganizationTitleComparator();
          REAL.remove(e.getKey(), EMBEDDED, MENACE);
          result.append("Элемент " + taken + " по ключу " + e.getKey() + " удален\n");
        });
    result.append("Нужные элементы успешно удалены");
    return new Report(0, result.toString());
  }

  public static final String NAME = "remove_lower";
  public static final String BRIEF = "удаляет из коллекции элементы, меньшие чем заданный";
  public static final String SYNTAX = NAME + " {element}";
  public static final int ARGNUM = 1;
}
