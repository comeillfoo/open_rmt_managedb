package parsing;

import communication.Component;
import communication.Segment;
import communication.Mediator;
import logging.customer.HawkPDroid;
import parsing.customer.distro.LimboKeeper;
import parsing.plants.Factory;
import parsing.plants.InstructionBuilder;
import parsing.supplying.FondleEmulator;

/**
 * Шаблон класса модуля, вытаскивающего пользовательский запрос
 * и делегирующий свою работу по обработке готовым классам.
 */
public abstract class Resolver implements Mediator, Component {
  protected final Mediator mediator; // контроллер модуля
  protected HawkPDroid radioman; // логгер
  protected FondleEmulator kael; // сутенер комманд
  protected LimboKeeper fate; // сутенер коллекции
  protected InstructionBuilder wizard; // фабрика вызываемых комманд
  protected Factory plant; // фабрика элементов коллекции
  public abstract void parse(Segment parcel);
  public Resolver(Mediator controller) { mediator = controller; }
}
