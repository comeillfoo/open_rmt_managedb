package instructions.rotten.extended;

import instructions.rotten.RawDecree;
import instructions.rotten.ITitled;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * "Сырая" команда "execute_script".
 * содержит основную информацию о команде.
 * исполняет скрипт по указанному имени файла.
 */
public class RawExecuteScript extends RawDecree implements ITitled, Serializable {
  public static final String NAME = "execute_script";
  public static final String BRIEF = "Исполняет скрипт по указанному имени файла.";
  public static final String SYNTAX = NAME + " [file_name]";
  public static final int ARGNUM = 1;
  private final ArrayList<String> scriptString;

  /**
   * Конструктор, принимающий скрипт, перенесенный в ArrayList.
   * @param scriptString
   */
  public RawExecuteScript(ArrayList<String> scriptString) {
    this.scriptString = scriptString;
  }

  public ArrayList<String> getScriptString() { return scriptString; }

  @Override
  public String Name() {
    return null;
  }
}
