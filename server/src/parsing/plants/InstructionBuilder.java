package parsing.plants;

import communication.Component;
import parsing.Resolver;
import parsing.customer.distro.LimboKeeper;
import parsing.instructions.concrete.ConDecree;
import parsing.instructions.rotten.RawDecree;

/**
 *
 */
public final class InstructionBuilder implements Component {
  protected final Resolver magiV; // ссылка на SSPC
  protected final Factory marduk; // ссылка на производителя элементов

  public InstructionBuilder(Resolver controller, Factory facility) {
    magiV = controller;
    marduk = facility;
  }

  // метод формирования из отправленной команды
  // исполняемой команды
  public ConDecree make(RawDecree flesh, LimboKeeper receiver) {
    return null;
    // TODO: написать реализацию
  }
}
