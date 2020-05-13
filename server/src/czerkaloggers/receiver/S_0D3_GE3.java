package czerkaloggers.receiver;

import communication.Component;
import communication.Mediator;
import czerkaloggers.HawkPDroid;
import receiver.Receptionist;

public final class S_0D3_GE3 extends HawkPDroid<Receptionist> implements Component {
  // builders
  public S_0D3_GE3(Mediator controller) { super((Receptionist) controller); }

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
