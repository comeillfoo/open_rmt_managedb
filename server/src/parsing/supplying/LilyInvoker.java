package parsing.supplying;

import communication.Report;
import communication.wrappers.AlertBag;
import communication.wrappers.ExecuteBag;
import instructions.concrete.base.Save;
import instructions.concrete.extended.ExecuteScript;
import parsing.Resolver;
import instructions.concrete.ConDecree;
import parsing.supplying.interpreter.LilyShell;

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
    Report result = null;
    if (concmd instanceof ExecuteScript) {
      result = new LilyShell(MAGIV, this).read(cmd);
    }else {
      result = concmd.execute();
    }
    Report respond = new Report(0, "Команда " + concmd + " успешно выполнена с результатом:\n" + result.Message());
    if (!(concmd instanceof Save) && cmd.Channel() != null)
      MAGIV.notify(this, new AlertBag(cmd.Channel(), respond));
  }
}
