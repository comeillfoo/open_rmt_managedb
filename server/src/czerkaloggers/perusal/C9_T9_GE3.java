package czerkaloggers.perusal;

import communication.Component;
import communication.Mediator;
import communication.Report;
import communication.wrappers.AlertBag;
import czerkaloggers.HawkPDroid;
import czerkaloggers.systemcore.C7_E3_GE3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import perusal.QueryReader;

import java.nio.channels.SocketChannel;

public final class C9_T9_GE3 extends HawkPDroid<QueryReader> implements Component {

  private static final Logger log = LoggerFactory.getLogger(C9_T9_GE3.class);


  public C9_T9_GE3(Mediator controller) { super((QueryReader) controller); }

  /**
   * Помимо логгирования, еще и составляет протокол действий.
   * @param errorCode код ошибки
   * @param message   отправляемое сообщение
   */
  @Override
  public void notify(Integer errorCode, String message) {
    logboard(errorCode, message);
    SocketChannel client = MAGIV.ClientChannel();
    AlertBag alert = new AlertBag(client, new Report(errorCode, message));
    MAGIV.notify(this, alert);
  }
  /**
   * Выполняет логгирование всех действий
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
