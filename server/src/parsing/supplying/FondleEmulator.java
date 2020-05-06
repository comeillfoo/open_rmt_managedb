package parsing.supplying;

import communication.Component;
import communication.Mediator;
import parsing.Resolver;
import parsing.instructions.Command;
import parsing.instructions.concrete.ConDecree;
import parsing.instructions.concrete.base.Exit;
import parsing.instructions.concrete.base.Save;
import parsing.plants.InstructionBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

  /**
   * Вместо скроллинга ленты в ВК,
   * скроллим список доступных комманд
   * @return список доступных комманд
   */
  public final List<ConDecree> scroll() {
    List<ConDecree> listing = new ArrayList<>();
    availableCommands
        .entrySet()
        .stream()
        .forEach(
            (Map.Entry<String, ConDecree> enter)->
            {
              if (!(Exit.NAME.equals(enter.getKey()) || Save.NAME.equals(enter.getKey())))
                listing.add(enter.getValue());
            });
    return listing;
  }
}
