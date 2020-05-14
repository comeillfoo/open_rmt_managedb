package instructions.concrete.base;

import communication.Report;
import instructions.concrete.ConDecree;
import parsing.customer.Receiver;

import java.util.ArrayList;
import java.util.List;

/**
 * Команда для возвращения информации о
 * доступных командах
 */
public final class Help extends instructions.concrete.ConDecree {
  protected final List<instructions.concrete.ConDecree> orders;
  /**
   * Конструктор, устанавливающий ссылку на
   * управленца коллекцией
   * @param sieve текущий управленец коллекцией
   * @param commands текущий список доступных комманд
   */
  public Help(Receiver sieve, List<instructions.concrete.ConDecree> commands) {
    super(sieve);
    orders = (commands == null)? new ArrayList<>() : commands;
  }

  /**
   * Пишем альманах всех комманд,
   * дабы друиды могли вызвать сатану
   */
  @Override
  public Report execute() {
    StringBuilder manual = new StringBuilder();
    orders
        .stream()
        .forEach(
            (order)->
            {
              manual.append(less(order));
            });
    return new Report(0, "Список доступных комманд:\n" + manual.toString());
  }

  /**
   * Вспомогательный метод
   * формирования информации о команде
   * @param command команда
   * @return информация по команде
   */
  private final String less(ConDecree command) {
    StringBuilder page = new StringBuilder();
    page.append("name: " + command.NAME + " -- brief: " + command.BRIEF + "\n");
    page.append("synopsys: " + command.SYNTAX + "\n");
    return page.toString();
  }
  public static final String NAME = "help";
  public static final String BRIEF = "выводит справку по доступным командам";
  public static final String SYNTAX = NAME;
  public static final int ARGNUM = 0;
}
