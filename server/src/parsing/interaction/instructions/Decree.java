package parsing.interaction.instructions;

import java.io.Serializable;

/**
 * Абстрактный класс комманд, расширяющий интерфейс
 * комманд основными полями и данными о команде.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Command
 */
public abstract class Decree implements Command {
  // every command must have information about itself [name, syntax, brief description]
  protected static final String NAME = "debug";
  protected static final String BRIEF = "enables developer mode";
  protected static final String SYNTAX = "debug";
}
