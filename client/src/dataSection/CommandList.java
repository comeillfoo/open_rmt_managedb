package dataSection;

import instructions.rotten.RawDecree;
import instructions.rotten.base.*;
import instructions.rotten.extended.*;
import java.util.HashMap;

/**
 * Класс содержащий в себе все доступные для клиента команды.
 * @author Leargy aka Anton Sushkevich
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @see Commands
 */
public class CommandList implements Commands {
    private HashMap<String,RawDecree> commandMap = new HashMap();

    /**
     * Конструктор в котором происходи добавление сырых команд в список доступных.
     */
    public CommandList() {
        commandMap.put(RawExit.SYNTAX,new RawExit());
        commandMap.put(RawClear.SYNTAX,new RawClear());
        commandMap.put(RawHelp.SYNTAX,new RawHelp());
        commandMap.put(RawInfo.SYNTAX,new RawInfo());
        commandMap.put(RawShow.SYNTAX,new RawShow());
        commandMap.put(RawInsert.SYNTAX,new RawInsert());
        commandMap.put(RawFilterContainsName.SYNTAX,new RawFilterContainsName());
        commandMap.put(RawMaxByDate.SYNTAX,new RawMaxByDate());
        commandMap.put(RawRemoveKey.SYNTAX,new RawRemoveKey());
        commandMap.put(RawRemoveLower.SYNTAX,new RawRemoveLower());
        commandMap.put(RawReplaceIfGreater.SYNTAX,new RawReplaceIfGreater());
        commandMap.put(RawReplaceIfLower.SYNTAX,new RawReplaceIfLower());
        commandMap.put(RawSumOfAnnualTurnover.SYNTAX,new RawSumOfAnnualTurnover());
        commandMap.put(RawUpdate.SYNTAX,new RawUpdate());
    }

    /**
     * Метод возвращающий проинициализированный список комманд.
     * @return HashMap<String,RawDecree>
     */
    public HashMap<String,RawDecree> getCommandMap() { return commandMap;}
}
