package parsing.instructions;

/**
 * Абстрактный класс комманд, расширяющий интерфейс
 * комманд основными полями и данными о команде.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Command
 */
public abstract class Decree implements Command {
  // every command must have information about itself [name, syntax, brief description, argument number, has arguments]
  protected static final String NAME = "debug";
  protected static final String BRIEF = "enables developer mode";
  protected static final String SYNTAX = "debug";
  protected static final int ARGNUM = 0;
  public static boolean isArgumented() { return ARGNUM > 0; }
}
