package dataSection;

import instructions.rotten.RawDecree;
import java.util.HashMap;

/**
 * Интерфей, определяющий класс CommandList
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public interface Commands {
    public HashMap<String,RawDecree> getCommandMap();
}
