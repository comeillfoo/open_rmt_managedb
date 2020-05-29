package czerkaloggers.dispatching;

import communication.Component;
import communication.Mediator;
import communication.wrappers.AlertBag;
import czerkaloggers.HawkPDroid;
import czerkaloggers.systemcore.C7_E3_GE3;
import dispatching.AliExpress;
import dispatching.Dispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Сущность, логирующая сообщения,
 * присылаемые от контроллера
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public final class TT_32_GE3 extends HawkPDroid<Dispatcher> implements Component {

  private static final Logger log = LoggerFactory.getLogger(TT_32_GE3.class);

  /**
   * Конструктор, установки
   * отправителя сообщения для
   * логов
   * @param controller отправитель логов
   */
  public TT_32_GE3(Mediator controller) { super((Dispatcher) controller); }


  /**
   * Помимо логгирования, еще и
   * составляет протокол действий.
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
    if (errorCode == 1)
      log.error("[{}] : {}", errorCode, message);
    else if (errorCode == 2)
      log.warn("[{}] : {}", errorCode, message);
    else log.info("[{}] : {}", errorCode, message);
  }
}
