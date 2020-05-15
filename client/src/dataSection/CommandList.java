package dataSection;

import instructions.rotten.base.*;
import instructions.rotten.extended.*;
import instructions.rotten.RawDecree;

import java.util.HashMap;

/**
 * Класс содержащий в себе все доступные для клиента команды.
 * @author Leargy aka Anton Sushkevich
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @see Commands
 */
public class CommandList implements Commands {
    private final HashMap<String,String> commandMap = new HashMap();

    /**
     * Конструктор в котором происходи добавление сырых команд в список доступных.
     */
    public CommandList() {
        commandMap.put(RawExit.SYNTAX, RawExit.NAME);
        commandMap.put(RawClear.SYNTAX, RawClear.NAME);
        commandMap.put(RawHelp.SYNTAX, RawHelp.NAME);
        commandMap.put(RawInfo.SYNTAX, RawInfo.NAME);
        commandMap.put(RawShow.SYNTAX, RawShow.NAME);
        commandMap.put(RawInsert.SYNTAX, RawInsert.NAME);
        commandMap.put(RawFilterContainsName.SYNTAX, RawFilterContainsName.NAME);
        commandMap.put(RawMaxByDate.SYNTAX, RawMaxByDate.NAME);
        commandMap.put(RawRemoveKey.SYNTAX, RawRemoveKey.NAME);
        commandMap.put(RawRemoveLower.SYNTAX, RawRemoveLower.NAME);
        commandMap.put(RawReplaceIfGreater.SYNTAX, RawReplaceIfGreater.NAME);
        commandMap.put(RawReplaceIfLower.SYNTAX, RawReplaceIfLower.NAME);
        commandMap.put(RawSumOfAnnualTurnover.SYNTAX, RawSumOfAnnualTurnover.NAME);
        commandMap.put(RawUpdate.SYNTAX, RawUpdate.NAME);
        commandMap.put(RawExecuteScript.SYNTAX, RawExecuteScript.NAME);
    }

    /**
     * Метод возвращающий проинициализированный список комманд.
     * @return HashMap<String,RawDecree>
     */
    public HashMap<String,String> getCommandMap() { return commandMap;}
}
