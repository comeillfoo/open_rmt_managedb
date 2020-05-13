package czerkaloggers.perusal;

import communication.Mediator;
import czerkaloggers.HawkPDroid;
import perusal.QueryReader;

public final class C9_T9_GE3 extends HawkPDroid<QueryReader> {

  public C9_T9_GE3(Mediator controller) { super((QueryReader) controller); }

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
