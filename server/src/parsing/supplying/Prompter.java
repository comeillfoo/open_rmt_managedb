package parsing.supplying;

import parsing.instructions.concrete.ConDecree;

import java.util.HashMap;
import java.util.Map;

/**
 * Абстракция Инвокера, содержащая
 * данные необходимые для вызова комманд
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Invoker
 */
public abstract class Prompter implements Invoker {
  protected final Map<String, ConDecree> availableCommands; // доступные к вызову команды

  /**
   * Конструктор устанавливащий
   * this very необходимые данные,
   * что нужны для вызова комманд
   */
  protected Prompter() {
    availableCommands = new HashMap<>();
  }
}
