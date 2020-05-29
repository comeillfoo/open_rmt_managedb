package czerkaloggers;

import communication.Mediator;
import communication.wrappers.AlertBag;

/**
 * Список требований к логгеру любого receiver'а.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public interface RadioLogger {
  /**
   * Помимо логгирования, еще и составляет протокол действий.
   * @param errorCode код ошибки
   * @param message отправляемое сообщение
   */
  void notify(Integer errorCode, String message);

  /**
   * Выполняет логгирование всех действий.
   * @param errorCode код ошибки
   * @param message передаваемое сообщение
   */
  void logboard(Integer errorCode, String message);
  // название метода, как девушка Буратино - бревно и доска
}
