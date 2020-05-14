package dispatching;


import communication.Component;
import communication.Mediator;
import communication.wrappers.AlertBag;
import systemcore.ServerController;

/**
 * Шаблон модуля отправки результатов клиенту
 */
public abstract class Dispatcher implements Component, Mediator {
  protected final ServerController kapellmeister; // ссылка на SSPC
  public abstract void send(AlertBag postcard);
  public Dispatcher(ServerController m) {
    kapellmeister = m;
  }
}
