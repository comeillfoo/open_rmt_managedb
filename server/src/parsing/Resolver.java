package parsing;


import communication.Component;
import communication.Mediator;
import communication.Segment;
import entities.Organization;
import parsing.customer.bootstrapper.LoaferLoader;
import parsing.customer.distro.LimboKeeper;
import parsing.plants.Factory;
import parsing.plants.InstructionBuilder;
import parsing.supplying.FondleEmulator;
import systemcore.ServerController;

/**
 * Шаблон класса модуля, вытаскивающего пользовательский запрос
 * и делегирующий свою работу по обработке готовым классам.
 */
public abstract class Resolver implements Mediator, Component {
  protected final ServerController CONTROLLER; // контроллер модуля
  protected FondleEmulator kael; // сутенер комманд
  protected LimboKeeper fate; // сутенер коллекции
  protected InstructionBuilder wizard; // фабрика вызываемых комманд
  protected Factory plant; // фабрика элементов коллекции
  protected LoaferLoader<Organization> breadLoader; // загрузчик коллекции
  public abstract void parse(Segment parcel);
  public Resolver(ServerController controller) { CONTROLLER = controller; }
}
