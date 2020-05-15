package czerkaloggers.systemcore;

import communication.Component;
import communication.Mediator;
import czerkaloggers.HawkPDroid;
import systemcore.SystemAdmin;

public final class C7_E3_GE3 extends HawkPDroid<SystemAdmin> implements Component {
  public C7_E3_GE3(Mediator controller) {
    super((SystemAdmin) controller);
  }

  /**
   * Помимо логгирования, еще и составляет протокол действий.
   *
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
