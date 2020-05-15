package czerkaloggers.systemcore;

import communication.Component;
import communication.Mediator;
import communication.wrappers.AlertBag;
import czerkaloggers.HawkPDroid;
import systemcore.ServerController;

/**
 *
 */
public final class S4_C8_GE3 extends HawkPDroid<ServerController> implements Component {

  public S4_C8_GE3(Mediator controller) {
    super((ServerController) controller);
  }

  /**
   * Помимо логгирования, еще и составляет протокол действий.
   * @param errorCode код ошибки
   * @param message   отправляемое сообщение
   */
  @Override
  public void notify(Integer errorCode, String message) {

  }

  /**
   * Выполняет логгирование всех действий.
   *
   * @param errorCode код ошибки
   * @param message   передаваемое сообщение
   */
  @Override
  public void logboard(Integer errorCode, String message) {

  }
}
