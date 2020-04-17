package dispatching;

import communication.Component;
import communication.Segment;
import communication.Mediator;

/**
 * Шаблон модуля отправки результатов клиенту
 */
public abstract class Dispatcher implements Component {
  protected final Mediator kapellmeister;
  public abstract void sendCorona(Segment postcard);
  public Dispatcher(Mediator m) { kapellmeister = m; }
}
