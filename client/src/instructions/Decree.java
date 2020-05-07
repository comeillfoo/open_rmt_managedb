package instructions;
/**
 * Абстрактный класс комманд, расширяющий интерфейс
 * комманд основными полями и данными о команде.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Command
 */
public abstract class Decree {
  // every command must have information about itself [name, syntax, brief description, argument number, has arguments]
  public static final String NAME = "debug";
  public static final String BRIEF = "enables developer mode";
  public static final String SYNTAX = "debug";
  public static final int ARGNUM = 0;
  public static boolean isArgumented() { return ARGNUM > 0; }
}
