package parsing;

import communication.Component;
import communication.Segment;
import communication.Mediator;
import logging.customer.HawkPDroid;
import parsing.customer.distro.LimboKeeper;

/**
 * Шаблон класса модуля, вытаскивающего пользовательский запрос
 * и делегирующий свою работу по обработке готовым классам.
 */
public abstract class Resolver implements Mediator, Component {
  protected final Mediator mediator;
  protected HawkPDroid radioman;
  protected LimboKeeper fate;
  public abstract void parse(Segment parcel);
  public Resolver(Mediator m) { mediator = m; }
}
