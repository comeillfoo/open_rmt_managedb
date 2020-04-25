package parsing;

import communication.Component;
import communication.Segment;
import communication.Mediator;

/**
 * Шаблон класса модуля, вытаскивающего пользовательский запрос
 * и делегирующий свою работу по обработке готовым классам.
 */
public abstract class Resolver implements Component {
  protected final Mediator mediator;
  public abstract void parse(Segment parcel);
  public Resolver(Mediator m) {
    mediator = m;
  }
}
