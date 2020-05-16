package czerkaloggers.customer;

import communication.Component;
import communication.Mediator;
import communication.Report;
import communication.wrappers.AlertBag;
import czerkaloggers.HawkPDroid;
import czerkaloggers.RadioLogger;
import czerkaloggers.dispatching.TT_32_GE3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parsing.Resolver;

import java.nio.channels.SocketChannel;

/**
 * Вот, самое сложное похоже это придумать название всему,
 * что есть в программе. Поэтому не нужно ругаться на название
 * этого класса. Знающие люди сразу обнаружать здесь забавную отсылку.
 * И тогда все встанет на свои места.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see HawkPDroid
 * @see RadioLogger
 */
public final class B_4D4_GE3 extends HawkPDroid<Resolver> implements Component {

  private static final Logger log = LoggerFactory.getLogger(B_4D4_GE3.class);

  // builders
  public B_4D4_GE3(Mediator controller) { super((Resolver) controller); }

  /**
   * Помимо логгирования, еще и составляет протокол действий.
   *
   * @param errorCode код ошибки
   * @param message передаваемое сообщение
   */
  @Override
  public void notify(Integer errorCode, String message) {
    logboard(errorCode, message);
    SocketChannel client = MAGIV.ClientChannel();
    AlertBag alert = new AlertBag(client, new Report(errorCode, message));
    MAGIV.notify(this, alert);
  }

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
