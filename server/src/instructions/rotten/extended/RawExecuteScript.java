package instructions.rotten.extended;

import instructions.rotten.ITitled;
import instructions.rotten.RawDecree;

import java.io.Serializable;
import java.util.ArrayList;

public class RawExecuteScript extends RawDecree implements ITitled, Serializable {
  //private final String FILE_NAME;
  private final ArrayList<String> scriptString;
  public RawExecuteScript(ArrayList<String> scriptString) {
    this.scriptString = scriptString;
    //FILE_NAME = filename;
  }
  public ArrayList<String> getScriptString() { return scriptString; }

  public static final String NAME = "execute_script";
  public static final String BRIEF = "исполняет скрипт по указанному имени файла";
  public static final String SYNTAX = NAME + " [file_name]";
  public static final int ARGNUM = 1;

  @Override
  public String Name() {
    return null;
  }
}
