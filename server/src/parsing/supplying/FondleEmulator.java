package parsing.supplying;

import communication.Component;
import communication.Mediator;
import parsing.Resolver;
import parsing.plants.InstructionBuilder;

/**
 * Абстракция Invoker'a пригодного
 * работать в сети и взаимодействовать
 * с контроллером
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Prompter
 * @see Component
 * @see Invoker
 */
public abstract class FondleEmulator extends Prompter implements Component {
  protected final Resolver magiV; // ссылка на SSPC

  /**
   * Конструктор устанавливающий контроллер
   * над эмулятором
   * @param controller ссылка на SSPC
   */
  public FondleEmulator(Resolver controller) { magiV = controller; }
}
