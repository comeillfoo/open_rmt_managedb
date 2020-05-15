package czerkaloggers;

import communication.Component;
import communication.Mediator;

/**
 * Логгер, умеющий работать с контроллером
 * исполнения запросов.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public abstract class HawkPDroid<T extends Mediator> implements RadioLogger, Component {
  // TODO: добавить ссылку на логгер
  protected final T MAGIV; // ссылка на SSPC
  // SubSystem Process Controller
  protected HawkPDroid(T controller) { MAGIV = controller; }

  public static HawkPDroid<? extends Mediator> assemble(Mediator controller, DroidFactory plant) {
    return plant.create(controller);
  }
}
