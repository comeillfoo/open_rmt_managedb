package parsing.supplying;

import communication.Component;
import instructions.concrete.ConDecree;
import instructions.concrete.base.Exit;
import instructions.concrete.base.Save;
import parsing.Resolver;
import parsing.supplying.interpreter.Shell;


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
  protected final Resolver MAGIV; // ссылка на SSPC
  protected Shell fReader;

  /**
   * Конструктор устанавливающий контроллер
   * над эмулятором
   * @param controller ссылка на SSPC
   */
  public FondleEmulator(Resolver controller) { MAGIV = controller; }

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
