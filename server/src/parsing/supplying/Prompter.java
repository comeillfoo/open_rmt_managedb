package parsing.supplying;

import instructions.concrete.ConDecree;

import java.util.*;

/**
 * Абстракция Инвокера, содержащая
 * данные необходимые для вызова комманд
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Invoker
 */
public abstract class Prompter implements Invoker {
  protected final Map<String, ConDecree> availableCommands; // доступные к вызову команды
  protected final SortedSet<String> junkedCommands = new TreeSet<String>() {
          {
              add("insert");
              add("update");
              add("remove_lower");
              add("replace_if_lower");
              add("replace_if_greater");
          }
      };

  /**
   * Конструктор устанавливащий
   * this very необходимые данные,
   * что нужны для вызова комманд
   */
  protected Prompter() {
    availableCommands = new HashMap<>();
  }
}
