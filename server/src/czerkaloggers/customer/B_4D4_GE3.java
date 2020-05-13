package czerkaloggers.customer;

import communication.Mediator;
import czerkaloggers.HawkPDroid;
import czerkaloggers.RadioLogger;
import parsing.Resolver;

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
public final class B_4D4_GE3 extends HawkPDroid<Resolver> {
  // builders

  public B_4D4_GE3(Mediator controller) { super((Resolver) controller); }
  /**
   * Помимо логгирования, еще и составляет протокол действий.
   *
   * @param errorCode код ошибки
   * @param message   передаваемое сообщение
   */
  @Override
  public void notify(Integer errorCode, String message) {
    // TODO: посмотреть теорию и написать реализацию
  }

  /**
   * Выполняет логгирование всех действий.
   *
   * @param errorCode код ошибки
   * @param message   передаваемое сообщение
   */
  @Override
  public void logboard(Integer errorCode, String message) {
    // TODO: посмотреть теорию и написать реализацию
  }
}
