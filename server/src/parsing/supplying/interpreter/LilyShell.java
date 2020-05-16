package parsing.supplying.interpreter;

import instructions.rotten.RawDecree;
import parsing.Resolver;
import parsing.supplying.Invoker;

public final class LilyShell extends Shell {

  public LilyShell(Resolver controller, String filename, Invoker ctrl) {
    super(controller, filename, ctrl);
  }

  @Override
  public RawDecree parse(String line) {
    return null;
  }
}
