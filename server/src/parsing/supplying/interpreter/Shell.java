package parsing.supplying.interpreter;

import instructions.rotten.RawDecree;
import parsing.Resolver;
import parsing.plants.InstructionBuilder;
import parsing.supplying.Invoker;
import parsing.supplying.LilyInvoker;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Абстракция интерпретатора,
 * который распознает команды,
 * переданные в поток
 */
public abstract class Shell extends LilyInvoker {
  protected final String FILE_NAME;
  protected final Invoker CTRL;
  protected Shell(Resolver controller, String filename, Invoker ctrl) {
    super(controller);
    FILE_NAME = filename;
    CTRL = ctrl;

  }

  public abstract RawDecree parse(String line, BufferedReader paramReader);
}
