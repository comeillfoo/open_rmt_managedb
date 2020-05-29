package parsing.supplying.interpreter;

import instructions.rotten.RawDecree;
import parsing.Resolver;
import parsing.plants.InstructionBuilder;
import parsing.supplying.Invoker;
import parsing.supplying.LilyInvoker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Абстракция интерпретатора,
 * который распознает команды,
 * переданные в поток
 *  @author Come_1LL_F00 aka Lenar Khannanov
 *  @author Leargy aka Anton Sushkevich
 */
public abstract class Shell extends LilyInvoker {
  protected String fileName;
  protected final Invoker CTRL;

  /**
   * Конструктор, принимающий ссылку на контроллер подпроцессов,
   * имя файла скрипта и ссылку на объект, вызывающий исполнение конкретных команд.
   */
  protected Shell(Resolver controller, String fileName, Invoker ctrl) {
    super(controller);
    this.fileName = fileName;
    CTRL = ctrl;
  }

  /**
   * Конструктор, принимающий ссылку на контроллер подпроцессов
   * и ссылку на объект, вызывающий исполнение конкретных команд.
   */
  protected Shell(Resolver controller, Invoker ctrl) {
    super(controller);
    CTRL = ctrl;
  }

  /**
   *
   * @param line
   * @param scriptParts
   * @return RawDecree
   */
  public abstract RawDecree parse(String line, ArrayDeque<String> scriptParts);
}
