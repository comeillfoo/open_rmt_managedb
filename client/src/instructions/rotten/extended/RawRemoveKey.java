package instructions.rotten.extended;

import instructions.rotten.RawCommiter;
import instructions.rotten.IClued;

public class RawRemoveKey extends RawCommiter implements IClued<Integer> {
    public static final String NAME = "remove_key";
    public static final String BRIEF = "Удаляет элемент по [key].";
    public static final String SYNTAX = NAME + " [key]";
    public static final int ARGNUM = 1;
    private Integer key;

    @Override
    public Integer Key() {
        return key;
    }


    public RawRemoveKey setKey(Integer key) {
        this.key = key;
        return this;
    }
}
