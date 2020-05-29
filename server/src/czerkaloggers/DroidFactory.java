package czerkaloggers;

import communication.Mediator;

/**
 * Фабрика дроидов? Нет, ****
 * фабрика логгеров
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public interface DroidFactory {
  /**
   * Фабричный метод создания логгеров
   * @param controller отправитель логгеров
   * @return экземпляр логгера
   */
  HawkPDroid<? extends Mediator> create(Mediator controller);
}
