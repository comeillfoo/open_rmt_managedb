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
 */
public abstract class Shell extends LilyInvoker {
  protected String fileName;
  protected final Invoker CTRL;

  protected Shell(Resolver controller, String fileName, Invoker ctrl) {
    super(controller);
    this.fileName = fileName;
    CTRL = ctrl;
  }

  protected Shell(Resolver controller, Invoker ctrl) {
    super(controller);
    CTRL = ctrl;
  }

  public abstract RawDecree parse(String line, ArrayDeque<String> scriptParts);
}
