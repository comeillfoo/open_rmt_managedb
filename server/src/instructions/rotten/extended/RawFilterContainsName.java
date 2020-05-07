package instructions.rotten.extended;

import instructions.rotten.RawDecree;
import instructions.rotten.IClued;

public class RawFilterContainsName extends RawDecree implements IClued<String> {
    public static final String NAME = "filter_contains_name";
    public static final String BRIEF = "Находит элементы коллекции по заданному имени";
    public static final String SYNTAX = NAME + " [name]";
    public static final int ARGNUM = 1;
    private String key;

    @Override
    public String Key() {
        return key;
    }

    public RawFilterContainsName setKey(String key) {
        this.key = key;
        return this;
    }
}
