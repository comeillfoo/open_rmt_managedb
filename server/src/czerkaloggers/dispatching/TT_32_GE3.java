package czerkaloggers.dispatching;

import communication.Component;
import communication.Mediator;
import communication.wrappers.AlertBag;
import czerkaloggers.HawkPDroid;
import dispatching.AliExpress;
import dispatching.Dispatcher;

public final class TT_32_GE3 extends HawkPDroid<Dispatcher> implements Component {
  protected TT_32_GE3(Dispatcher controller) { super(controller); }

  public TT_32_GE3(Mediator controller) { this((Dispatcher) controller); }


  /**
   * Помимо логгирования, еще и составляет протокол действий.
   *
   * @param errorCode код ошибки
   * @param message   отправляемое сообщение
   */
  @Override
  public void notify(Integer errorCode, String message) { logboard(errorCode, message); }

  /**
   * Выполняет логгирование всех действий.
   * @param errorCode код ошибки
   * @param message   передаваемое сообщение
   */
  @Override
  public void logboard(Integer errorCode, String message) {
    // TODO: log
  }
}
