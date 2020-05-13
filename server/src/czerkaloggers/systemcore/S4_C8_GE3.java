package czerkaloggers.systemcore;

import czerkaloggers.HawkPDroid;
import systemcore.ServerController;

/**
 *
 */
public final class S4_C8_GE3 extends HawkPDroid<ServerController> {

  public S4_C8_GE3(ServerController controller) {
    super(controller);
  }

  /**
   * Помимо логгирования, еще и составляет протокол действий.
   *
   * @param errorCode код ошибки
   * @param message   передаваемое сообщение
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
