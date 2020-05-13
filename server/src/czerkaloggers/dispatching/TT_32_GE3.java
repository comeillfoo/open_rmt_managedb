package czerkaloggers.dispatching;

import communication.Mediator;
import czerkaloggers.HawkPDroid;
import dispatching.AliExpress;
import dispatching.Dispatcher;

public final class TT_32_GE3 extends HawkPDroid<Dispatcher> {
  protected TT_32_GE3(Dispatcher controller) { super(controller); }

  public TT_32_GE3(Mediator controller) { this((Dispatcher) controller); }
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
