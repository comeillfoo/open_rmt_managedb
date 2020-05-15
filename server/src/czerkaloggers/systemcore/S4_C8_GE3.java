package czerkaloggers.systemcore;

import communication.Component;
import communication.Mediator;
import communication.Report;
import communication.wrappers.AlertBag;
import czerkaloggers.HawkPDroid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import systemcore.ServerController;

import java.nio.channels.SocketChannel;

/**
 *
 */
public final class S4_C8_GE3 extends HawkPDroid<ServerController> implements Component {

  private static final Logger log = LoggerFactory.getLogger(S4_C8_GE3.class);

  public S4_C8_GE3(Mediator controller) {
    super((ServerController) controller);
  }

  /**
   * Помимо логгирования, еще и составляет протокол действий.
   * @param errorCode код ошибки
   * @param message   отправляемое сообщение
   */
  @Override
  public void notify(Integer errorCode, String message) { logboard(errorCode, message); }

  /**
   * Выполняет логгирование всех действий.
   *
   * @param errorCode код ошибки
   * @param message   передаваемое сообщение
   */
  @Override
  public void logboard(Integer errorCode, String message) {
    if (errorCode == 1)
      log.error("[{}] : {}", errorCode, message);
    else if (errorCode == 2)
      log.warn("[{}] : {}", errorCode, message);
    else log.info("[{}] : {}", errorCode, message);
  }
}
