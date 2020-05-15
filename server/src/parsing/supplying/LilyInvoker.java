package parsing.supplying;

import communication.Report;
import communication.wrappers.AlertBag;
import communication.wrappers.ExecuteBag;
import parsing.Resolver;
import instructions.concrete.ConDecree;

/**
 * Эмулятор клиента, что вызывает приходящие
 * от него команды и вызывает их. Пародия на LilyTerm
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public class LilyInvoker extends FondleEmulator {

  /**
   * Конструктор, устанавливающий
   * контроллер с которого приходят команды
   * @param controller ссылка на SSPC
   */
  public LilyInvoker(Resolver controller) {
    super(controller);
  }

  /**
   * Метод записи комманды в
   * список обслуживаемых комманд
   * @param commandName название команды
   */
  @Override
  public void signup(ConDecree command) { availableCommands.put(command.toString(), command); }

  /**
   * Метод вызова команды
   * @param cmd присланная команда
   */
  @Override
  public void invoke(ExecuteBag cmd) {
    ConDecree concmd = cmd.Exec();
    concmd.execute();
    Report respond = new Report(0, "Команда " + concmd + " успешно выполнена");
    MAGIV.notify(this, new AlertBag(cmd.Channel(), respond));
  }
}
