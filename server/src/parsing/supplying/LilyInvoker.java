package parsing.supplying;

import parsing.Resolver;
import parsing.instructions.concrete.ConDecree;

/**
 * Эмулятор клиента, что вызывает приходящие
 * от него команды и вызывает их. Пародия на LilyTerm
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public final class LilyInvoker extends FondleEmulator {

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
  public void signup(ConDecree command) { availableCommands.put(command.NAME, command); }

  /**
   * Метод вызова команды
   * @param command присланная команда
   */
  @Override
  public void invoke(ConDecree command) {
    // TODO: написать реализацию
  }
}
