package dispatching;


import communication.Component;
import communication.Mediator;
import communication.Segment;

/**
 * Шаблон модуля отправки результатов клиенту
 */
public abstract class Dispatcher implements Component {
  protected final Mediator kapellmeister;
  public abstract void sendCorona(Segment postcard);
  public Dispatcher(Mediator m) { kapellmeister = m; }
}
