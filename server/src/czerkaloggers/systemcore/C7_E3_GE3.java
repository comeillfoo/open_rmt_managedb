package czerkaloggers.systemcore;

import communication.Component;
import communication.Mediator;
import czerkaloggers.HawkPDroid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import systemcore.SystemAdmin;

/**
 * Где киса? Вот..., а
 * стоп, это очередной логгер
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public final class C7_E3_GE3 extends HawkPDroid<SystemAdmin> implements Component {

  private static final Logger log = LoggerFactory.getLogger(C7_E3_GE3.class);

  public C7_E3_GE3(Mediator controller) {
    super((SystemAdmin) controller);
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
