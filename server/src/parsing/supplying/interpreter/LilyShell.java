package parsing.supplying.interpreter;

import instructions.rotten.RawDecree;
import parsing.supplying.Invoker;

public final class LilyShell extends Shell {

  public LilyShell(String filename, Invoker ctrl) {
    super(filename, ctrl);
  }

  @Override
  public RawDecree parse(String line) {
    return null;
  }
}
