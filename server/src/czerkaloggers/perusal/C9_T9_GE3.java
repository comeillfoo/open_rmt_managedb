package czerkaloggers.perusal;

import communication.Component;
import communication.Mediator;
import communication.Report;
import communication.wrappers.AlertBag;
import czerkaloggers.HawkPDroid;
import perusal.QueryReader;

import java.nio.channels.SocketChannel;

public final class C9_T9_GE3 extends HawkPDroid<QueryReader> implements Component {

  public C9_T9_GE3(Mediator controller) { super((QueryReader) controller); }

  /**
   * Помимо логгирования, еще и составляет протокол действий.
   *
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

  }
}
