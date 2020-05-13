package receiver;

import communication.Component;
import communication.Valuable;
import czerkaloggers.HawkPDroid;
import czerkaloggers.receiver.S_0D3_GE3;
import systemcore.ServerController;

/**
 * Та самая девушка на ресепшене,
 * что распределяет гостей, и
 * записывает их в базу
 */
public final class Hostess extends Receptionist {
  private final HawkPDroid<Hostess> tetrode;
  /**
   * Стандартный конструктор,
   * с установкой контроллера над модулем
   *
   * @param core контроллер системы модулей
   */
  public Hostess(ServerController core) {
    super(core);
    tetrode = (HawkPDroid<Hostess>) S_0D3_GE3.assemble(this, S_0D3_GE3::new);
  }

  @Override
  public void notify(Component sender, Valuable data) {

  }
}
