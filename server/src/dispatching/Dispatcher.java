package dispatching;


import communication.Component;
import communication.Mediator;
import communication.Segment;
import czerkaloggers.HawkPDroid;
import systemcore.ServerController;

/**
 * Шаблон модуля отправки результатов клиенту
 */
public abstract class Dispatcher implements Component, Mediator {
  protected final ServerController kapellmeister; // ссылка на SSPC
  public abstract void sendCorona(Segment postcard);
  public Dispatcher(ServerController m) {
    kapellmeister = m;
  }
}
