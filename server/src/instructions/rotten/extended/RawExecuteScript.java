package instructions.rotten.extended;

import instructions.rotten.ITitled;
import instructions.rotten.RawDecree;

public class RawExecuteScript extends RawDecree implements ITitled {
  private final String FILE_NAME;
  public RawExecuteScript(String filename) {
    FILE_NAME = filename;
  }
  @Override
  public String Name() { return FILE_NAME; }

  public static final String NAME = "execute_script";
  public static final String BRIEF = "исполняет скрипт по указанному имени файла";
  public static final String SYNTAX = NAME + " [file_name]";
  public static final int ARGNUM = 1;
}
