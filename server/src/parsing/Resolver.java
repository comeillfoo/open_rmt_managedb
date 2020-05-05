package parsing;

import communication.Component;
import communication.Segment;
import communication.Mediator;
import logging.customer.HawkPDroid;
import parsing.customer.distro.LimboKeeper;
import parsing.plants.Factory;
import parsing.supplying.Invoker;

/**
 * Шаблон класса модуля, вытаскивающего пользовательский запрос
 * и делегирующий свою работу по обработке готовым классам.
 */
public abstract class Resolver implements Mediator, Component {
  protected final Mediator mediator; // контроллер модуля
  protected HawkPDroid radioman; // логгер
  protected LimboKeeper fate; // сетевой ресивер
  protected Invoker kael; // сутенер комманд
  protected Factory plant; // фабрика элементов коллекции
  public abstract void parse(Segment parcel);
  public Resolver(Mediator m) { mediator = m; }
}
