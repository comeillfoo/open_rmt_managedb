package instructions.concrete.base;

import communication.Report;
import instructions.concrete.ConDecree;
import instructions.concrete.extended.*;
import instructions.rotten.RawDecree;
import parsing.customer.Receiver;

import java.util.ArrayList;
import java.util.List;

/**
 * Команда для возвращения информации о
 * доступных командах
 */
public final class Help extends instructions.concrete.ConDecree {
  private final List<String[]> CMDS = new ArrayList<>();
  {
    CMDS.add(new String[]{ Clear.NAME, Clear.BRIEF, Clear.SYNTAX });
    CMDS.add(new String[]{ Help.NAME, Help.BRIEF, Help.SYNTAX });
    CMDS.add(new String[]{ Info.NAME, Info.BRIEF, Info.SYNTAX });
    CMDS.add(new String[]{ Insert.NAME, Insert.BRIEF, Insert.SYNTAX });
    CMDS.add(new String[]{ RemoveKey.NAME, RemoveKey.BRIEF, RemoveKey.SYNTAX });
    CMDS.add(new String[]{ Show.NAME, Show.BRIEF, Show.SYNTAX });
    CMDS.add(new String[]{ Update.NAME, Update.BRIEF, Update.SYNTAX });
    CMDS.add(new String[]{ ExecuteScript.NAME, ExecuteScript.BRIEF, ExecuteScript.SYNTAX });
    CMDS.add(new String[]{ FilterContainsName.NAME, FilterContainsName.BRIEF, FilterContainsName.SYNTAX });
    CMDS.add(new String[]{ MaxByDate.NAME, MaxByDate.BRIEF, MaxByDate.SYNTAX });
    CMDS.add(new String[]{ RemoveLower.NAME, RemoveLower.BRIEF, RemoveLower.SYNTAX });
    CMDS.add(new String[]{ ReplaceIfGreater.NAME, ReplaceIfGreater.BRIEF, ReplaceIfGreater.SYNTAX });
    CMDS.add(new String[]{ ReplaceIfLower.NAME, ReplaceIfLower.BRIEF, ReplaceIfLower.SYNTAX });
    CMDS.add(new String[]{ SumOfAnnualTurnover.NAME, SumOfAnnualTurnover.BRIEF, SumOfAnnualTurnover.SYNTAX });
  }
  /**
   * Конструктор, устанавливающий ссылку на
   * управленца коллекцией
   * @param sieve текущий управленец коллекцией
   */
  public Help(Receiver sieve) { super(sieve); }

  /**
   * Пишем альманах всех комманд,
   * дабы друиды могли вызвать сатану
   */
  @Override
  public Report execute() {
    StringBuilder manual = new StringBuilder();
    CMDS
        .stream()
        .forEach(
            (o)->
            {
              manual.append(less(o[0], o[1], o[2]));
            });
    return new Report(0, "Список доступных комманд:\n" + manual.toString());
  }

  /**
   * Вспомогательный метод
   * формирования информации о команде
   * @param NAME название команды
   * @param BRIEF краткое описание команды
   * @param SYNTAX синтаксис команды
   * @return информация по команде
   */
  private final String less(String NAME, String BRIEF, String SYNTAX) {
    StringBuilder page = new StringBuilder();
    page.append("name: " + NAME + " -- brief: " + BRIEF + "\n");
    page.append("synopsys: " + SYNTAX + "\n");
    return page.toString();
  }
  public static final String NAME = "help";
  public static final String BRIEF = "выводит справку по доступным командам";
  public static final String SYNTAX = NAME;
  public static final int ARGNUM = 0;

  @Override
  public String toString() { return NAME; }
}
