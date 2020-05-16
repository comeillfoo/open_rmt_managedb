package parsing.supplying.interpreter;

import instructions.rotten.RawDecree;
import parsing.Resolver;
import parsing.supplying.Invoker;
import parsing.supplying.LilyInvoker;

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

  public abstract RawDecree parse(String line);
}
